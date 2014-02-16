package org.frc4931.robot.command.groups;

import org.frc4931.robot.command.pneumatics.RaiseArm;
import org.frc4931.robot.command.roller.RollIn;
import org.frc4931.robot.command.roller.StopRoller;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CompleteCapture extends CommandGroup{

	public CompleteCapture() {
		addParallel(new RollIn());
		addSequential(new RaiseArm());
		addSequential(new StopRoller());
	}

}
