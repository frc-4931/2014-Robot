package org.frc4931.robot.test;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.SetState;
import org.frc4931.robot.command.TwoState.State;
import org.frc4931.robot.command.pneumatics.Pressurize;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class TestPneumatics extends CommandGroup{

	public TestPneumatics() {
		addSequential(new Output("Starting Pneumatics Test"));
		addSequential(new Pressurize());
		addSequential(new WaitCommand(3.0d));
		addSequential(new SetState(Subsystems.arm, State.DOWN));
		addSequential(new WaitCommand(3.0d));
		addSequential(new SetState(Subsystems.arm, State.UP));
		addSequential(new WaitCommand(3.0d));
		addSequential(new Pressurize());
		addSequential(new WaitCommand(1.0d));
		addSequential(new TestReliefValve(),10.0d);
		addSequential(new Output("Pneumatics Test Complete"));
	}

}
