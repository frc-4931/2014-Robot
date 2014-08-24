package org.frc4931.robot.command.net;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Opens both nets.
 * @author Zach Anderson
 *
 */
public class OpenNets extends CommandGroup {
	/**
	 * Constructs the command group.
	 */
	//TODO Update to use the new Nets class.
	public OpenNets() {
		requires(Subsystems.leftNet);
		requires(Subsystems.rightNet);
		addParallel(new Open(Subsystems.rightNet));
		addParallel(new AddCommandAfterDelay(new Open(Subsystems.leftNet), 0.5d));
	}
}
