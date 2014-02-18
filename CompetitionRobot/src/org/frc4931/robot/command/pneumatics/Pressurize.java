package org.frc4931.robot.command.pneumatics;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Pressurize extends Command{

	public Pressurize() {
		requires(Subsystems.compressor);
	}

	protected void initialize() {
	}

	protected void execute() {
		Subsystems.compressor.activate();
	}

	protected boolean isFinished() {
		return !Subsystems.compressor.testPressure();
	}

	protected void end() {
		Subsystems.compressor.deactive();
		Scheduler.getInstance().add(new CheckPressure());
	}

	protected void interrupted() {
		Subsystems.compressor.deactive();
	}

}
