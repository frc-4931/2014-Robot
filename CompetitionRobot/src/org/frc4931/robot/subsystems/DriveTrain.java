package org.frc4931.robot.subsystems;

import org.frc4931.robot.CompetitionRobot;
import org.frc4931.robot.command.drive.ArcadeDriveWithJoystick;
import org.frc4931.robot.command.drive.ModifiedDriveWithJoystick;
import org.frc4931.robot.command.drive.StrafeDriveWithJoystick;
import org.frc4931.robot.command.drive.TankDriveWithJoysticks;
import org.frc4931.zach.drive.Motor;
import org.frc4931.zach.utils.Transform;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A class that represents the physical drive train.
 * @author zach
 */
public class DriveTrain extends Subsystem{
	private final Motor rightFrontMotor;
	private final Motor leftFrontMotor;
	private final Motor rightRearMotor;
	private final Motor leftRearMotor;
	private final RobotDrive drive;
	
	/**The current forward drive speed.*/
	private double currentDrive = 0;
	/**The current turn speed*/
	private double currentTurn = 0;	
	
	/**
	 * Constructs a new drive train with the given values.
	 * @param leftFrontMotor The channel of the left front motor.
	 * @param leftRearMotor The channel of the left rear motor.
	 * @param rightFrontMotor The channel of the right front motor.
	 * @param rightRearMotor The channel of the right rear motor.
	 * @param controller The type of speed controller.
	 */
	public DriveTrain(int leftFrontMotor, int leftRearMotor, int rightFrontMotor, int rightRearMotor, Motor.SpeedControllerType type){
		this.leftFrontMotor = new Motor(leftFrontMotor,type);
		this.leftRearMotor = new Motor(leftRearMotor,type);
		this.rightFrontMotor = new Motor(rightFrontMotor,type);
		this.rightRearMotor = new Motor(rightRearMotor,type);
		drive = new RobotDrive(this.leftFrontMotor.getController(),this.leftRearMotor.getController(),this.rightFrontMotor.getController(),this.rightRearMotor.getController());
	}
	
	/**
	 * Constructs a new drive train with the given values.
	 * @param driveMotorLeft The channel of the left motor.
	 * @param driveMotorRight The channel of the right motor.
	 * @param type The type of the speed controller.
	 */
	public DriveTrain(int driveMotorLeft, int driveMotorRight, Motor.SpeedControllerType type) {
		this.leftFrontMotor = new Motor(driveMotorLeft,type);
		this.rightFrontMotor = new Motor(driveMotorRight,type);
		drive = new RobotDrive(this.leftFrontMotor.getController(),this.rightFrontMotor.getController());
		this.leftRearMotor = null;
		this.rightRearMotor = null;
	}

	/**
	 * @deprecated Use setDriveSpeed() and setTurnSpeed() instead.  
	 * Drive this drive train in tank mode.
	 * @param leftSpeed the speed of the left motor from -1.0 to 1.0.
	 * @param rightSpeed the speed of the right motor from -1.0 to 1.0.
	 */
	public void tankDrive(double leftSpeed, double rightSpeed){
		drive.tankDrive(leftSpeed, rightSpeed);
	}
	
	/**
	 * Drive this drive train in arcade mode.
	 * @param driveSpeed the forward speed from -1.0 to 1.0.
	 * @param turnSpeed the turn rate from -1.0 to 1.0.
	 */
	public void arcadeDrive(double driveSpeed, double turnSpeed){
		double delta = driveSpeed-currentDrive;
		if(delta<0){
			delta = (Math.min(delta*-1, getMaxDelta(currentDrive)))*-1;
		}else if(delta>0){
			delta = Math.min(delta, getMaxDelta(currentDrive));
		}else{
			
		}
		setDriveSpeed(currentDrive+delta);
		setTurnSpeed(turnSpeed);
	}
	
	/**
	 * Finds the maximum amount the drive speed of the robot can change in one iteration
	 * given an initial speed.
	 * @param speed The speed to find the max delta for
	 * @return The maximum amount the speed can change in one iteration.
	 */
	private double getMaxDelta(double speed){
		if(Math.abs(speed)<SmartDashboard.getNumber("Range 1")){
			return SmartDashboard.getNumber("Max Delta 1");
		}else if(Math.abs(speed)<SmartDashboard.getNumber("Range 2")){
			return SmartDashboard.getNumber("Max Delta 2");
		}else if(Math.abs(speed)<=SmartDashboard.getNumber("Range 3")){
			return SmartDashboard.getNumber("Max Delta 3");
		}else{
			return 0.0d;
		}
	}
	
	public void drive(int driveMode){
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
//		update();
	}
	
	/**
	 * Updates the speed of the motors.
	 */
	public void update(){
		//TODO Move joystick deadzones out of DriveTrain
		if(CompetitionRobot.DRIVE_ENABLED==true){
			int direction =1;
			if(currentDrive>SmartDashboard.getNumber("DriveDeadZone")){
				direction = 1;
			}else if(currentDrive<SmartDashboard.getNumber("DriveDeadZone")*-1){
				direction = -1;
			}else{
				direction = 0;
			}
			double minDriveSpeed = SmartDashboard.getNumber("MinDriveSpeed");
			double maxDriveSpeed = SmartDashboard.getNumber("MaxDriveSpeed");
			double mappedSpeed = Transform.map(0,1,minDriveSpeed,maxDriveSpeed,Math.abs(currentDrive));
			mappedSpeed = mappedSpeed*direction;
			
			int turnDirection =1;
			if(currentTurn>SmartDashboard.getNumber("TurnDeadZone")){
				turnDirection = 1;
			}else if(currentTurn<SmartDashboard.getNumber("TurnDeadZone")*-1){
				turnDirection = -1;
			}else{
				turnDirection = 0;
			}
			double minTurnSpeed = SmartDashboard.getNumber("MinTurnSpeed");
			double maxTurnSpeed = SmartDashboard.getNumber("MaxTurnSpeed");
			double mappedTurn = Transform.map(0,1,minTurnSpeed,maxTurnSpeed,Math.abs(currentTurn));
			mappedTurn = mappedTurn*turnDirection;
			
			drive.arcadeDrive(mappedSpeed,mappedTurn);
		}else{
			drive.arcadeDrive(0,0);
		}
	}
	
	/**
	 * Stops all of the motors.
	 */
	public void stop(){
		drive.arcadeDrive(0,0);
		currentDrive = 0.0;
		currentTurn = 0.0;
	}
	
	/**
	 * Sets the forward speed. Actual speed of the motors does not change until update()
	 * is called.
	 * @param speed The new speed from -1.0 to 1.0
	 */
	public void setDriveSpeed(double speed){
		speed = Math.max(speed, -1.0);
		speed = Math.min(speed, 1.0);
		currentDrive = speed;
	}
	
	/**
	 * Sets the turn speed. Actual speed of the motors does not change until update()
	 * is called.
	 * @param speed The new speed from -1.0 to 1.0
	 */
	public void setTurnSpeed(double speed){
		speed = Math.max(speed, -1.0);
		speed = Math.min(speed, 1.0);
		currentTurn = speed;
	}
	
	public Motor getLeftMotor(){
		return leftFrontMotor;
	}
	
	public Motor getLeftRearMotor(){
		return leftRearMotor;
	}
	
	public Motor getRightMotor(){
		return rightFrontMotor;
	}
	
	public Motor getRightRearMotor(){
		return rightRearMotor;
	}
	
	public double getLeftSpeed(){
		return leftFrontMotor.getSpeed();
	}
	
	public double getLeftRearSpeed(){
		return leftRearMotor.getSpeed();
	}
	
	public double getRightSpeed(){
		return rightFrontMotor.getSpeed();
	}
	
	public double getRightRearSpeed(){
		return rightRearMotor.getSpeed();
	}
	
	public void putToDashboard(){
		SmartDashboard.putNumber("Drive Speed", currentDrive);
		SmartDashboard.putNumber("Turn Speed", currentTurn*-1);
	}

	protected void initDefaultCommand() {
	}
}
