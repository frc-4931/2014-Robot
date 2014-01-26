package org.frc4931.robot;

import org.frc4931.robot.command.*;
import org.frc4931.robot.subsystems.DriveTrain;
import org.frc4931.zach.drive.Motor;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class CompetitionRobot extends IterativeRobot{
	public int driveMode = 0;
	public void robotInit(){
		Subsystems.robot = this;
		Subsystems.driveTrain = new DriveTrain(new Motor(1, "Jaguar Speed Controller"), new Motor(2, "Jaguar Speed Controller"));
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
