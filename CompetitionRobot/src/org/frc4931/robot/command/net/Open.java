package org.frc4931.robot.command.net;

import org.frc4931.robot.command.CommandBase;
import org.frc4931.robot.command.SetState;
import org.frc4931.robot.subsystems.Net;
import org.frc4931.robot.subsystems.Nets;

/**
 * Opens a given net.
 * @author Zach Anderson
 * @deprecated Use {@link SetState}
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
		net.open(Nets.OPEN_SPEED);
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
