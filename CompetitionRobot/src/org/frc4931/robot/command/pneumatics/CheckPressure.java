package org.frc4931.robot.command.pneumatics;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class CheckPressure extends Command{
	private boolean done = false;
	public CheckPressure() {
		requires(Subsystems.compressor);
	}

	protected void end() {
	}

	protected void execute() {
		if(Subsystems.compressor.testPressure()){
			Scheduler.getInstance().add(new Pressurize());
			done = true;
		}
	}

	protected void initialize() {
	}

	protected void interrupted() {
		Subsystems.compressor.deactive();
	}

	protected boolean isFinished() {
		if(Subsystems.robot.isDisabled()){
			return true;
		}
		return done;
	}

}
