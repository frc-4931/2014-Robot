package org.frc4931.zach.diagnosticrobot;

import org.frc4931.zach.diagnosticrobot.drive.DriveTrain;
import org.frc4931.zach.diagnosticrobot.io.AnalogInput;
import org.frc4931.zach.diagnosticrobot.io.FlightStick;

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
		joystick = new FlightStick(1);
		SmartDashboard.putData("Joystick", joystick);
		leftMotor = new Talon(1);
		rightMotor = new Talon(2);
		drive = new DriveTrain(leftMotor, rightMotor);
		//drive.setInvertedMotor(MotorType.kRearLeft, false);
		//drive.setInvertedMotor(MotorType.kRearRight, false);
	}
	//@Override
	public void teleopPeriodic(){
		SmartDashboard.putData("Joystick", joystick);
		System.out.println("Im a robot");
		double rawDriveSpeed = joystick.getAxis(FlightStick.PITCH_AXIS);
		double rawTurnSpeed = joystick.getAxis(FlightStick.YAW_AXIS)*-1;
		double scaledDriveSpeed = rawDriveSpeed*(1-joystick.getNormalizedAxis(FlightStick.THROTTLE_AXIS));
		double scaledTurnSpeed = rawTurnSpeed*(1-joystick.getNormalizedAxis(FlightStick.THROTTLE_AXIS));
		drive.arcadeDrive(scaledDriveSpeed,scaledTurnSpeed);
	}
}
