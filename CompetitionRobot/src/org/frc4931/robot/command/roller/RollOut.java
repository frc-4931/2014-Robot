package org.frc4931.robot.command.roller;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class RollOut extends Command{

	public RollOut() {
		requires(Subsystems.roller);
	}

	protected void end() {
	}

	protected void execute() {
		Subsystems.roller.rollOut();
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
