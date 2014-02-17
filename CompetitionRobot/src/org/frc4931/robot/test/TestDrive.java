package org.frc4931.robot.test;

import org.frc4931.robot.command.autonomous.DriveAtSpeed;
import org.frc4931.robot.command.autonomous.TurnAtSpeed;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class TestDrive extends CommandGroup{

	public TestDrive() {
		addSequential(new Output("Starting Drive Test"));
		addSequential(new DriveAtSpeed(1.0d),3.0d);
		addSequential(new WaitCommand(3.0d));
		addSequential(new DriveAtSpeed(-1.0d),3.0d);
		addSequential(new WaitCommand(3.0d));
		addSequential(new TurnAtSpeed(1.0d),3.0d);
		addSequential(new WaitCommand(3.0d));
		addSequential(new TurnAtSpeed(-1.0d),3.0d);
		addSequential(new Output("Drive Test Complete"));
	}

}
