package org.frc4931.robot.command.net;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OpenNets extends CommandGroup {
	public OpenNets(double speed) {
		speed = Math.abs(speed);
		requires(Subsystems.leftNet);
		requires(Subsystems.rightNet);
		addParallel(new OpenLeftNet(speed));
//		addSequential(new WaitCommand(Net.DELAY));
		addParallel(new OpenRightNet(speed));
	}
}
