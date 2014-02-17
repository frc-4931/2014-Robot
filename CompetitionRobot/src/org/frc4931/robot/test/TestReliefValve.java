package org.frc4931.robot.test;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class TestReliefValve extends Command{

	public TestReliefValve() {
	}

	protected void initialize() {
	}

	protected void execute() {
		Subsystems.compressor.activate();
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Subsystems.compressor.deactive();
	}

	protected void interrupted() {
		end();
	}

}
