package org.frc4931.robot.test;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.SetState;
import org.frc4931.robot.command.TwoState.State;
import org.frc4931.robot.subsystems.Nets;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class TestNets extends CommandGroup{

	public TestNets() {
		addSequential(new Output("Starting Net Test"));
		addSequential(new SetState(Subsystems.nets, State.CLOSED, Nets.CLOSE_SPEED));
		
		addSequential(new SetState(Subsystems.nets.leftNet, State.OPEN, Nets.OPEN_SPEED));
		
		addSequential(new WaitCommand(3.0d));
		addSequential(new SetState(Subsystems.nets.rightNet, State.OPEN, Nets.OPEN_SPEED));
		
		addSequential(new WaitCommand(3.0d));
		addSequential(new SetState(Subsystems.nets.rightNet, State.CLOSED, Nets.CLOSE_SPEED));
		
		addSequential(new WaitCommand(3.0d));
		addSequential(new SetState(Subsystems.nets.leftNet, State.CLOSED, Nets.CLOSE_SPEED));
		
		addSequential(new WaitCommand(3.0d));
		addSequential(new SetState(Subsystems.nets, State.OPEN, Nets.OPEN_SPEED));
		
		addSequential(new WaitCommand(3.0d));
		addSequential(new SetState(Subsystems.nets, State.CLOSED, Nets.CLOSE_SPEED));
		
		addSequential(new Output("Net Test Complete"));
	}

}
