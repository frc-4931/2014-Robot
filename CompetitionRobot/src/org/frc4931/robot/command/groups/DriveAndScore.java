package org.frc4931.robot.command.groups;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.autonomous.DriveToRange;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveAndScore extends CommandGroup{

	public DriveAndScore() {
		requires(Subsystems.driveTrain);
		requires(Subsystems.ranger);
//		requires(Subsystems.roller);
		addSequential(new DriveToRange(Subsystems.ranger, 0.12d));
		addSequential(new DriveToRange(Subsystems.ranger, 1.2d));
		addSequential(new DriveToRange(Subsystems.ranger, 0.12d));
		addSequential(new DriveToRange(Subsystems.ranger, 1.2d));
		addSequential(new DriveToRange(Subsystems.ranger, 0.12d));
		addSequential(new DriveToRange(Subsystems.ranger, 1.2d));
		addSequential(new DriveToRange(Subsystems.ranger, 0.12d));
		addSequential(new DriveToRange(Subsystems.ranger, 1.2d));
		addSequential(new DriveToRange(Subsystems.ranger, 0.12d));
		addSequential(new DriveToRange(Subsystems.ranger, 1.2d));
//		addSequential(new WaitCommand(0.5));
//		addSequential(new EjectBall());
	}

}
