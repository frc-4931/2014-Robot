package org.frc4931.robot.command.groups;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.SetState;
import org.frc4931.robot.command.TwoState.State;
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
		addSequential(new SetState(Subsystems.arm, State.UP));
		addSequential(new StopRoller());
	}
}
