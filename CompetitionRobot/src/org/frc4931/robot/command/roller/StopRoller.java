package org.frc4931.robot.command.roller;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.OneShotCommand;

/**
 * Stops the roller.
 * @author Zach Anderson
 *
 */
public class StopRoller extends OneShotCommand{

	public StopRoller() {
		requires(Subsystems.roller);
	}
	
	protected void execute() {
		Subsystems.roller.stop();
	}
}
