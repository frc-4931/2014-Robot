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
	//@Override
	public void robotInit(){
		SmartDashboard.putData("Fader", new AnalogInput(1));
		//SmartDashboard.putData("Motor", new AnalogOutput(1));
		joystick = new LogitechPro(1);
		SmartDashboard.putData("Joystick", joystick);
		leftMotor = new Talon(1);
		rightMotor = new Talon(2);
		drive = new DriveTrain(leftMotor, rightMotor);
	}
	//@Override
	public void teleopPeriodic(){
		SmartDashboard.putData("Joystick", joystick);
		double rawDriveSpeed = joystick.getPitch();
		double rawTurnSpeed = joystick.getYaw();
		double scaledDriveSpeed = rawDriveSpeed*joystick.getNormalThrottle();
		double scaledTurnSpeed = rawTurnSpeed*joystick.getNormalThrottle();
		drive.arcadeDrive(scaledDriveSpeed,scaledTurnSpeed);
	}
}
