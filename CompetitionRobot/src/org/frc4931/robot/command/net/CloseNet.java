package org.frc4931.robot.command.net;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.subsystems.Net;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class CloseNet extends Command{
	public CloseNet() {
		requires(Subsystems.RightNet);
		requires(Subsystems.LeftNet);
	}

	protected void end() {
		System.out.println("Net Closed");
	}

	protected void execute() {
		Subsystems.RightNet.close(0.5);
		Timer.delay(Net.DELAY);
		Subsystems.LeftNet.close(0.5);
	}

	protected void initialize() {
	}

	protected void interrupted() {
		System.out.println("Net Closing Interupted");
		end();
	}

	protected boolean isFinished() {
		return Subsystems.RightNet.isClosed()&&Subsystems.LeftNet.isClosed();
	}

}
