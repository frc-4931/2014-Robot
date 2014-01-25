package org.frc4931.robot.command;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class TankDriveWithJoysticks extends Command{

	public TankDriveWithJoysticks() {
		requires(Subsystems.driveTrain);
	}

	protected void initialize() {
	}

	protected void execute() {
		double scaledRightSpeed = Subsystems.joystick.getPitch()*Subsystems.joystick.getNormalThrottle();
		double scaledLeftSpeed = Subsystems.secondJoystick.getPitch()*Subsystems.joystick.getNormalThrottle();
		Subsystems.driveTrain.tankDrive(scaledLeftSpeed, scaledRightSpeed);
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
