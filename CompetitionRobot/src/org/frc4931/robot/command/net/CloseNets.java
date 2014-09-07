package org.frc4931.robot.command.net;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.SetState;
import org.frc4931.robot.subsystems.Nets;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Closes both nets.
 * @author Zach Anderson
 * @deprecated Use {@link SetState}
 */

public class CloseNets extends CommandGroup{
	/**
	 * Constructs the command group.
	 */
	//TODO Update to use the new Nets class.
	public CloseNets() {
		requires(Subsystems.nets);	
		addParallel(new Close(Subsystems.nets.leftNet));
		addParallel(new AddCommandAfterDelay(new Close(Subsystems.nets.rightNet), Nets.DELAY));
	}
}
