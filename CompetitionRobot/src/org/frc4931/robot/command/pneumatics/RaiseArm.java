package org.frc4931.robot.command.pneumatics;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

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
		return Subsystems.arm.isUp();
	}

}
