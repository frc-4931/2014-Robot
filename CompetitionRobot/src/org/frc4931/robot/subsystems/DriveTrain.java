package org.frc4931.robot.subsystems;

import org.frc4931.zach.drive.Motor;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * A class that represents the physical drive train.
 * @author zach
 */
public class DriveTrain extends Subsystem{
	private final Motor rightMotor;
	private final Motor leftMotor;
	private final RobotDrive drive;
	
	/**
	 * Creates a new drive train with a given left and right motor.
	 * @param leftMotor the Motor object that represents the left motor.
	 * @param rightMotor the Motor object that represents the right motor.
	 */
	public DriveTrain(Motor leftMotor, Motor rightMotor){
		drive = new RobotDrive(leftMotor.getController(), rightMotor.getController());
		this.rightMotor=rightMotor;
		this.leftMotor=leftMotor;
	}
	
	public void tankDrive(double leftSpeed, double rightSpeed){
		drive.tankDrive(leftSpeed, rightSpeed);
	}
	
	public void arcadeDrive(double driveSpeed, double turnSpeed){
		drive.arcadeDrive(driveSpeed, turnSpeed);
	}
	
	public Motor getLeftMotor(){
		return leftMotor;
	}
	
	public Motor getRightMotor(){
		return rightMotor;
	}
	
	public double getLeftSpeed(){
		return leftMotor.getSpeed();
	}
	
	public double getRightSpeed(){
		return rightMotor.getSpeed();
	}

	protected void initDefaultCommand() {
	}
}
