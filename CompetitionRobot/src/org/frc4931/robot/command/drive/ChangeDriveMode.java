package org.frc4931.robot.command.drive;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class ChangeDriveMode extends Command{
	private final int driveMode;
	public ChangeDriveMode(int mode) {
		requires(Subsystems.driveTrain);
		driveMode = mode;
	}

	protected void execute() {
		Subsystems.robot.driveMode = driveMode;
	}
	
	protected void end() {
	}

	protected void initialize() {
	}

	protected void interrupted() {
		end();
	}

	protected boolean isFinished() {
		return true;
	}

}
