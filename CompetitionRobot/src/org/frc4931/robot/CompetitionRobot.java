package org.frc4931.robot;

import org.frc4931.robot.command.*;
import org.frc4931.robot.subsystems.DriveTrain;
import org.frc4931.zach.drive.Motor;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Scheduler;

public class CompetitionRobot extends IterativeRobot{
	public static final int LEFT_FRONT_PORT = 1;
	public static final int RIGHT_FRONT_PORT = 3;
	public static final int LEFT_REAR_PORT = 2;
	public static final int RIGHT_REAR_PORT = 4;
	
	public DigitalInput maxSwitch;
	public DigitalInput minSwitch;
	public Motor windowMotor;
	
	public DigitalInput pressureSwitch;
	
//	public Relay relay;
	
	public int driveMode = 0;
	public void robotInit(){
		maxSwitch = new DigitalInput(2);
		minSwitch = new DigitalInput(3);
//		pressureSwitch = new DigitalInput(4);
		windowMotor = new Motor(5, "Victor Speed Controller", maxSwitch, minSwitch);
		Subsystems.robot = this;
		Subsystems.driveTrain = new DriveTrain(new Motor(LEFT_FRONT_PORT, "Talon Speed Controller"), new Motor(LEFT_REAR_PORT, "Talon Speed Controller"), new Motor(RIGHT_FRONT_PORT, "Talon Speed Controller"), new Motor(RIGHT_REAR_PORT, "Talon Speed Controller"));
		OperatorInterface.init();
//		relay = new Relay(1);
	}
	public void teleopPeriodic(){
//		SmartDashboard.putBoolean("Digital Input", limitSwitch.get());
//		//System.out.println("Current Limit Switch Value: "+limitSwitch.get());
//		if(pressureSwitch.get()){
//			relay.set(Relay.Value.kForward);
//			System.out.println("Spinning");
//		}else{
//			relay.set(Relay.Value.kOff);
//			System.out.println("Not Spinning");
//		}
		OperatorInterface.joysticks[0].buttons[3].whenPressed(new MinimizeMotor(windowMotor,1));
		OperatorInterface.joysticks[0].buttons[4].whenPressed(new MaximizeMotor(windowMotor,1));
		switch(driveMode){
		case 0:
			Scheduler.getInstance().add(new ArcadeDriveWithJoystick());
			break;
		case 1:
			Scheduler.getInstance().add(new ModifiedDriveWithJoystick());
			break;
		case 2:
			Scheduler.getInstance().add(new StrafeDriveWithJoystick());
			break;
		case 3:
			Scheduler.getInstance().add(new TankDriveWithJoysticks());
			break;
		default:
			break;
		}
		Scheduler.getInstance().run();
	}
}
