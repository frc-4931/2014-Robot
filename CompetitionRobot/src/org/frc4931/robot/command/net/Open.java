package org.frc4931.robot.command.net;

import org.frc4931.robot.command.CommandBase;
import org.frc4931.robot.subsystems.Net;

/**
 * Opens a given net.
 * @author Zach Anderson
 *
 */
public class Open extends CommandBase{
	private final Net net;
	
	/**
	 * Constructs the command with the given net.
	 * @param net The net to Open.
	 */
	public Open(Net net) {
		this.net = net;
	}

	protected void execute() {
		net.setSpeed(-1*Net.OPEN_SPEED);
	}

	protected boolean isFinished() {
		return net.isOpen();
	}

	/**
	 * Stops the net.
	 */
	protected void end() {
		net.stop();
		super.end();
	}
}
