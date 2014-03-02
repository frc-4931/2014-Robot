package org.frc4931.robot.command.groups;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.net.CloseNets;
import org.frc4931.robot.command.net.OpenNets;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ToggleNetArms extends CommandGroup{

	public ToggleNetArms() {
		if(Subsystems.leftNet.isOpen()&&Subsystems.rightNet.isOpen()){
			addSequential(new CloseNets());
		}else if(Subsystems.leftNet.isClosed()&&Subsystems.rightNet.isClosed()){
			addSequential(new OpenNets());
		}
	}

}
