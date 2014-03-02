package org.frc4931.zach.diagnosticrobot;

import org.frc4931.zach.diagnosticrobot.control.FlightStick;
import org.frc4931.zach.diagnosticrobot.control.LogitechAttack;
import org.frc4931.zach.diagnosticrobot.control.LogitechPro;
import org.frc4931.zach.diagnosticrobot.drive.DriveTrain;
import org.frc4931.zach.diagnosticrobot.drive.Motor;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DiagnosticRobot extends IterativeRobot{
	public FlightStick joystick;
	public FlightStick attack;
	public Motor leftMotor;
	public Motor rightMotor;
	public DriveTrain drive;
	
	public int buttonCooldown = 0;
	public int driveMode = 0;
	
	AnalogChannel analog;
	//@Override
	public void robotInit(){
		analog = new AnalogChannel(4);
		joystick = new LogitechPro(1);
		attack = new LogitechAttack(2);
		leftMotor = new Motor(1,"Jaguar Speed Controller");
		rightMotor = new Motor(2, "Jaguar Speed Controller");
		drive = new DriveTrain(leftMotor, rightMotor);
	}
	//@Override
	public void teleopPeriodic(){
		System.out.println(analog.getValue());
		SmartDashboard.putData("Analog", analog);
		SmartDashboard.putData("Joystick", joystick);
		SmartDashboard.putData("Drive Train", drive);
		buttonCooldown--;
		buttonCooldown=Math.max(0, buttonCooldown);
		if(joystick.getRawButton(7)&&buttonCooldown==0){
			driveMode = 0;
			buttonCooldown = 10;
		}
		if(joystick.getRawButton(8)&&buttonCooldown==0){
			driveMode = 1;
			buttonCooldown = 10;
		}
		if(joystick.getRawButton(9)&&buttonCooldown==0){
			driveMode = 2;
			buttonCooldown = 10;
		}
		if(joystick.getRawButton(11)&&buttonCooldown==0){
			driveMode = 3;
			buttonCooldown = 10;
		}
		drive();
	}
	
	private void drive(){
		double rawDriveSpeed;
		double rawTurnSpeed;
		double scaledDriveSpeed;
		double scaledTurnSpeed;
		switch (driveMode) {
		case 0:	//Regular Arcade
			rawDriveSpeed = joystick.getPitch();
			rawTurnSpeed = joystick.getRoll()*-1;
			scaledDriveSpeed = rawDriveSpeed*joystick.getNormalThrottle();
			scaledTurnSpeed = rawTurnSpeed*joystick.getNormalThrottle();
			drive.arcadeDrive(scaledDriveSpeed,scaledTurnSpeed);
			break;
			
		case 1:	//Twist to turn
			rawDriveSpeed = joystick.getPitch();
			rawTurnSpeed = joystick.getYaw()*-1;
			scaledDriveSpeed = rawDriveSpeed*joystick.getNormalThrottle();
			scaledTurnSpeed = rawTurnSpeed*joystick.getNormalThrottle();
			drive.arcadeDrive(scaledDriveSpeed,scaledTurnSpeed);
			break;
			
		case 2: //Strafe
			rawDriveSpeed = joystick.getRoll();
			rawTurnSpeed = joystick.getYaw()*-1;
			scaledDriveSpeed = rawDriveSpeed*joystick.getNormalThrottle();
			scaledTurnSpeed = rawTurnSpeed*joystick.getNormalThrottle();
			drive.arcadeDrive(scaledDriveSpeed,scaledTurnSpeed);
			break;
			
		case 3:	//Tank Drive
			double scaledRightSpeed = joystick.getPitch()*joystick.getNormalThrottle();
			double scaledLeftSpeed = attack.getPitch()*joystick.getNormalThrottle();
			drive.tankDrive(scaledLeftSpeed, scaledRightSpeed);
			break;
			
		default:	//Invalid case
			break;
			
		}
	}
}
