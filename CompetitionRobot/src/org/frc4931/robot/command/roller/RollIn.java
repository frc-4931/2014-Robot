package org.frc4931.robot.command.roller;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class RollIn extends Command{

	public RollIn() {
		requires(Subsystems.roller);
	}

	protected void end() {
	}

	protected void execute() {
		Subsystems.roller.rollIn();
	}

	protected void initialize() {
	}

	protected void interrupted() {
		end();
	}

	protected boolean isFinished() {
		return true;
	}

}
