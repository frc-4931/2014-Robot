package org.frc4931.robot;

import org.frc4931.robot.command.ArcadeDriveWithJoystick;
import org.frc4931.robot.subsystems.DriveTrain;
import org.frc4931.zach.control.LogitechAttack;
import org.frc4931.zach.control.LogitechPro;
import org.frc4931.zach.drive.Motor;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class CompetitionRobot extends IterativeRobot{
	public void robotInit(){
		Subsystems.driveTrain = new DriveTrain(new Motor(1, "Jaguar Speed Controller"), new Motor(2, "Jaguar Speed Controller"));
		Subsystems.joystick = new LogitechPro(1);
		Subsystems.secondJoystick = new LogitechAttack(2);
	}
	public void teleopPeriodic(){
		Scheduler.getInstance().run();
		new ArcadeDriveWithJoystick();
	}
}
