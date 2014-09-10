/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2013. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package org.frc4931.robot.command.autonomous;

import org.frc4931.robot.CompetitionRobot;
import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.CommandBase;
import org.frc4931.robot.subsystems.IMU;
import org.frc4931.robot.subsystems.Ranger;
import org.frc4931.zach.utils.Transform;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Ultrasonic;

/**
 * A command that will (autonomously) drive the robot toward one of the low goals, ensuring that the robot follows the side wall
 * of the field by some present "ideal" distance.
 * <p>
 * The algorithm works by calculating the position of the robot relative to the nearest side wall and the goal wall, and as the
 * robot drives forward how much the robot should be rotated from its current orientation so that the robot will be at the
 * prescribed "ideal" distance from the wall before the robot reaches the specified range to goal wall.
 * </p>
 * <h2>Geometric equations</h2>
 * <p>
 * Consider a robot placed onto the field, perfectly parallel to the side wall so that it is facing the front goal. The robot has
 * an ultrasonic sensor that will report the distance from the wall. If the robot is rotated (but not moved), the distance sensor
 * will report a larger distance, even though the robot has not moved. The measured distance is related to the actual distance by
 * the cosine of the angle of rotation, where the hypotenuse is the measured distance plus the distance that the sensor is from
 * the robot's center of rototation.
 *
 * <pre>
 *                                      actualDistance
 * cos( rotationAngle ) = ----------------------------------------------------
 *                         measuredDistance + distanceFromSensorToRobotCenter
 * </pre>
 *
 * If we assume that <code>distanceFromSensorToRobotCenter</code> is equal to 1/2 of the width of the robot frame, then:
 *
 * <pre>
 *                                  actualDistance
 * cos( rotationAngle ) = -----------------------------------
 *                         measuredDistance + 0.5*robotWidth
 * </pre>
 *
 * Therefore, we can calculate the actual distance from the robot's center of rotation to the side wall:
 *
 * <pre>
 * actualDistance = (measuredDistance + 0.5 * robotWidth) * cos(rotationAngle)
 * </pre>
 *
 * Using similar geometry, we can calculate the actual range from the robot's center of rotation to the goal wall:
 *
 * <pre>
 * actualRange = (measuredRange + 0.5 * robotLength) * cos(rotationAngle)
 * </pre>
 *
 * We can then determine how the <i>distance error</i>, which is the difference between the actual distance to the side wall and
 * the desired distance to the wall:
 *
 * <pre>
 * distanceError = actualDistance - targetDistance
 * </pre>
 *
 * Note that <code>distanceError</code> is <i>positive</i> if the robot's center of rotation is <i>farther</i> from the wall than
 * the target distance, or <i>negative</i> if the robot's center of rotation is <i>closer</i> from the wall than the target
 * distance.
 * <p>
 * So, assuming that we are not the correct distance from the wall, then we can eliminate this error if we rotate the robot so
 * that as it drives forward a <i>correction range</i>, this error will approach zero. If the correction range is relatively
 * small, the robot will more quickly approach the target distance from the wall. Likewise, if the correction range is relatively
 * large, the robot will more slowly approach the target distance from the wall. the rob ot's center of rotation is Therefore, if
 * we know the measured distance and the measured angle of rotation, then we can calculate the actual distance from the wall.
 * <p>
 * So what value should we use for this correction range? We could pick a fixed value, or we can choose to make it a function of
 * the actual range to the goal wall. For example, if we use the actual range for the correction range, then by definition this
 * algorithm will attempt to rotate the robot so that the distance error gets to 0 exactly when the robot reaches the prescribed
 * stopping range in front of the wall. On the other hand, if the correction range is 1/2 of the actual range, then the robot
 * should reach the prescribed distance from the wall half-way to it's stopping range. In other words:
 *
 * <pre>
 *                         correctionRange
 * correctionRangeRatio = -----------------  <= 1.0
 *                           actualRange
 * </pre>
 *
 * The lower this ration, the smaller the correction range and the more quickly the robot will attempt to correct the distance
 * error. We can thus compute the correction range given the predetermined ratio and previously-computed actual range:
 *
 * <pre>
 * correctionRange = actualRange * correctionRangeRatio
 * </pre>
 * <p>
 * The next step is to determine what rotation angle would correctly orient the robot so that when it reaches the correction range
 * the distance error is exactly 0. The tangent of this <i>correction angle</i> is simply the ratio of distance error to the
 * correction range:
 *
 * <pre>
 *                            distanceError
 * tan( correctionAngle ) = -----------------
 *                           correctionRange
 * </pre>
 *
 * or
 *
 * <pre>
 * correctionAngle = tan<sup>-1</sup>( distanceError / correctionRange )
 * </pre>
 *
 * But the robot is already at some rotation angle, <code>rotationAngle</code>, so we have to rotate the robot from it's current
 * orientation to the correction ange, and this difference in rotation is:
 *
 * <pre>
 * turnAngle = -(rotationAngle - correctionAngle)
 * </pre>
 * <p>
 * If the <code>turnAngle</code> is positive then the robot must turn away from the wall; if the <code>turnAngle</code> is
 * negative then the robot must turn away from the wall.
 * </p>
 * <h2>Turning the robot</h2>
 * <p>
 * So we know how much we need to rotate the robot from its current orientation so that the distance error will be zero at exactly
 * the aforementioned correction range. But we cannot instantaneously rotate the robot an arbitrary amount. Instead we have to
 * tell the robot to turn using the drive system's non-linear and turn "speed" that does not correlate to a physical rate.
 * </p>
 * <p>
 * We can do this by computing the turn speed based upon the magnitude of the turn angle. If the turn angle is higher, we want a
 * high turn speed. If the turn angle is small, we want a small turn speed. The fact that the turn speed non-linearly affects the
 * actual robot's turn rate simply means that we have to estimate this ratio.
 *
 * <pre>
 *                   turnSpeed
 * turnFactor = --------------------
 *               turnAngleInDegrees
 * </pre>
 * <p>
 * Let's look at some turn speeds. A value of 0.4 turns the robot pretty quickly, and we probably want to do this only when the
 * angle is high, such as 40 degrees. The factor would then be:
 *
 * <pre>
 *                   turnSpeed          0.4
 * turnFactor = -------------------- = ----- = 0.01
 *               turnAngleInDegrees     40
 * </pre>
 *
 * We can then solve for the turn speed:
 *
 * <pre>
 * turnSpeed = turnFactor * turnAngleInDegrees = 0.01 * turnAngleInDegrees
 * </pre>
 *
 * This command introduces a scale factor for this conversion, such that:
 *
 * <pre>
 * turnSpeed = turnFactor * turnAngleInDegrees * scale = 0.01 * turnAngleInDegrees * scale
 * </pre>
 *
 * or
 *
 * <pre>
 * turnSpeed = 0.01 * turnAngleInDegrees * scale
 * </pre>
 *
 * where scale defaults to be 1.0.
 * <p>
 * <h2>Assumptions</h2>
 * <p>
 * This command makes several assumptions:
 * <ol>
 * <li>The geometry is idealized, and may not exactly reflect the actual robot configuration. Any variation from this will
 * introduce some error on the real robot, but this should be acceptable as the algorithm is recomputed many times each second,
 * and because the algorithm will always attempting to minimize the errors.</li>
 * <li>The command will automatically determine whether it is closer to the left side wall or right side wall.</li>
 * <li>The robot is placed on the field exactly parallel with the side walls.</li>
 * <li>The robot's center of rotation is approximately in the center of the robot, half-way from front to back and half-way from
 * side to side.</li>
 * <li>The robot has a gyroscope that can measure the current angle of rotation. Rates of rotation are not used. This algorithm
 * depends on the angle, so this is assumed to be relatively accurate.</li>
 * <li>The robot has one distance sensor on each side of the robot. Each of these is located on the robot on the outside of the
 * robot frame. The algorithm assumes that this position is exactly at the logitudinal location of the center of rotation (1/2 of
 * the length of the robot frame).</li>
 * <li>The robot has one range sensor on the front of the robot, flush with the outside of the frame. The algorithm assumes that
 * this position is exactly at the lateral location of the center of rotation (1/2 of width of the robot frame).</li>
 * </ol>
 * </p>
 */
public class AngularFollowWall extends CommandBase {

    /**
     * The default turn scale for the constructor. The value is <code>1.0</code>.
     */
    public static final double DEFAULT_TURN_SCALE = 1.0;

    /**
     * The default value for the ratio of range correction to actual range. The value is <code>0.4</code>.
     */
    public static final double DEFAULT_CORRECTION_RANGE_SCALE = 0.4;

    /**
     * The tolerance in the rotation angle (positive or negative) below which no correction will be used.
     */
    private static final double ROTATION_ANGLE_TOLERANCE_IN_DEGREES = 1.0;

    /**
     * The constant value that is scaled by {@link #turnScaleFactor} and that converts a turn angle in degrees to a drive train
     * turn speed. A very high turn speed is 0.4, so we'll assume we want to turn at this speed if the angle is 40 degrees.
     * Assuming a linear relationship, then 0.4/40 is 0.01.
     */
    private static final double RATIO_OF_TURN_SPEED_TO_TURN_ANGLE_IN_DEGREES = 0.01;

    /**
     * The maximum turn speed.
     */
    private static final double MAX_TURN_SPEED = 0.3;

    private final double targetDistanceFromWall;
    private final double widthOfRobot;
    private final double lengthOfRobot;
    private final double minRange;
    private final double correctionRangeRatio;
    private final double turnScaleFactor;

    /**
     * The robot's left side is closer to the wall than the right.
     */
    private boolean wallOnLeft;

    /**
     * The angle of the robot relative to the field when this command starts. Positive is always turning toward the wall.
     */
    private double startingRobotAngleOfRotation;

    /**
     * The ultrasonic sensor that we'll use throughout.
     */
    private Ultrasonic distanceSensor;
    private Ranger rangeSensor;
    private IMU imu;
    private Averager angleAverager = new Averager(10);

    /**
     * Create a new command.
     *
     * @param targetDistanceFromWall the ideal distance in inches from the <i>center of the robot</i> to the wall; must be
     *        positive
     * @param minRange the range in inches from the front of the robot to the goal wall at which the robot should moving; must be
     *        positive
     * @param widthOfRobot the width of the robot frame in inches
     * @param lengthOfRobot the length of the robot frame in inches
     */
    public AngularFollowWall( double targetDistanceFromWall,
                              double minRange,
                              double widthOfRobot,
                              double lengthOfRobot ) {
        this(targetDistanceFromWall, minRange, widthOfRobot, lengthOfRobot, DEFAULT_CORRECTION_RANGE_SCALE, DEFAULT_TURN_SCALE);
    }

    /**
     * Create a new command.
     *
     * @param targetDistanceFromWall the ideal distance in inches from the <i>center of the robot</i> to the wall; must be
     *        positive
     * @param minRange the range in inches from the front of the robot to the goal wall at which the robot should moving; must be
     *        positive
     * @param widthOfRobot the width of the robot frame in inches
     * @param lengthOfRobot the length of the robot frame in inches
     * @param correctionRangeScale the ratio of the range at which this command will attempt to correct the error in the distance
     *        to the wall, relative to the total range of the robot from the front of the wall; must be positive, but should
     *        generally be less than or equal 1.0.
     * @param turnScaleFactor the scale factor to determine the turn speed based upon the number of degrees that we need to turn
     *        the robot; 1 means turn more slowly, 10 means turn more quickly
     */
    public AngularFollowWall( double targetDistanceFromWall,
                              double minRange,
                              double widthOfRobot,
                              double lengthOfRobot,
                              double correctionRangeScale,
                              double turnScaleFactor ) {
        super(true); // continuous
        this.targetDistanceFromWall = targetDistanceFromWall;
        this.widthOfRobot = widthOfRobot;
        this.lengthOfRobot = lengthOfRobot;
        this.minRange = minRange;
        this.correctionRangeRatio = correctionRangeScale;
        this.turnScaleFactor = turnScaleFactor;
    }

    protected void initialize() {
        // Before the robot has moved, we want to reset the gyro so that the current angle of rotation is 0.
        // Subsystems.imu.resetGyro();
        imu = Subsystems.imu;
        startingRobotAngleOfRotation = imu.getOrientationAngle(); // > -180 and <= 180

        // We don't know ahead of time whether the robot is closer to the left wall or right wall, so use both sensors
        // to figure this out ...
        double leftDistance = Subsystems.leftUltrasonicSensor.getRangeInches();
        double rightDistance = Subsystems.rightUltrasonicSensor.getRangeInches();
        leftDistance = Transform.clamp(0, 40, leftDistance);
        rightDistance = Transform.clamp(0, 40, rightDistance);
        if (leftDistance < rightDistance) {
            distanceSensor = Subsystems.leftUltrasonicSensor;
            wallOnLeft = true;
        } else {
            distanceSensor = Subsystems.rightUltrasonicSensor;
            wallOnLeft = false;
        }

        // Hack ...
        wallOnLeft = true;
        distanceSensor = Subsystems.leftUltrasonicSensor;

        rangeSensor = Subsystems.ranger;
    }

    protected void doExecute() {
        if (!Subsystems.robot.isAutonomous()) {
            CompetitionRobot.output("Command Cancelled");
            this.cancel();
            return;
        }
        // We need to measure the range, distance to wall, and robot angle ...
        double range = rangeSensor.getRange(); // in inches
        double distance = distanceSensor.getRangeInches();
        // Negative is left ...
        double rotationInDegrees = imu.getOrientationAngle(startingRobotAngleOfRotation); // positive or negative
        if (wallOnLeft) {
            // The wall is on the right, so we have to reverse the sign of the angle
            rotationInDegrees *= -1.0;
        }
        // // HACK:
        // rotationInDegrees = angleAverager.getValue(rotationInDegrees);

        double rotationInRadians = Math.toRadians(rotationInDegrees);

        // We want to reduce the drive speed as we approach the goal wall (e.g., as range approaches 0) ...
        double driveSpeed = Transform.clamp(0, 0.4, range / 100);

        // Compute the actual distance to the wall, based upon the current rotation and the distance measurment ...
        double actualDistance = (distance + 0.5 * widthOfRobot) * Math.cos(rotationInRadians);

        // Compute how far away we actually are from our ideal distance to the wall. This will be negative when we are
        // CLOSER to the wall than the ideal distance ...
        double distanceError = actualDistance - targetDistanceFromWall;
        if (Math.abs(distanceError) > 50.0) {
            CompetitionRobot.output("Distance error too great (" + Math.abs(distanceError)
                                    + " inches); cancelling autonomous command");
            this.cancel();
            return;
        }

        // Determine the actual range to the goal wall, based upon the current rotation and the range measurement ...
        double actualRange = (range + 0.5 * lengthOfRobot) * Math.cos(rotationInRadians);

        // Determine the range at which we want to eliminate the distance error ...
        double correctionRange = actualRange * correctionRangeRatio;
        correctionRange = Transform.clamp(30, 250, correctionRange);

        // Determine the correction angle at which the robot can drive forward for some correction range at which point the
        // distance error will be 0. Note that Java ME does not have 'atan', but com.sun.sqawk does...
        double correctionAngleInRadians = MathUtils.atan(distanceError / correctionRange);
        double correctionAngleInDegress = Math.toDegrees(correctionAngleInRadians);

        // How much must we (ideally) rotate the robot to get to this angle? A positive value means we turn away from
        // the wall; a negative value means we turn towards the wall ...
        double turnAngleInDegrees = -1 * (rotationInDegrees - correctionAngleInDegress);

        // Compute the turn speed, which we assume to be a function of the turn angle ...
        double turnSpeed = turnAngleInDegrees * turnScaleFactor * RATIO_OF_TURN_SPEED_TO_TURN_ANGLE_IN_DEGREES;

        // Limit the turn speed to positive or negative MAX_TURN_SPEED ...
        turnSpeed = Transform.clamp(-MAX_TURN_SPEED, MAX_TURN_SPEED, turnSpeed);

        // Figure out which way we should turn ...
        if (turnAngleInDegrees > 0) {
            // We are turning towards the wall ...
            if (wallOnLeft) {
                // wall is on the left, and we want to turn towards it (to the left), so the turn speed must be positive ...
                turnSpeed = Math.abs(turnSpeed);
            } else {
                // wall is on the right, and we want to turn towards it (to the right), so the turn speed must be negative ...
                turnSpeed = -Math.abs(turnSpeed);
            }
        } else if (turnAngleInDegrees < 0) {
            // We are turning away from the wall ...
            if (wallOnLeft) {
                // wall is on the left, and we want to turn away from it (to the right), so the turn speed must be negative ...
                turnSpeed = -Math.abs(turnSpeed);
            } else {
                // wall is on the right, and we want to turn away from it (to the left), so the turn speed must be positive ...
                turnSpeed = Math.abs(turnSpeed);
            }
        } else {
            // We're orientated correctly (or close enough), so don't turn ...
            turnSpeed = 0.0;
        }

        debugMessage(rotationInDegrees, distanceError, actualDistance, actualRange, turnAngleInDegrees, turnSpeed);

        // Set the drive speed and turn speed ...
        Subsystems.driveTrain.setDriveSpeed(driveSpeed);
        Subsystems.driveTrain.setTurnSpeed(turnSpeed);
    }

    private void debugMessage( double rotation,
                               double distanceError,
                               double actualDistance,
                               double range,
                               double turnAngle,
                               double turnSpeed ) {
//        CompetitionRobot.output("AngularFollowWall: aR=" + rotation + " deg; dE=" + distanceError + " in; dM=" + actualDistance
//                                + "; rS=" + turnAngle + " in; rR=" + range + " in; vT=" + turnSpeed);
    }

    /**
     * This command stops when the range from the robot to the front wall is less than the prescribed minimum range.
     *
     * @return true if finished, or false otherwise
     */
    protected boolean isFinished() {
        try {
            return rangeSensor.getRange() < minRange;
        } catch (RuntimeException e) {
            // If any error occurs, stop immediately ...
            this.end();
            throw e;
        }
    }

    /**
     * Complete this command.
     */
    protected void end() {
        try {
            super.end();
        } finally {
            Subsystems.driveTrain.stop();
        }
    }

    public static class Averager {
        private final double[] values;
        private final int size;
        private int index = 0;
        private int numValues;

        public Averager( int num ) {
            this.size = num;
            this.values = new double[num];
        }

        public double getValue( double newValue ) {
            if (index >= size) {
                index = 0;
            } else if (numValues < size) {
                ++numValues;
            }
            values[index] = newValue;
            // Compute the average ...
            double average = 0.0d;
            for (int i = 0; i != numValues; ++i) {
                average += values[i];
            }
            return average / numValues;
        }
    }

}
