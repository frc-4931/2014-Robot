package org.frc4931.robot.command.net;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.SetState;
import org.frc4931.robot.subsystems.Nets;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Opens both nets.
 * @author Zach Anderson
 * @deprecated Use {@link SetState}
 */
public class OpenNets extends CommandGroup {
	/**
	 * Constructs the command group.
	 */
	//TODO Update to use the new Nets class.
	public OpenNets() {
		requires(Subsystems.nets);
		addParallel(new Open(Subsystems.nets.rightNet));
		addParallel(new AddCommandAfterDelay(new Open(Subsystems.nets.leftNet), Nets.DELAY));
	}
}
