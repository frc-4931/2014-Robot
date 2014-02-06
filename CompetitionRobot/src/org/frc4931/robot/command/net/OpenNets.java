package org.frc4931.robot.command.net;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.subsystems.Net;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class OpenNets extends CommandGroup {
	public OpenNets(double speed) {
		speed = Math.abs(speed);
		requires(Subsystems.leftNet);
		requires(Subsystems.rightNet);
		addParallel(new OpenLeftNet(speed));
		addSequential(new WaitCommand(Net.DELAY));
		addParallel(new OpenRightNet(speed));
	}
}
