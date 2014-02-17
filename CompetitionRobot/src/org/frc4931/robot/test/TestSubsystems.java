package org.frc4931.robot.test;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class TestSubsystems extends CommandGroup{

	public TestSubsystems() {
		addSequential(new TestNets());
		addSequential(new WaitCommand(3.0d));
		addSequential(new TestRoller());
		addSequential(new WaitCommand(3.0d));
		addSequential(new TestPneumatics());
		addSequential(new WaitCommand(3.0d));
		addSequential(new TestDrive());
	}

}
