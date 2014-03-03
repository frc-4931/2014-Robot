package org.frc4931.robot.command.groups;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.autonomous.DriveToRange;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class DriveAndScore extends CommandGroup{

	public DriveAndScore() {
		requires(Subsystems.driveTrain);
		requires(Subsystems.ranger);
		requires(Subsystems.roller);
		addSequential(new DriveToRange(Subsystems.ranger.getSensor(), 3.0d));
		addSequential(new WaitCommand(0.5));
		addSequential(new EjectBall());
	}

}
