package org.frc4931.robot.command.groups;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.autonomous.FollowWall;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Drives straight forward and ejects the ball.
 * @author Zach Anderson
 *
 */
public class DriveAndScore extends CommandGroup{

	/**
	 * Constructs the command group.
	 **/
	public DriveAndScore() {
		requires(Subsystems.driveTrain);
		requires(Subsystems.ranger);
		requires(Subsystems.roller);
		//TODO Identify magic number.
		addSequential(new FollowWall(8));
		addSequential(new WaitCommand(0.5));
		addSequential(new EjectBall());
	}

}
