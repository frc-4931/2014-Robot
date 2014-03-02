package org.frc4931.robot.command.net;

import org.frc4931.robot.subsystems.Net;

import edu.wpi.first.wpilibj.command.Command;

public class Close extends Command{
	private final Net net;
	public Close(Net net) {
		this.net = net;
	}

	protected void initialize() {
	}

	protected void execute() {
		net.setSpeed(Net.CLOSE_SPEED);
	}

	protected boolean isFinished() {
		return net.isClosed();
	}

	protected void end() {
		net.stop();
	}

	protected void interrupted() {
		end();
	}

}
