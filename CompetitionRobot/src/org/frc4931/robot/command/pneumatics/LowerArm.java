package org.frc4931.robot.command.pneumatics;

import org.frc4931.robot.CompetitionRobot;
import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class LowerArm extends Command{

	public LowerArm() {
		requires(Subsystems.arm);
	}

	protected void end() {
		CompetitionRobot.output("Arm Lowered");
	}

	protected void execute() {
		Subsystems.arm.lower();
	}

	protected void initialize() {
	}

	protected void interrupted() {
		end();
	}

	protected boolean isFinished() {
		return Subsystems.arm.isDown();
	}

}
