package org.frc4931.robot.test;

import org.frc4931.robot.command.net.AddCommandAfterDelay;
import org.frc4931.robot.command.roller.RollIn;
import org.frc4931.robot.command.roller.RollOut;
import org.frc4931.robot.command.roller.StopRoller;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestRoller extends CommandGroup{

	public TestRoller() {
		addSequential(new Output("Comencing Roller Test"));
		addSequential(new RollIn());
		addSequential(new AddCommandAfterDelay(new StopRoller(),3.0d));
		addSequential(new AddCommandAfterDelay(new RollOut(),3.0d));
		addSequential(new AddCommandAfterDelay(new StopRoller(),3.0d));
		addSequential(new Output("Roller Test Complete"));
	}

}
