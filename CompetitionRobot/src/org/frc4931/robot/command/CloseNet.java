package org.frc4931.robot.command;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class CloseNet extends Command{
	public CloseNet() {
		requires(Subsystems.net);
	}

	protected void end() {
		System.out.println("Net Closed");
	}

	protected void execute() {
		Subsystems.net.close(0.5);
	}

	protected void initialize() {
	}

	protected void interrupted() {
		System.out.println("Net Closing Interupted");
		end();
	}

	protected boolean isFinished() {
		return Subsystems.net.isClosed();
	}

}
