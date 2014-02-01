package org.frc4931.robot.subsystems;

import org.frc4931.zach.drive.Motor;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * A class that represents the physical drive train.
 * @author zach
 */
public class DriveTrain extends Subsystem{
	private final Motor rightFrontMotor;
	private final Motor leftFrontMotor;
	private final Motor rightRearMotor;
	private final Motor leftRearMotor;
	private final RobotDrive drive;
	
	/**
	 * Creates a new drive train with a given left and right motor.
	 * @param leftMotor the Motor object that represents the left motor.
	 * @param rightMotor the Motor object that represents the right motor.
	 */
	public DriveTrain(Motor leftMotor, Motor rightMotor){
		drive = new RobotDrive(leftMotor.getController(), rightMotor.getController());
		this.rightFrontMotor=rightMotor;
		this.leftFrontMotor=leftMotor;
		this.rightRearMotor=null;
		this.leftRearMotor=null;
	}
	
	public DriveTrain(Motor leftFrontMotor, Motor leftRearMotor, Motor rightFrontMotor, Motor rightRearMotor){
		drive = new RobotDrive(leftFrontMotor.getController(),leftRearMotor.getController(),rightFrontMotor.getController(),rightRearMotor.getController());
		this.rightFrontMotor=rightFrontMotor;
		this.leftFrontMotor=leftFrontMotor;
		this.rightRearMotor=rightRearMotor;
		this.leftRearMotor=leftRearMotor;
	}
	
	
	/**
	 * Drive this drive train in tank mode.
	 * @param leftSpeed the speed of the left motor from -1.0 to 1.0.
	 * @param rightSpeed the speed of the right motor from -1.0 to 1.0.
	 */
	public void tankDrive(double leftSpeed, double rightSpeed){
		drive.tankDrive(leftSpeed, rightSpeed);
	}
	
	/**
	 * Drive this drive train in arcade mode.
	 * @param driveSpeed the forward speed from -1.0 to 1.0.
	 * @param turnSpeed the turn rate from -1.0 to 1.0.
	 */
	public void arcadeDrive(double driveSpeed, double turnSpeed){
		drive.arcadeDrive(driveSpeed, turnSpeed);
	}
	
	public Motor getLeftMotor(){
		return leftFrontMotor;
	}
	
	public Motor getLeftRearMotor(){
		return leftRearMotor;
	}
	
	public Motor getRightMotor(){
		return rightFrontMotor;
	}
	
	public Motor getRightRearMotor(){
		return rightRearMotor;
	}
	
	public double getLeftSpeed(){
		return leftFrontMotor.getSpeed();
	}
	
	public double getLeftRearSpeed(){
		return leftRearMotor.getSpeed();
	}
	
	public double getRightSpeed(){
		return rightFrontMotor.getSpeed();
	}
	
	public double getRightRearSpeed(){
		return rightRearMotor.getSpeed();
	}

	protected void initDefaultCommand() {
	}
}
