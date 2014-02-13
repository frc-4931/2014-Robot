package org.frc4931.robot.command.groups;

import org.frc4931.robot.command.pneumatics.LowerArm;
import org.frc4931.robot.command.roller.RollIn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PrepareForCapture extends CommandGroup{

	public PrepareForCapture() {
		addParallel(new RollIn());
		addParallel(new LowerArm());
	}

}
