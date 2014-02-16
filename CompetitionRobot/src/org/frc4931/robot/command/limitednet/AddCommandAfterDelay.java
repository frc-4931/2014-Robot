package org.frc4931.robot.command.limitednet;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AddCommandAfterDelay extends CommandGroup{

	public AddCommandAfterDelay(Command command, double delay) {
		addSequential(new WaitCommand(delay));
		addSequential(command);
	}

}
