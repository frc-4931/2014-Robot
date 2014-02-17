package org.frc4931.robot.command.limitednet;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.subsystems.Net;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CloseNets extends CommandGroup{
	public CloseNets() {
		requires(Subsystems.leftNet);
		requires(Subsystems.rightNet);	
		addParallel(new Close(Subsystems.leftNet.motor));
		addParallel(new AddCommandAfterDelay(new Close(Subsystems.rightNet.motor), Net.DELAY));
	}
}
