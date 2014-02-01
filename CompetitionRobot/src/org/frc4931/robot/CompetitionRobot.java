package org.frc4931.robot;

import org.frc4931.robot.command.*;
import org.frc4931.robot.subsystems.DriveTrain;
import org.frc4931.zach.drive.Motor;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class CompetitionRobot extends IterativeRobot{
	public static final int LEFT_FRONT_PORT = 1;
	public static final int RIGHT_FRONT_PORT = 3;
	public static final int LEFT_REAR_PORT = 2;
	public static final int RIGHT_REAR_PORT = 4;
	
	public int driveMode = 0;
	public void robotInit(){
		Subsystems.robot = this;
		Subsystems.driveTrain = new DriveTrain(new Motor(LEFT_FRONT_PORT, "Talon Speed Controller"), new Motor(LEFT_REAR_PORT, "Talon Speed Controller"), new Motor(RIGHT_FRONT_PORT, "Talon Speed Controller"), new Motor(RIGHT_REAR_PORT, "Talon Speed Controller"));
		OperatorInterface.init();
	}
	public void teleopPeriodic(){
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
