package org.frc4931.robot.command;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class StrafeDriveWithJoystick extends Command{

	public StrafeDriveWithJoystick() {
		requires(Subsystems.driveTrain);
	}

	protected void initialize() {
	}

	protected void execute() {
		double rawDriveSpeed = Subsystems.joystick.getRoll();
		double rawTurnSpeed = Subsystems.joystick.getYaw()*-1;
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
