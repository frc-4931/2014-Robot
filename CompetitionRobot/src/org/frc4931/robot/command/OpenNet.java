package org.frc4931.robot.command;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class OpenNet extends Command{
	public OpenNet() {
		requires(Subsystems.net);
	}

	protected void end() {
		System.out.println("Net Opened");
	}

	protected void execute() {
		Subsystems.net.open(1.0);
	}

	protected void initialize() {
	}

	protected void interrupted() {
		System.out.println("Net Opening Interupted");
		end();
	}

	protected boolean isFinished() {
		return Subsystems.net.isOpen();
	}

}
