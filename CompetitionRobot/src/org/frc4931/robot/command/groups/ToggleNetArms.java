package org.frc4931.robot.command.groups;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.net.CloseNets;
import org.frc4931.robot.command.net.OpenNets;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class ToggleNetArms extends Command{

	public ToggleNetArms() {
		requires(Subsystems.leftNet);
		requires(Subsystems.rightNet);
	}

	protected void end() {
	}

	protected void execute() {
		if(Subsystems.leftNet.isOpen()&&Subsystems.rightNet.isOpen()){
			Scheduler.getInstance().add(new CloseNets());
		}
		if(Subsystems.leftNet.isClosed()&&Subsystems.rightNet.isClosed()){
			Scheduler.getInstance().add(new OpenNets());
		}
	}

	protected void initialize() {
	}

	protected void interrupted() {
		end();
	}

	protected boolean isFinished() {
		return true;
	}

}
