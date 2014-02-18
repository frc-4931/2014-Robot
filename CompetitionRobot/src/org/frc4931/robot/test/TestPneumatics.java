package org.frc4931.robot.test;

import org.frc4931.robot.command.pneumatics.LowerArm;
import org.frc4931.robot.command.pneumatics.Pressurize;
import org.frc4931.robot.command.pneumatics.RaiseArm;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class TestPneumatics extends CommandGroup{

	public TestPneumatics() {
		addSequential(new Output("Starting Pneumatics Test"));
		addSequential(new Pressurize());
		addSequential(new WaitCommand(3.0d));
		addSequential(new LowerArm());
		addSequential(new WaitCommand(3.0d));
		addSequential(new RaiseArm());
		addSequential(new WaitCommand(3.0d));
		addSequential(new Pressurize());
		addSequential(new WaitCommand(1.0d));
		addSequential(new TestReliefValve(),10.0d);
		addSequential(new Output("Pneumatis Test Complete"));
	}

}
