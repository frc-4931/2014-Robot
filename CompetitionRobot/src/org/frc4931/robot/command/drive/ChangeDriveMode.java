package org.frc4931.robot.command.drive;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.OneShotCommand;

/**
 * Changes the drive mode of CopetitionRobot.
 *
 * @author Zach Anderson
 */
public class ChangeDriveMode extends OneShotCommand{
	private final int driveMode;
	
	public ChangeDriveMode(int mode) {
		requires(Subsystems.driveTrain);
		driveMode = mode;
	}

	protected void execute() {
		Subsystems.robot.driveMode = driveMode;
	}
}
