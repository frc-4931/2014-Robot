package org.frc4931.robot.command.net;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Adds a given command after a given delay.
 * @author Zach Anderson
 *
 */
public class AddCommandAfterDelay extends CommandGroup{

	/**
	 * Constructs the command group with a given command and delay.
	 * @param command The command to add after a delay.
	 * @param delay The delay to add the command after.
	 */
	public AddCommandAfterDelay(Command command, double delay) {
		addSequential(new WaitCommand(delay));
		addSequential(command);
	}

}
