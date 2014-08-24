package org.frc4931.robot.command.drive;

import org.frc4931.robot.OperatorInterface;
import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.OneShotCommand;

/**
 * Updates CompetitionRobot.driveTrain with the current joystick value using pitch for speed
 * and roll for turn.
 * 
 * @author Zach Anderson
 */
public class ArcadeDriveWithJoystick extends OneShotCommand{
	
	public ArcadeDriveWithJoystick(){
		requires(Subsystems.driveTrain);
	}

	protected void execute() {
		double rawDriveSpeed = OperatorInterface.joysticks[0].getPitch();
		double rawTurnSpeed = OperatorInterface.joysticks[0].getRoll()*-1;
		double scaledDriveSpeed = rawDriveSpeed*OperatorInterface.joysticks[0].getNormalThrottle();
		double scaledTurnSpeed = rawTurnSpeed*OperatorInterface.joysticks[0].getNormalThrottle();
		Subsystems.driveTrain.arcadeDrive(scaledDriveSpeed,scaledTurnSpeed);
	}
}
