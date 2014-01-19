package org.frc4931.zach.diagnosticrobot;

import org.frc4931.zach.diagnosticrobot.control.FlightStick;
import org.frc4931.zach.diagnosticrobot.control.LogitechPro;
import org.frc4931.zach.diagnosticrobot.drive.DriveTrain;
import org.frc4931.zach.diagnosticrobot.io.AnalogInput;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DiagnosticRobot extends IterativeRobot{
	public FlightStick joystick;
	public SpeedController leftMotor;
	public SpeedController rightMotor;
	public DriveTrain drive;
	
	public int buttonCooldown = 0;
	public boolean strafe = false;
	//@Override
	public void robotInit(){
		SmartDashboard.putData("Fader", new AnalogInput(1));
		joystick = new LogitechPro(1);
		SmartDashboard.putData("Joystick", joystick);
		leftMotor = new Talon(1);
		rightMotor = new Talon(2);
		drive = new DriveTrain(leftMotor, rightMotor);
	}
	//@Override
	public void teleopPeriodic(){
		SmartDashboard.putData("Joystick", joystick);
		buttonCooldown--;
		buttonCooldown=Math.max(0, buttonCooldown);
		if(joystick.getRawButton(2)&&buttonCooldown==0){
			strafe=!strafe;
			buttonCooldown = 10;
		}
		drive();
	}
	
	private void drive(){
		double rawDriveSpeed = 0;
		double rawTurnSpeed = 0;
		if(strafe){
			rawDriveSpeed = joystick.getRoll();
			rawTurnSpeed = joystick.getYaw()*-1;
		}else if(!strafe){
			rawDriveSpeed = joystick.getPitch();
			rawTurnSpeed = joystick.getRoll()*-1;
		}
		double scaledDriveSpeed = rawDriveSpeed*joystick.getNormalThrottle();
		double scaledTurnSpeed = rawTurnSpeed*joystick.getNormalThrottle();
		drive.arcadeDrive(scaledDriveSpeed,scaledTurnSpeed);
	}
}
