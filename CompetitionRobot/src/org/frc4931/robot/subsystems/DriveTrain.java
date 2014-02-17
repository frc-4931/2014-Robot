package org.frc4931.robot.subsystems;

import org.frc4931.zach.drive.Motor;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * A class that represents the physical drive train.
 * @author zach
 */
public class DriveTrain extends Subsystem{
	public static final double DRIVE_ACCEL = 0.1/1000; //Speed Units per milliseconds per milliseconds
	private static final double ROT_ACCEL = 0.1/1000; //Turn Speed Units per milliseconds per milliseconds
	private final Motor rightFrontMotor;
	private final Motor leftFrontMotor;
	private final Motor rightRearMotor;
	private final Motor leftRearMotor;
	private final RobotDrive drive;
	
	private double initialDrive = 0;
	private double initialTurn = 0;
	private double targetDrive = 0;
	private double targetTurn = 0;
	private double currentDrive = 0;
	private double currentTurn = 0;
	private double driveAccel = 0;
	private double turnAccel = 0;
	private long timeDriveSet;
	private long timeTurnSet;
	
	/**
	 * Creates a new drive train with a given left and right motor.
	 * @param leftMotor the Motor object that represents the left motor.
	 * @param rightMotor the Motor object that represents the right motor.
	 */
	public DriveTrain(Motor leftMotor, Motor rightMotor){
		drive = new RobotDrive(leftMotor.getController(), rightMotor.getController());
		this.rightFrontMotor=rightMotor;
		this.leftFrontMotor=leftMotor;
		this.rightRearMotor=null;
		this.leftRearMotor=null;
	}
	
	public DriveTrain(Motor leftFrontMotor, Motor leftRearMotor, Motor rightFrontMotor, Motor rightRearMotor){
		drive = new RobotDrive(leftFrontMotor.getController(),leftRearMotor.getController(),rightFrontMotor.getController(),rightRearMotor.getController());
		this.rightFrontMotor=rightFrontMotor;
		this.leftFrontMotor=leftFrontMotor;
		this.rightRearMotor=rightRearMotor;
		this.leftRearMotor=leftRearMotor;
	}
	
	public DriveTrain(int leftFrontMotor, int leftRearMotor, int rightFrontMotor, int rightRearMotor, int controller){
		this.leftFrontMotor = new Motor(leftFrontMotor,controller);
		this.leftRearMotor = new Motor(leftRearMotor,controller);
		this.rightFrontMotor = new Motor(rightFrontMotor,controller);
		this.rightRearMotor = new Motor(rightRearMotor,controller);
		drive = new RobotDrive(this.leftFrontMotor.getController(),this.leftRearMotor.getController(),this.rightFrontMotor.getController(),this.rightRearMotor.getController());
	}
	
	
	/**
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
		if(driveSpeed!=targetDrive){
			targetDrive = driveSpeed;
			initialDrive = currentDrive;
			timeDriveSet = System.currentTimeMillis();
			if(targetDrive<initialDrive){
				driveAccel = DRIVE_ACCEL*-1;
			}else if(targetDrive<initialDrive){
				driveAccel = DRIVE_ACCEL;
			}
		}
		long timeSinceDriveSet = System.currentTimeMillis()-timeDriveSet;
		if(driveAccel>0&&currentDrive>=targetDrive){
			currentDrive = targetDrive;
		}else if(driveAccel<0&&currentDrive<=targetDrive){
			currentDrive = initialDrive+(driveAccel*timeSinceDriveSet);
		}
		
		if(turnSpeed!=targetTurn){
			targetTurn = turnSpeed;
			initialTurn = currentTurn;
			timeTurnSet = System.currentTimeMillis();
			if(targetTurn<initialTurn){
				turnAccel = ROT_ACCEL*-1;
			}else if(targetTurn<initialTurn){
				turnAccel = ROT_ACCEL;
			}
		}
		long timeSinceTurnSet = System.currentTimeMillis()-timeTurnSet;
		if(turnAccel>0&&currentTurn>=targetTurn){
			currentTurn = targetTurn;
		}else if(turnAccel<0&&currentTurn<=targetTurn){
			currentTurn = initialTurn+(turnAccel*timeSinceTurnSet);
		}
		
		drive.arcadeDrive(currentDrive,currentTurn);
	}
	
	public void setDriveSpeed(double speed){
		if(speed!=targetDrive){
			targetDrive = speed;
			initialDrive = currentDrive;
			timeDriveSet = System.currentTimeMillis();
		}
	}
	
	public void setTurnSpeed(double speed){
		if(speed!=targetTurn){
			targetTurn = speed;
			initialTurn = currentTurn;
			timeTurnSet = System.currentTimeMillis();
		}
	}
	
	//Bypasses acceleration curve
	public void directArcadeDrive(double driveSpeed, double turnSpeed){
		drive.arcadeDrive(driveSpeed,turnSpeed);
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

	protected void initDefaultCommand() {
	}
}
