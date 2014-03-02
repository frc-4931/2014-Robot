package org.frc4931.robot.command.autonomous;

import org.frc4931.robot.command.drive.PIDDriveInterface;
import org.frc4931.zach.io.AnalogInput;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

public class DriveToRange extends Command{
	private final PIDController pid;
	public DriveToRange(AnalogInput range) {
		pid = new PIDController(1,0,0,range,new PIDDriveInterface());
//		pid.setInputRange(0, 1);
//		pid.setOutputRange(-1, 1);
	}

	protected void initialize() {
		pid.setSetpoint(0.2);
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