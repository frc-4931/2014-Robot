package org.frc4931.robot.command.pneumatics;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class CheckPressure extends Command{

	public CheckPressure() {
		requires(Subsystems.compressor);
	}

	protected void end() {
		System.out.println("Compressor disengaged");
		Subsystems.compressor.deactive();
	}

	protected void execute() {
		if(Subsystems.compressor.testPressure()){
			Scheduler.getInstance().add(new Pressurize());
		}
	}

	protected void initialize() {
	}

	protected void interrupted() {
		end();
	}

	protected boolean isFinished() {
		return false;
	}

}
