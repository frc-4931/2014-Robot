package org.frc4931.robot.command.limitednet;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OpenNets extends CommandGroup {
	public OpenNets() {
		requires(Subsystems.leftNet);
		requires(Subsystems.rightNet);
		addParallel(new Open(Subsystems.rightNet.motor));
		addParallel(new AddCommandAfterDelay(new Open(Subsystems.leftNet.motor), 0.5d));
	}
}
