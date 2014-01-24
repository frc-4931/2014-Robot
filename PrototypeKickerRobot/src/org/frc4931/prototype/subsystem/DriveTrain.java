/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype.subsystem;

import org.frc4931.prototype.Robot;
import org.frc4931.prototype.Robot.KickMotors;
import org.frc4931.prototype.command.StartKick;
import org.frc4931.prototype.command.StopKick;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * The drive train, which sets up and uses an {@link StartKick} command by default.
 */
public class DriveTrain extends Subsystem {

    private static final double MAX_SPEED_FACTOR = 1.0d;
    private static final double MIN_SPEED_FACTOR = 0.0d;

    private static final double MAX_KICK_DURATION_IN_SECONDS = 2.0d;
    private static final double MIN_KICK_DURATION_IN_SECONDS = 0.0d;

    protected final Jaguar leftMotor;
    protected final Jaguar rightMotor;
    private final RobotDrive drive;

    private volatile double speedFactor = MAX_SPEED_FACTOR;

    private volatile double kickDuration = KickMotors.INITIAL_KICK_DURATION_IN_SECONDS;

    public DriveTrain() {
        // Set up the motors ...
        this.leftMotor = new Jaguar(Robot.KickMotors.LEFT_PORT);
        this.rightMotor = new Jaguar(Robot.KickMotors.RIGHT_PORT);

        // And the drive controller ...
        drive = new RobotDrive(leftMotor, rightMotor);
        drive.setInvertedMotor(Robot.KickMotors.LEFT_POSITION, Robot.KickMotors.LEFT_REVERSED);
        drive.setInvertedMotor(Robot.KickMotors.RIGHT_POSITION, Robot.KickMotors.RIGHT_REVERSED);
        drive.setSafetyEnabled(false);
        setMaxDriveSpeed(Robot.KickMotors.INITIAL_MAX_KICK_SPEED);
    }

    /**
     * Initialize the default command that will be run whenever no commands for this subsystem are enqueued.
     */
    protected void initDefaultCommand() {
        setDefaultCommand(new StopKick());
    }

    /**
     * Drive forward or backward.
     * <p>
     * This can be called within commands.
     * </p>
     * 
     * @param speedFactor the fraction of full-speed to drive, ranging from -1.0 (backward at full power) to 1.0 (forward at full
     *        power)
     */
    public void driveStraight( double speedFactor ) {
        drive.tankDrive(speedFactor, speedFactor);
    }

    /**
     * Stop driving.
     * <p>
     * This can be called within commands.
     * </p>
     */
    public void stopAllMotors() {
        drive.stopMotor();
    }

    /**
     * Increase or decrease the maximum drive speed by the given delta. Calling this method is safe even if the delta is out of
     * range, because the maximum drive speed will never be set smaller than 0.0 or larger than 1.0.
     * <p>
     * This can be called within commands.
     * </p>
     * 
     * @param delta the change in the maximum drive speed, between -1.0 and 1.0
     */
    public void changeMaxDriveSpeed( double delta ) {
        setMaxDriveSpeed(speedFactor + delta);
    }

    /**
     * Increase or decrease the kick duration by the given delta. Calling this method is safe even if the delta is out of range,
     * because the maximum drive speed will never be set smaller than 0.0 or larger than 1.0.
     * <p>
     * This can be called within commands.
     * </p>
     * 
     * @param deltaInSeconds the change in the maximum kick duration (in seconds), between -1.0 and 1.0
     */
    public void changeKickDuration( double deltaInSeconds ) {
        double newDuration = kickDuration + deltaInSeconds;
        // Make sure the new speed is in range ...
        newDuration = Math.max(newDuration, MAX_KICK_DURATION_IN_SECONDS);
        newDuration = Math.min(newDuration, MIN_KICK_DURATION_IN_SECONDS);
        kickDuration = newDuration;
    }

    /**
     * Get the duration of the kick in seconds.
     * 
     * @return kickDuration
     */
    public double getKickDurationInSeconds() {
        return kickDuration;
    }

    /**
     * Set the maximum drive speed by the given delta.
     * <p>
     * This can be called within commands.
     * </p>
     * 
     * @param newSpeed the new maximum drive speed, between 0.0 and 1.0; if negative, then 0.0 will be used; if greater than 1.0,
     *        then 1.0 will be used.
     */
    public void setMaxDriveSpeed( double newSpeed ) {
        Robot.print("Setting max drive speed to " + newSpeed);
        // Make sure the new speed is in range ...
        newSpeed = Math.max(newSpeed, MIN_SPEED_FACTOR);
        newSpeed = Math.min(newSpeed, MAX_SPEED_FACTOR);
        speedFactor = newSpeed;
        drive.setMaxOutput(newSpeed);
    }

    public void initTable( ITable table ) {
        super.initTable(table);
        ITable t = getTable();
        if (t != null) {
            t.putNumber("Motor (left)", leftMotor.getSpeed());
            t.putNumber("Motor (right)", rightMotor.getSpeed());
            t.putNumber("Max speed", speedFactor);
            t.putNumber("Kick duration (sec)", kickDuration);
        }
    }

    public void addInLiveWindow() {
        LiveWindow.addActuator(getName(), "left motor", leftMotor);
        LiveWindow.addActuator(getName(), "right motor", rightMotor);
    }
}
