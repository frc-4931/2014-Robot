package org.frc4931.robot.command.groups;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.roller.RollOut;
import org.frc4931.robot.command.roller.StopRoller;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class EjectBall extends CommandGroup{

	public EjectBall() {
		requires(Subsystems.roller);
		addSequential(new RollOut());
		addSequential(new WaitCommand(1.0d));
		addSequential(new StopRoller());
	}

}
