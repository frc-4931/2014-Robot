package org.frc4931.robot.command.pneumatics;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.CommandBase;

import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * Continuously checks the pressure and schedules a pressurize if it is low.
 * @author Zach Anderson
 * @deprecated
 */
//TODO this should be a OneShotCommand that is created every iteration.
public class CheckPressure extends CommandBase{
	private boolean done = false;
	public CheckPressure() {
		requires(Subsystems.compressor);
	}

	protected void doExecute() {
		if(Subsystems.compressor.testPressure()){
			Scheduler.getInstance().add(new Pressurize());
			done = true;
		}
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
