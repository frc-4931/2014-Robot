package org.frc4931.robot.command.roller;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class StopRoller extends Command{

	public StopRoller() {
		requires(Subsystems.roller);
	}

	protected void end() {
	}

	protected void execute() {
		Subsystems.roller.stop();
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
