package org.frc4931.robot.command.drive;

import org.frc4931.robot.OperatorInterface;
import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.OneShotCommand;

/**
 * Updates Subsystems.driveTrain with the current joystick values, using pitch for speed and
 * yaw for turn.
 * 
 * @author Zach Anderson
 */
public class ModifiedDriveWithJoystick extends OneShotCommand{
	
	public ModifiedDriveWithJoystick(){
		requires(Subsystems.driveTrain);
	}

	protected void execute() {
		double rawDriveSpeed = OperatorInterface.joysticks[0].getPitch();
		double rawTurnSpeed = OperatorInterface.joysticks[0].getYaw()*-1;
		double scaledDriveSpeed = rawDriveSpeed*OperatorInterface.joysticks[0].getNormalThrottle();
		double scaledTurnSpeed = rawTurnSpeed*OperatorInterface.joysticks[0].getNormalThrottle();
		Subsystems.driveTrain.arcadeDrive(scaledDriveSpeed,scaledTurnSpeed);
	}
}
