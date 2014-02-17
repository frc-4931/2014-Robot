package org.frc4931.robot;

import org.frc4931.robot.command.autonomous.DriveBox;
import org.frc4931.robot.subsystems.Compressor;
import org.frc4931.robot.subsystems.DriveTrain;
import org.frc4931.robot.subsystems.Net;
import org.frc4931.robot.subsystems.Roller;
import org.frc4931.robot.subsystems.RollerArm;
import org.frc4931.robot.test.TestSubsystems;
import org.frc4931.zach.drive.Motor;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class CompetitionRobot extends IterativeRobot{
	/*
	 * Constant Convention:
	 * SUBSYSTEM_COMPONET_POSITION_DESCRIPTOR
	 */
	public static boolean DRIVE_ENABLED = true;
	public static boolean COMPRESSOR_ENABLED = true;
	public static boolean ROLLER_ENABLED = true;
	public static boolean ARM_ENABLED = true;
	public static boolean NETS_ENABLED = true;
	
	/*Drive Train Constants*/
	public static final int DRIVE_MOTOR_FRONTLEFT = 1;
	public static final int DRIVE_MOTOR_FRONTRIGHT = 3;
	public static final int DRIVE_MOTOR_BACKLEFT = 2;
	public static final int DRIVE_MOTOR_BACKRIGHT = 4;
	
	/*Compressor Constants*/
	public static final int COMPRESSOR_RELAY = 1;
	public static final int COMPRESSOR_PRESSURESWITCH = 1;
	
	/*Net Constants*/
	public static final int NET_MOTOR_LEFT = 6;
	public static final int NET_SWITCH_LEFT = 3;
	public static final int NET_MOTOR_RIGHT = 7;
	public static final int NET_SWITCH_RIGHT = 2;
	public static final int NET_PROX_LEFT = 8;
	public static final int NET_PROX_RIGHT = 7;
	
	/*Roller Constants*/
	public static final int ROLLER_MOTOR = 5;
	
	/*Solenoid Constants*/
	public static final int SOLENOID_LEFT_EXTEND = 1;
	public static final int SOLENOID_LEFT_RETRACT = 2;
	public static final int SOLENOID_RIGHT_EXTEND = 3;
	public static final int SOLENOID_RIGHT_RETRACT = 4;
	
//	public AnalogInput analog;
	public int driveMode = 0;
	public void robotInit(){
		Subsystems.robot = this;
		Subsystems.driveTrain = new DriveTrain(DRIVE_MOTOR_FRONTLEFT, DRIVE_MOTOR_BACKLEFT, DRIVE_MOTOR_FRONTRIGHT, DRIVE_MOTOR_BACKRIGHT, Motor.TALON_SPEED_CONTROLLER);
		Subsystems.compressor = new Compressor(COMPRESSOR_RELAY, COMPRESSOR_PRESSURESWITCH);
		Subsystems.leftNet = new Net(NET_MOTOR_LEFT, Motor.VICTOR_SPEED_CONTROLLER, NET_SWITCH_LEFT, NET_PROX_LEFT);
		Subsystems.rightNet = new Net(NET_MOTOR_RIGHT, Motor.VICTOR_SPEED_CONTROLLER, NET_SWITCH_RIGHT, NET_PROX_RIGHT);
		Subsystems.arm = new RollerArm(SOLENOID_LEFT_EXTEND,SOLENOID_LEFT_RETRACT,SOLENOID_RIGHT_EXTEND,SOLENOID_RIGHT_RETRACT);
		Subsystems.roller = new Roller(ROLLER_MOTOR, Motor.VICTOR_SPEED_CONTROLLER);

		Subsystems.compressor.init();
		OperatorInterface.init();
		
		Subsystems.leftNet.reset();
		Subsystems.rightNet.reset();
	}
//	public void teleopPeriodic(){
//		switch(driveMode){
//			case 0:
//				Scheduler.getInstance().add(new ArcadeDriveWithJoystick());
//				break;
//			case 1:
//				Scheduler.getInstance().add(new ModifiedDriveWithJoystick());
//				break;
//			case 2:
//				Scheduler.getInstance().add(new StrafeDriveWithJoystick());
//				break;
//			case 3:
//				Scheduler.getInstance().add(new TankDriveWithJoysticks());
//				break;
//			default:
//				break;
//		}
//		Subsystems.roller.roll();
//		Scheduler.getInstance().run();
//	}
	
	public void autonomousPeriodic(){
		Scheduler.getInstance().add(new DriveBox());
		Scheduler.getInstance().run();
	}
	
	public void teleopInit(){
		Scheduler.getInstance().add(new TestSubsystems());
	}
	
	public void teleopPeriodic(){
		Scheduler.getInstance().run();
	}
}
