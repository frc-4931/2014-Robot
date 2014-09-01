package org.frc4931.robot.command.groups;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.SetState;
import org.frc4931.robot.command.TwoState.State;
import org.frc4931.robot.command.roller.RollIn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Lower the arm and start rolling the roller in.
 * @author Zach Anderson
 *
 */
public class PrepareForCapture extends CommandGroup{

	/**
	 * Constructs the command group.
	 */
	public PrepareForCapture() {
		addParallel(new RollIn());
		addParallel(new SetState(Subsystems.arm, State.DOWN));
	}

}
