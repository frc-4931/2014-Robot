package org.frc4931.robot.command;


public abstract class OneShotCommand extends CommandBase{
	protected final boolean isFinished() {
		return true;
	}

}
