package org.frc4931.zach.diagnosticrobot;

import org.frc4931.zach.diagnosticrobot.control.FlightStick;
import org.frc4931.zach.diagnosticrobot.control.LogitechAttack;
import org.frc4931.zach.diagnosticrobot.control.LogitechPro;
import org.frc4931.zach.diagnosticrobot.drive.DriveTrain;
import org.frc4931.zach.diagnosticrobot.drive.Motor;
import org.frc4931.zach.diagnosticrobot.io.AnalogInput;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DiagnosticRobot extends IterativeRobot{
	public FlightStick joystick;
	public FlightStick attack;
	public Motor leftMotor;
	public Motor rightMotor;
	public DriveTrain drive;
	
	public int buttonCooldown = 0;
	public boolean strafe = false;
	public boolean modified = false;
	public boolean arcadeDrive = false;
	//@Override
	public void robotInit(){
		SmartDashboard.putData("Fader", new AnalogInput(1));
		joystick = new LogitechPro(1);
		attack = new LogitechAttack(2);
		SmartDashboard.putData("Joystick", joystick);
		leftMotor = new Motor(1,"Talon Speed Controller");
		rightMotor = new Motor(2, "Talon Speed Controller");
		drive = new DriveTrain(leftMotor, rightMotor);
		SmartDashboard.putData("Drive Train", drive);
	}
	//@Override
	public void teleopPeriodic(){
		SmartDashboard.putData("Joystick", joystick);
		SmartDashboard.putData("Drive Train", drive);
		buttonCooldown--;
		buttonCooldown=Math.max(0, buttonCooldown);
		if(joystick.getRawButton(1)&&buttonCooldown==0){
			arcadeDrive=!arcadeDrive;
			buttonCooldown = 10;
		}
		if(joystick.getRawButton(2)&&buttonCooldown==0){
			strafe=!strafe;
			buttonCooldown = 10;
		}
		if(joystick.getRawButton(3)&&buttonCooldown==0){
			modified = !modified;
			buttonCooldown = 10;
		}
		drive();
	}
	
	private void drive(){
		if(arcadeDrive){
			double rawDriveSpeed = 0;
			double rawTurnSpeed = 0;
			if(strafe){
				rawDriveSpeed = joystick.getRoll();
				rawTurnSpeed = joystick.getYaw()*-1;
			}else if(!strafe){
				rawDriveSpeed = joystick.getPitch();
				if(!modified){
					rawTurnSpeed = joystick.getRoll()*-1;
				}else if(modified){
					rawTurnSpeed = joystick.getYaw()*-1;
				}
			}
			double scaledDriveSpeed = rawDriveSpeed*joystick.getNormalThrottle();
			double scaledTurnSpeed = rawTurnSpeed*joystick.getNormalThrottle();
			drive.arcadeDrive(scaledDriveSpeed,scaledTurnSpeed);
		}else if(!arcadeDrive){
			double scaledRightSpeed = joystick.getPitch()*joystick.getNormalThrottle();
			double scaledLeftSpeed = attack.getPitch()*joystick.getNormalThrottle();
			drive.tankDrive(scaledLeftSpeed, scaledRightSpeed);
		}
	}
}
