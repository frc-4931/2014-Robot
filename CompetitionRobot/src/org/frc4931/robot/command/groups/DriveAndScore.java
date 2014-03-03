package org.frc4931.robot.command.groups;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.autonomous.DriveToRange;
import org.frc4931.robot.command.roller.RollOut;
import org.frc4931.robot.command.roller.StopRoller;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class DriveAndScore extends CommandGroup{

	public DriveAndScore() {
		requires(Subsystems.driveTrain);
		requires(Subsystems.ranger);
		addSequential(new DriveToRange(Subsystems.ranger.getSensor(), 3.0d));
		addSequential(new WaitCommand(0.5));
		addSequential(new RollOut());
		addSequential(new WaitCommand(1.5));
		addSequential(new StopRoller());
	}

}
