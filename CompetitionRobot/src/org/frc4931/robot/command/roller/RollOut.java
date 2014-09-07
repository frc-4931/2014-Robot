package org.frc4931.robot.command.roller;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.OneShotCommand;

/**
 * Rolls the roller out.
 * @author Zach Anderson
 *
 */
public class RollOut extends OneShotCommand{

	public RollOut() {
		requires(Subsystems.roller);
	}
	
	protected void doExecute() {
		Subsystems.roller.rollOut();
	}
}
