package org.frc4931.robot.command.groups;

import org.frc4931.robot.command.autonomous.DriveAtSpeed;
import org.frc4931.robot.command.autonomous.TurnRelativeAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class DropAndDrive extends CommandGroup{

	public DropAndDrive() {
		addSequential(new EjectBall());
		addSequential(new WaitCommand(0.5));
		addSequential(new TurnRelativeAngle(-90));
		addSequential(new DriveAtSpeed(0.75));
	}

}
