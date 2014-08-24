package org.frc4931.robot.command.pneumatics;

import org.frc4931.robot.CompetitionRobot;
import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.CommandBase;

/**
 * Raises the roller arm.
 * @author Zach Anderson
 *
 */
public class RaiseArm extends CommandBase{
	public RaiseArm() {
		requires(Subsystems.arm);
	}

	protected void end() {
		CompetitionRobot.output("Arm Raised");
	}

	protected void execute() {
		Subsystems.arm.raise();
	}

	protected boolean isFinished() {
		return Subsystems.arm.isUp();
	}

}
