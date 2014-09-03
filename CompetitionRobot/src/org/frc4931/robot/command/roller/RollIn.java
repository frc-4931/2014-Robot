package org.frc4931.robot.command.roller;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.OneShotCommand;

/**
 * Rolls the roller in.
 * @author Zach Anderson
 *
 */
public class RollIn extends OneShotCommand{
	
	public RollIn() {
		requires(Subsystems.roller);
	}

	protected void doExecute() {
		Subsystems.roller.rollIn();
	}
}
