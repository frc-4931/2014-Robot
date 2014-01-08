package org.frc4931.zach.diagnosticrobot.drive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

public class DriveTrain extends RobotDrive{
	public DriveTrain(SpeedController leftMotor, SpeedController rightMotor){
		super(leftMotor, rightMotor);
	}
}
