package org.frc4931.robot;

import org.frc4931.robot.command.drive.PIDDriveInterface;
import org.frc4931.robot.command.groups.DriveAndScore;
import org.frc4931.robot.command.net.Close;
import org.frc4931.robot.command.net.Open;
import org.frc4931.robot.command.pneumatics.LowerArm;
import org.frc4931.robot.command.pneumatics.RaiseArm;
import org.frc4931.robot.command.roller.RollIn;
import org.frc4931.robot.command.roller.RollOut;
import org.frc4931.robot.command.roller.StopRoller;
import org.frc4931.robot.subsystems.Compressor;
import org.frc4931.robot.subsystems.DriveTrain;
import org.frc4931.robot.subsystems.IMU;
import org.frc4931.robot.subsystems.Net;
import org.frc4931.robot.subsystems.Ranger;
import org.frc4931.robot.subsystems.Roller;
import org.frc4931.robot.subsystems.RollerArm;
import org.frc4931.zach.drive.Motor;
import org.frc4931.zach.io.Accel;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CompetitionRobot extends IterativeRobot{
	/*
	 * Constant Convention:
	 * SUBSYSTEM_COMPONET_POSITION_DESCRIPTOR
	 */
	public static boolean DRIVE_ENABLED = true;
	public static boolean COMPRESSOR_ENABLED = false;
	public static boolean ROLLER_ENABLED = false;
	public static boolean ARM_ENABLED = false;
	public static boolean NETS_ENABLED = false;
	
	/*Drive Train Constants*/
	public static final int DRIVE_MOTOR_FRONTLEFT = 1;
	public static final int DRIVE_MOTOR_FRONTRIGHT = 3;
	public static final int DRIVE_MOTOR_BACKLEFT = 2;
	public static final int DRIVE_MOTOR_BACKRIGHT = 4;
	
	/*Roller Constants*/
	public static final int ROLLER_MOTOR = 5;
	
	/*Net Constants*/
	public static final int NET_MOTOR_LEFT = 6;
	public static final int NET_SWITCH_LEFT = 3;
	public static final int NET_MOTOR_RIGHT = 7;
	public static final int NET_SWITCH_RIGHT = 2;
	public static final int NET_PROX_LEFT = 8;
	public static final int NET_PROX_RIGHT = 7;
	
	/*Compressor Constants*/
	public static final int COMPRESSOR_RELAY = 1;
	public static final int COMPRESSOR_PRESSURESWITCH = 1;
	
	/*Solenoid Constants*/
	public static final int SOLENOID_LEFT_EXTEND = 1;
	public static final int SOLENOID_LEFT_RETRACT = 2;
	public static final int SOLENOID_RIGHT_EXTEND = 3;
	public static final int SOLENOID_RIGHT_RETRACT = 4;
	
	public static final int GYRO_CHANNEL = 1;
	public static final int RANGER_CHANNEL = 2;
	
	public static final String VISION_RASPI_IP = "10.99.31.3";
	
	public Gyro gyro;
	public Accel accel;
	public int driveMode = 1;
	public void robotInit(){
		Subsystems.robot = this;
//		Subsystems.driveTrain = new DriveTrain(DRIVE_MOTOR_FRONTLEFT, DRIVE_MOTOR_BACKLEFT, DRIVE_MOTOR_FRONTRIGHT, DRIVE_MOTOR_BACKRIGHT, Motor.TALON_SPEED_CONTROLLER);
		Subsystems.driveTrain = new DriveTrain(1, 2, Motor.JAGUAR_SPEED_CONTROLLER);
		Subsystems.compressor = new Compressor(COMPRESSOR_RELAY, COMPRESSOR_PRESSURESWITCH);
		Subsystems.leftNet = new Net(NET_MOTOR_LEFT, Motor.VICTOR_SPEED_CONTROLLER, NET_SWITCH_LEFT, NET_PROX_LEFT);
		Subsystems.rightNet = new Net(NET_MOTOR_RIGHT, Motor.VICTOR_SPEED_CONTROLLER, NET_SWITCH_RIGHT, NET_PROX_RIGHT);
		Subsystems.arm = new RollerArm(SOLENOID_LEFT_EXTEND,SOLENOID_LEFT_RETRACT,SOLENOID_RIGHT_EXTEND,SOLENOID_RIGHT_RETRACT);
		Subsystems.roller = new Roller(ROLLER_MOTOR, Motor.VICTOR_SPEED_CONTROLLER);
		Subsystems.ranger = new Ranger(RANGER_CHANNEL);
		Subsystems.imu = new IMU(GYRO_CHANNEL);
		Subsystems.pid = new PIDController(1,0,0,Subsystems.ranger,new PIDDriveInterface());
		
		Subsystems.compressor.init();
		Subsystems.imu.reset();
		OperatorInterface.init();
		
		smartDashboardInit();
		
		//TODO Have drivers refine these values and make them constants in DriveTrain.
		SmartDashboard.putNumber("Range 1", 0.4);
		SmartDashboard.putNumber("Range 2", 0.8);
		SmartDashboard.putNumber("Range 3", 1.0);
		
		SmartDashboard.putNumber("Max Delta 1", 1.0);
		SmartDashboard.putNumber("Max Delta 2", 0.1);
		SmartDashboard.putNumber("Max Delta 3", 0.01);
	}
	
	public void smartDashboardInit(){
		/*Operator Interface Booleans*/
		SmartDashboard.putBoolean("Pressure Switch", false);
		SmartDashboard.putBoolean("Verbose", true);
		SmartDashboard.putNumber("MinDriveSpeed", 0.35);
		SmartDashboard.putNumber("MaxDriveSpeed", 1.0);
		SmartDashboard.putNumber("DriveDeadZone", 0.06);
		SmartDashboard.putData("PID", Subsystems.pid);
		
		/*Net Override Commands*/
		SmartDashboard.putData("Close Left Net",new Close(Subsystems.leftNet));
		SmartDashboard.putData("Close Right Net",new Close(Subsystems.rightNet));
		SmartDashboard.putData("Open Left Net",new Open(Subsystems.leftNet));
		SmartDashboard.putData("Open Right Net",new Open(Subsystems.rightNet));

		/*Roller Arm Override Commands*/
		SmartDashboard.putData("Lower Roller Arm", new LowerArm());
		SmartDashboard.putData("Raise Roller Arm", new RaiseArm());
	
		/*Roller Override Commands*/
		SmartDashboard.putData("Roll Arm In", new RollIn());
		SmartDashboard.putData("Roll Arm Out", new RollOut());
		SmartDashboard.putData("Stop Roller", new StopRoller());
	}
	
	public void updateSmartDashboard(){
		/*Put Sensor Values*/
//		SmartDashboard.putNumber("Range Sensor",analog.getValue()/61.0d);
		
		/*Put Subsystems*/
		Subsystems.driveTrain.putToDashboard();
		Subsystems.compressor.putToDashboard();
		Subsystems.roller.putToDashboard();
		Subsystems.arm.putToDashboard();
		Subsystems.ranger.putToDashboard();
		Subsystems.imu.putToDashboard();
		
		/*Put Subsystem Values*/
		SmartDashboard.putBoolean("Left Net Status", Subsystems.leftNet.dashboardOpen);
		SmartDashboard.putBoolean("Right Net Status", Subsystems.rightNet.dashboardOpen);
	}
	
	public void teleopPeriodic(){
		Subsystems.driveTrain.drive(driveMode);
		Subsystems.driveTrain.update();
		Subsystems.roller.roll();
		updateSmartDashboard();

		Scheduler.getInstance().run();
	}
	
	public void autonomousInit(){
		Scheduler.getInstance().add(new DriveAndScore());
		//MIN TURN SPEED 0.350
//		Scheduler.getInstance().add(new DriveBox());
//		Scheduler.getInstance().add(new DriveToRange(Subsystems.ranger,0.12d));
	}
	
	public void autonomousPeriodic(){
		Scheduler.getInstance().run();
		Subsystems.driveTrain.update();
		updateSmartDashboard();
	}
	
	public static void output(String string){
//		if(SmartDashboard.getBoolean("Verbose")){
			System.out.println(string);
//		}
	}
	
	public static void printToUserConsole(String string){
		
	}
}
