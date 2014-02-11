package org.frc4931.robot.command.pneumatics;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.roller.StopRoller;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class RaiseArm extends Command{
	public RaiseArm() {
		requires(Subsystems.arm);
	}

	protected void end() {
		System.out.println("Arm Raised");
	}

	protected void execute() {
		Subsystems.arm.raise();
	}

	protected void initialize() {
	}

	protected void interrupted() {
		end();
	}

	protected boolean isFinished() {
		if(Subsystems.arm.isUp()){
			Scheduler.getInstance().add(new StopRoller());
			return true;
		}
		return false;
	}

}
