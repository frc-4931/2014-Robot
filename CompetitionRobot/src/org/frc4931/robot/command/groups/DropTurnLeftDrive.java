package org.frc4931.robot.command.groups;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.autonomous.DriveToRange;
import org.frc4931.robot.command.autonomous.TurnRelativeAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class DropTurnLeftDrive extends CommandGroup{

	public DropTurnLeftDrive() {
		requires(Subsystems.driveTrain);
		requires(Subsystems.roller);
		requires(Subsystems.imu);
		addSequential(new EjectBall());
		addSequential(new WaitCommand(0.5));
		addSequential(new TurnRelativeAngle(-90));
		addSequential(new WaitCommand(0.5));
		addSequential(new DriveToRange(Subsystems.ranger, 0.5d));
	}

}
