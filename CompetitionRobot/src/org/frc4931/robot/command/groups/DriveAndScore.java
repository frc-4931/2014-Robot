package org.frc4931.robot.command.groups;

import org.frc4931.robot.CompetitionRobot;
import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.autonomous.AngularFollowWall;
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

		addSequential(new AngularFollowWall(CompetitionRobot.TARGET_DISTANCE_FROM_CENTER_OF_ROBOT_TO_WALL_IN_INCHES,
                CompetitionRobot.MINIMUM_RANGE_TO_GOAL_WALL_IN_INCHES, CompetitionRobot.ROBOT_FRAME_WIDTH_IN_INCHES,
                CompetitionRobot.ROBOT_FRAME_LENGTH_IN_INCHES, CompetitionRobot.CORRECTION_RANGE_FACTOR,
                CompetitionRobot.TURN_SPEED_SCALE_FACTOR));
		addSequential(new WaitCommand(0.5));
		addSequential(new EjectBall());
	}

}
