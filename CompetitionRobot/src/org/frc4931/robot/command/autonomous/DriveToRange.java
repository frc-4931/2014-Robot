package org.frc4931.robot.command.autonomous;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.subsystems.Ranger;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

public class DriveToRange extends Command{
	private final PIDController pid;
	private final double targetRange;
	public DriveToRange(Ranger sensor, double range) {
		pid = Subsystems.pid;//new PIDController(01d,0,0,sensor,new PIDDriveInterface());
		targetRange = range;
		pid.setOutputRange(-0.25, 0.25);
		pid.setInputRange(0, 2.5);
		pid.setPercentTolerance(1.0d);
		pid.setContinuous(false);
//		SmartDashboard.putData("DRIVEPID", pid);
	}

	protected void initialize() {
		pid.setSetpoint(targetRange);
		pid.enable();
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return pid.onTarget();
	}

	protected void end() {
		pid.disable();
		System.out.println("Target Reached");
		Subsystems.driveTrain.stop();
	}

	protected void interrupted() {
		end();
	}

}