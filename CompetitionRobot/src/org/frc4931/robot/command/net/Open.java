package org.frc4931.robot.command.net;

import org.frc4931.robot.subsystems.Net;

import edu.wpi.first.wpilibj.command.Command;

public class Open extends Command{
	private final Net net;
	public Open(Net net) {
		this.net = net;
	}

	protected void initialize() {
	}

	protected void execute() {
		net.setSpeed(-1*Net.OPEN_SPEED);
	}

	protected boolean isFinished() {
		return net.isOpen();
	}

	protected void end() {
		net.stop();
	}

	protected void interrupted() {
		end();
	}

}
