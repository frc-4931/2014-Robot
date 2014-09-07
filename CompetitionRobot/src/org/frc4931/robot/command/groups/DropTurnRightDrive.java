package org.frc4931.robot.command.groups;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.autonomous.DriveToRange;
import org.frc4931.robot.command.autonomous.TurnRelativeAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Ejects the ball, turns right 90 degrees, and drives forward.
 * @author Zach Anderson
 *
 */
public class DropTurnRightDrive extends CommandGroup{

	/**
	 * Constructs the command group.
	 */
	public DropTurnRightDrive() {
		requires(Subsystems.driveTrain);
		requires(Subsystems.roller);
		requires(Subsystems.imu);
		addSequential(new EjectBall());
		addSequential(new WaitCommand(0.5));
		addSequential(new TurnRelativeAngle(90));
		addSequential(new WaitCommand(0.5));
		//TODO Identify magic number.
		addSequential(new DriveToRange(Subsystems.ranger, 0.5d));
	}

}
