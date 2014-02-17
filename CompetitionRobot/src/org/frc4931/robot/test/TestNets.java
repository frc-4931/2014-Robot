package org.frc4931.robot.test;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.limitednet.Close;
import org.frc4931.robot.command.limitednet.CloseNets;
import org.frc4931.robot.command.limitednet.Open;
import org.frc4931.robot.command.limitednet.OpenNets;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class TestNets extends CommandGroup{

	public TestNets() {
		addSequential(new Output("Starting Net Test"));
		addSequential(new CloseNets());
		addSequential(new Open(Subsystems.rightNet.motor));
		addSequential(new WaitCommand(3.0d));
		addSequential(new Open(Subsystems.leftNet.motor));
		addSequential(new WaitCommand(3.0d));
		addSequential(new Close(Subsystems.leftNet.motor));
		addSequential(new WaitCommand(3.0d));
		addSequential(new Close(Subsystems.rightNet.motor));
		addSequential(new WaitCommand(3.0d));
		addSequential(new OpenNets());
		addSequential(new WaitCommand(3.0d));
		addSequential(new CloseNets());
		addSequential(new Output("Net Test Complete"));
	}

}
