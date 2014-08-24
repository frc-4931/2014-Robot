package org.frc4931.robot.command.autonomous;

import org.frc4931.robot.CompetitionRobot;
import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.CommandBase;
import org.frc4931.robot.command.drive.PIDTurnInterface;
import org.frc4931.robot.subsystems.Ranger;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Approach a given value using a PID loop and a given sensor by changing the drive speed.
 * @author Zach Anderson
 *
 */
public class DriveToRange extends CommandBase{
	public static final double SPEED = 0.5;
	private final PIDController pid;
	private final PIDController stablizer;	
	private final double targetRange;
	
	/**
	 * Constructs the command with the given sensor and range.
	 * @param sensor the Sensor to read.
	 * @param range the target value.
	 */
	public DriveToRange(Ranger sensor, double range) {
		CompetitionRobot.output("Driving to range "+range);
		pid = Subsystems.pid;
		stablizer = new PIDController(0.04,0,0, Subsystems.imu, new PIDTurnInterface());
//		pid = new PIDController(01d,0,0,sensor,new PIDDriveInterface());
		targetRange = range;
//		pid.setOutputRange(-1*SPEED, SPEED);
		pid.setOutputRange(-1*SmartDashboard.getNumber("MaxApproachSpeed"), SmartDashboard.getNumber("MaxApproachSpeed"));
		pid.setInputRange(0, 2.5);
		pid.setPercentTolerance(1.0d);
		pid.setContinuous(false);
		stablizer.setOutputRange(-0.2, 0.2);
		stablizer.setInputRange(0,360);
		stablizer.setPercentTolerance(1.0);
		stablizer.setContinuous(true);
	}

	/**
	 * Sets up and starts the PID Loops.
	 */
	protected void initialize() {
		pid.setSetpoint(targetRange);
		pid.enable();
		stablizer.setSetpoint(stablizer.get());
		stablizer.enable();
		super.initialize();
	}

	protected boolean isFinished() {
		return pid.onTarget();
	}

	/**
	 * Closes the PID Loops and stops the drive train.
	 */
	protected void end() {
		stablizer.disable();
		pid.disable();
		CompetitionRobot.output("Current range "+Subsystems.ranger.pidGet()+" is within tolerance.");
		Subsystems.driveTrain.stop();
		super.end();
	}
}