package org.frc4931.robot.command.autonomous;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveBox extends CommandGroup{

	public DriveBox() {
		requires(Subsystems.driveTrain);
		addSequential(new DriveAtSpeed(0.7d),1.0d);
		addSequential(new TurnRelativeAngle(90));
		addSequential(new DriveAtSpeed(0.7d),1.0d);
		addSequential(new TurnRelativeAngle(90));
		addSequential(new DriveAtSpeed(0.7d),1.0d);
		addSequential(new TurnRelativeAngle(90));
		addSequential(new DriveAtSpeed(0.7d),1.0d);
		addSequential(new TurnRelativeAngle(90));
	}

}
