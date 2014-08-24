package org.frc4931.robot.command.autonomous;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.CommandBase;

/**
 * Drives at the specified speed until interrupted.
 * @author Zach Anderson
 *
 */
public class DriveAtSpeed extends CommandBase{
	private final double speed;
	
	/**
	 * Constructs the command with the given speed.
	 * @param speed the speed to drive at as a double between -1.0 and 1.0.
	 */
	public DriveAtSpeed(double speed) {
		requires(Subsystems.driveTrain);
		this.speed = speed;
	}

	protected void execute() {
		Subsystems.driveTrain.arcadeDrive(speed, 0);
	}

	protected void end() {
		Subsystems.driveTrain.stop();
		super.end();
	}

	protected boolean isFinished() {
		return false;
	}
}
