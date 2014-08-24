package org.frc4931.robot.command.net;

import org.frc4931.robot.command.CommandBase;
import org.frc4931.robot.subsystems.Net;

/**
 * Closes a given Net.
 * @author Zach Anderson
 *
 */
public class Close extends CommandBase{
	private final Net net;
	
	/**
	 * Constructs the command with the given net.
	 * @param net The net to close.
	 */
	public Close(Net net) {
		this.net = net;
	}

	protected void execute() {
		net.setSpeed(Net.CLOSE_SPEED);
	}

	protected boolean isFinished() {
		return net.isClosed();
	}

	/**
	 * Stops the net.
	 */
	protected void end() {
		net.stop();
		super.end();
	}

}
