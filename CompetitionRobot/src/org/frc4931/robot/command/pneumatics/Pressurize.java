package org.frc4931.robot.command.pneumatics;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.CommandBase;

/**
 * Pressurizes the pneumatic system.
 * @author Zach Anderson
 *
 */
public class Pressurize extends CommandBase{
	/**
	 * Constructs the command.
	 */
	public Pressurize() {
		requires(Subsystems.compressor);
		this.setInterruptible(false);
	}

	protected void execute() {
		Subsystems.compressor.activate();
	}

	protected boolean isFinished() {
		return !Subsystems.compressor.testPressure();
	}

	protected void end() {
		Subsystems.compressor.deactive();
	}
}
