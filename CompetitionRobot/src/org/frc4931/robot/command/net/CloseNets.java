package org.frc4931.robot.command.net;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.subsystems.Net;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Closes both nets.
 * @author Zach Anderson
 *
 */
public class CloseNets extends CommandGroup{
	/**
	 * Constructs the command group.
	 */
	//TODO Update to use the new Nets class.
	public CloseNets() {
		requires(Subsystems.leftNet);
		requires(Subsystems.rightNet);	
		addParallel(new Close(Subsystems.leftNet));
		addParallel(new AddCommandAfterDelay(new Close(Subsystems.rightNet), Net.DELAY));
	}
}
