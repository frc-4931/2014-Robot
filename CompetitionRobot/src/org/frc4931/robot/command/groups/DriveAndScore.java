package org.frc4931.robot.command.groups;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.autonomous.DriveAtSpeed;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveAndScore extends CommandGroup{

	public DriveAndScore() {
		requires(Subsystems.driveTrain);
		requires(Subsystems.ranger);
		requires(Subsystems.roller);
		addSequential(new DriveAtSpeed(0.5),3.0d);
		addSequential(new DriveAtSpeed(0.0),2.0d);
//		addSequential(new DriveToRange(Subsystems.ranger, 0.3d));
//		addSequential(new WaitCommand(0.5));
//		addSequential(new EjectBall());
	}

}
