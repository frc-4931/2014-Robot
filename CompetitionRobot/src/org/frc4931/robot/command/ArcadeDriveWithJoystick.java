package org.frc4931.robot.command;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.OperatorInterface;

import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDriveWithJoystick extends Command{
	
	public ArcadeDriveWithJoystick(){
		requires(Subsystems.driveTrain);
	}

	protected void initialize() {
	}

	protected void execute() {
		double rawDriveSpeed = OperatorInterface.joysticks[0].getPitch();
		double rawTurnSpeed = OperatorInterface.joysticks[0].getRoll()*-1;
		double scaledDriveSpeed = rawDriveSpeed*OperatorInterface.joysticks[0].getNormalThrottle();
		double scaledTurnSpeed = rawTurnSpeed*OperatorInterface.joysticks[0].getNormalThrottle();
		Subsystems.driveTrain.arcadeDrive(scaledDriveSpeed,scaledTurnSpeed);
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
		end();
	}

}
