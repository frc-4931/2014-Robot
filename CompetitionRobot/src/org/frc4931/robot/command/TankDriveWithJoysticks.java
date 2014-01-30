package org.frc4931.robot.command;

import org.frc4931.robot.OperatorInterface;
import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives the robot in tank mode using joystick 1 for left speed and joystick 2 for right speed.
 * @author zach
 *
 */
public class TankDriveWithJoysticks extends Command{

	public TankDriveWithJoysticks() {
		requires(Subsystems.driveTrain);
	}

	protected void initialize() {
	}

	protected void execute() {
		double scaledRightSpeed = OperatorInterface.joysticks[0].getPitch()*OperatorInterface.joysticks[0].getNormalThrottle();
		double scaledLeftSpeed = OperatorInterface.joysticks[1].getPitch()*OperatorInterface.joysticks[0].getNormalThrottle();
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
