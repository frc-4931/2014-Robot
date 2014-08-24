package org.frc4931.robot.command.groups;

import org.frc4931.robot.command.pneumatics.RaiseArm;
import org.frc4931.robot.command.roller.StopRoller;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Raises the arm and stops the roller.
 */
public class CompleteCapture extends CommandGroup{

	/**
	 * Constructs the command group.
	 */
	public CompleteCapture() {
//		addParallel(new RollIn());
		addSequential(new RaiseArm());
		addSequential(new StopRoller());
	}
}
