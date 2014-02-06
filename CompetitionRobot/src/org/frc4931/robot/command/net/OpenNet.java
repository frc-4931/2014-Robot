package org.frc4931.robot.command.net;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.subsystems.Net;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class OpenNet extends Command{
	public OpenNet() {
		requires(Subsystems.LeftNet);
		requires(Subsystems.RightNet);
	}

	protected void end() {
		System.out.println("Net Opened");
	}

	protected void execute() {
		Subsystems.RightNet.open(0.5);
		Timer.delay(Net.DELAY);
		Subsystems.LeftNet.open(0.5);
	}

	protected void initialize() {
	}

	protected void interrupted() {
		System.out.println("Net Opening Interupted");
		end();
	}

	protected boolean isFinished() {
		return Subsystems.RightNet.isOpen()&&Subsystems.LeftNet.isOpen();
	}

}
