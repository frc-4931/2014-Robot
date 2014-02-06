package org.frc4931.robot;

import org.frc4931.robot.command.drive.ArcadeDriveWithJoystick;
import org.frc4931.robot.command.drive.ModifiedDriveWithJoystick;
import org.frc4931.robot.command.drive.StrafeDriveWithJoystick;
import org.frc4931.robot.command.drive.TankDriveWithJoysticks;
import org.frc4931.robot.subsystems.Compressor;
import org.frc4931.robot.subsystems.DriveTrain;
import org.frc4931.robot.subsystems.Net;
import org.frc4931.zach.drive.Motor;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class CompetitionRobot extends IterativeRobot{
	/*
	 * Constant Convention:
	 * SUBSYSTEM_COMPONET_POSITION_DESCRIPTOR
	 */
	/*Drive Train Constants*/
	public static final int DRIVE_MOTOR_FRONTLEFT = 1;
	public static final int DRIVE_MOTOR_FRONTRIGHT = 3;
	public static final int DRIVE_MOTOR_BACKLEFT = 2;
	public static final int DRIVE_MOTOR_BACKRIGHT = 4;
	
	/*Compressor Constants*/
	public static final int COMPRESSOR_RELAY = 1;
	public static final int COMPRESSOR_PRESSURESWITCH = 5;
	
	/*Net Constants*/
	public static final int NET_MOTOR_LEFT = 6;
	public static final int NET_SWITCH_LEFT = 2;
	public static final int NET_MOTOR_RIGHT = 7;
	public static final int NET_SWITCH_RIGHT = 3;
	
	/*Roller Constants*/
	public static final int ROLLER_MOTOR = 5;
	
	
	public int driveMode = 0;
	public void robotInit(){
		Subsystems.robot = this;
		Subsystems.driveTrain = new DriveTrain(DRIVE_MOTOR_FRONTLEFT, DRIVE_MOTOR_BACKLEFT, DRIVE_MOTOR_FRONTRIGHT, DRIVE_MOTOR_BACKRIGHT, Motor.TALON_SPEED_CONTROLLER);
		Subsystems.compressor = new Compressor(COMPRESSOR_RELAY, COMPRESSOR_PRESSURESWITCH);
		Subsystems.LeftNet = new Net(NET_MOTOR_LEFT, Motor.VICTOR_SPEED_CONTROLLER, NET_SWITCH_LEFT, Net.LEFT);
		Subsystems.RightNet = new Net(NET_MOTOR_RIGHT, Motor.VICTOR_SPEED_CONTROLLER, NET_SWITCH_RIGHT, Net.RIGHT);
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
