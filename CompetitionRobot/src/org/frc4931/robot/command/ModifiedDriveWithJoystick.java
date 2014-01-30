package org.frc4931.robot.command;

import org.frc4931.robot.OperatorInterface;
import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives the robot in arcade mode using pitch for speed and yaw for turn.
 * @author zach
 *
 */
public class ModifiedDriveWithJoystick extends Command{
	
	public ModifiedDriveWithJoystick(){
		requires(Subsystems.driveTrain);
	}

	protected void initialize() {
	}

	protected void execute() {
		double rawDriveSpeed = OperatorInterface.joysticks[0].getPitch();
		double rawTurnSpeed = OperatorInterface.joysticks[0].getYaw()*-1;
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
