package org.frc4931.robot.command.autonomous;

import org.frc4931.robot.command.drive.PIDDriveInterface;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveToRange extends Command{
	private final PIDController pid;
	private final double targetRange;
	public DriveToRange(AnalogChannel sensor, double range) {
		pid = new PIDController(1,0,0,sensor,new PIDDriveInterface());
		SmartDashboard.putData("Drive to Range", pid);
		targetRange = range;
		pid.setInputRange(0, 5);
		pid.setOutputRange(-0.75, 0.75);
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
	}

	protected void interrupted() {
		end();
	}

}