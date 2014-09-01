package org.frc4931.robot.command.autonomous;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.CommandBase;

/**
 * Turn at a given speed until interupted.
 * @author Zach Anderson
 *
 */
public class TurnAtSpeed extends CommandBase{
	private final double speed;
	
	/**
	 * Constructs the command with a given speed.
	 * @param speed the speed to turn at.
	 */
	public TurnAtSpeed(double speed) {
		requires(Subsystems.driveTrain);
		this.speed = speed;
	}

	protected void doExecute() {
		Subsystems.driveTrain.arcadeDrive(0, speed);
	}

	protected boolean isFinished() {
		return false;
	}

	/**
	 * Stops the drive train.
	 */
	protected void end() {
		Subsystems.driveTrain.arcadeDrive(0, 0);
		super.end();
	}
}