package org.frc4931.robot.command.limitednet;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.subsystems.Net;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CloseNets extends CommandGroup{
	public CloseNets(double speed) {
		speed = Math.abs(speed);
		requires(Subsystems.leftNet);
		requires(Subsystems.rightNet);
//		addParallel(new CloseLeftNet(speed));
//		addSequential(new WaitCommand(Net.DELAY));
//		addParallel(new CloseRightNet(speed));
		addParallel(new Close(Subsystems.leftNet.motor));
//		addSequential(new WaitCommand(Net.DELAY));
//		addParallel(new Close(Subsystems.rightNet.motor));
		addParallel(new AddCommandAfterDelay(new Close(Subsystems.rightNet.motor), Net.DELAY));
	}
}
