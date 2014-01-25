package org.frc4931.robot.command;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDriveWithJoystick extends Command{
	
	public ArcadeDriveWithJoystick(){
		requires(Subsystems.driveTrain);
	}

	protected void initialize() {
	}

	protected void execute() {
		double rawDriveSpeed = Subsystems.joystick.getPitch();
		double rawTurnSpeed = Subsystems.joystick.getRoll()*-1;
		double scaledDriveSpeed = rawDriveSpeed*Subsystems.joystick.getNormalThrottle();
		double scaledTurnSpeed = rawTurnSpeed*Subsystems.joystick.getNormalThrottle();
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
