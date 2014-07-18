package org.frc4931.robot.command.autonomous;

import org.frc4931.robot.CompetitionRobot;
import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.drive.PIDTurnInterface;
import org.frc4931.robot.subsystems.Ranger;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveToRange extends Command{
	public static final double SPEED = 0.5;
	private final PIDController pid;
	private final PIDController stablizer;	
	private final double targetRange;
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

	protected void initialize() {
		pid.setSetpoint(targetRange);
		pid.enable();
		stablizer.setSetpoint(stablizer.get());
		stablizer.enable();
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return pid.onTarget();
	}

	protected void end() {
		stablizer.disable();
		pid.disable();
		CompetitionRobot.output("Current range "+Subsystems.ranger.pidGet()+" is within tolerance.");
		Subsystems.driveTrain.stop();
		CompetitionRobot.output("Operation complete.");
	}

	protected void interrupted() {
		end();
	}

}