package org.frc4931.robot.command.drive;

import org.frc4931.robot.OperatorInterface;
import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.OneShotCommand;

/**
 * Updates CompetitionRobot.driveTrain with the current joystick values using the pitch of
 * joystick 1 for left speed and the pitch of joystick 2 for right speed.
 * 
 * @author Zach Anderson
 */
public class TankDriveWithJoysticks extends OneShotCommand{

	public TankDriveWithJoysticks() {
		requires(Subsystems.driveTrain);
	}

	protected void doExecute() {
		double scaledRightSpeed = OperatorInterface.joysticks[0].getPitch()*OperatorInterface.joysticks[0].getNormalThrottle();
		double scaledLeftSpeed = OperatorInterface.joysticks[1].getPitch()*OperatorInterface.joysticks[0].getNormalThrottle();
		Subsystems.driveTrain.tankDrive(scaledLeftSpeed, scaledRightSpeed);
	}
}
