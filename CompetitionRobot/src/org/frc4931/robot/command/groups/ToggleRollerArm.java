package org.frc4931.robot.command.groups;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.pneumatics.LowerArm;
import org.frc4931.robot.command.pneumatics.RaiseArm;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class ToggleRollerArm extends Command{
	public ToggleRollerArm() {
		requires(Subsystems.arm);
	}

	protected void initialize() {
	}

	protected void execute() {
		if(Subsystems.arm.isUp()){
			Scheduler.getInstance().add(new LowerArm());
		}else if(Subsystems.arm.isDown()){
			Scheduler.getInstance().add(new RaiseArm());;
		}
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
		end();
	}

}
