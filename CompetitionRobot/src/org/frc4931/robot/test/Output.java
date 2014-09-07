package org.frc4931.robot.test;

import org.frc4931.robot.CompetitionRobot;

import edu.wpi.first.wpilibj.command.Command;

public class Output extends Command{
	private final String text;
	public Output(String text) {
		this.text = text;
	}

	protected void initialize() {
	}

	protected void execute() {
		CompetitionRobot.output(text);
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
		end();
	}

}
