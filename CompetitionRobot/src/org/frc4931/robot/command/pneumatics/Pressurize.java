package org.frc4931.robot.command.pneumatics;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class Pressurize extends Command{

	public Pressurize() {
		requires(Subsystems.compressor);
		this.setInterruptible(false);
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
	}

	protected void interrupted() {
		System.out.println("INtERUPptED");
		Subsystems.compressor.deactive();
	}

}
