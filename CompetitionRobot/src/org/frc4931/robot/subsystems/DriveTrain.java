package org.frc4931.robot.subsystems;

import org.frc4931.zach.drive.Motor;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A class that represents the physical drive train.
 * @author zach
 */
public class DriveTrain extends Subsystem implements PIDSource{
	public static final double P = 1.0d;
	public static final double I = 0.0d;
	public static final double D = 0.0d;
//	public static final double MAX_DELTA = 0.1d;
	public static final double DRIVE_ACCEL = 5.0d/1000.0d; //Speed Units per milliseconds per milliseconds
	private static final double ROT_ACCEL = 20.0d/1000.0d; //Turn Speed Units per milliseconds per milliseconds
	private final Motor rightFrontMotor;
	private final Motor leftFrontMotor;
	private final Motor rightRearMotor;
	private final Motor leftRearMotor;
	private final RobotDrive drive;
	
	public final PIDController pidDrive;
	//TODO
	public double currentDrive = 0;
	private double currentTurn = 0;
	
	/**
	 * Creates a new drive train with a given left and right motor.
	 * @param leftMotor the Motor object that represents the left motor.
	 * @param rightMotor the Motor object that represents the right motor.
	 */
//	public DriveTrain(Motor leftMotor, Motor rightMotor){
//		drive = new RobotDrive(leftMotor.getController(), rightMotor.getController());
//		this.rightFrontMotor=rightMotor;
//		this.leftFrontMotor=leftMotor;
//		this.rightRearMotor=null;
//		this.leftRearMotor=null;
//		pidDrive = new PIDController(P, I, D, this, new PIDOutput() {
//			public void pidWrite(double output) {
//				setDriveSpeed(output);
//			}
//		});
//		pidDrive.setInputRange(-1.0d, 1.0d);
//		pidDrive.setOutputRange(-1.0d, 1.0d);
//		pidDrive.setPercentTolerance(15.0d);
//	}
//	
//	public DriveTrain(Motor leftFrontMotor, Motor leftRearMotor, Motor rightFrontMotor, Motor rightRearMotor){
//		drive = new RobotDrive(leftFrontMotor.getController(),leftRearMotor.getController(),rightFrontMotor.getController(),rightRearMotor.getController());
//		this.rightFrontMotor=rightFrontMotor;
//		this.leftFrontMotor=leftFrontMotor;
//		this.rightRearMotor=rightRearMotor;
//		this.leftRearMotor=leftRearMotor;
//		pidDrive = new PIDController(P, I, D, this, new PIDOutput() {
//			public void pidWrite(double output) {
//				setDriveSpeed(output);
//			}
//		});
//		pidDrive.setInputRange(0.0d, 100.0d);
//		pidDrive.setOutputRange(0.0d, 100.0d);
//		pidDrive.setPercentTolerance(15.0d);
//	}
	
	public DriveTrain(int leftFrontMotor, int leftRearMotor, int rightFrontMotor, int rightRearMotor, int controller){
		this.leftFrontMotor = new Motor(leftFrontMotor,controller);
		this.leftRearMotor = new Motor(leftRearMotor,controller);
		this.rightFrontMotor = new Motor(rightFrontMotor,controller);
		this.rightRearMotor = new Motor(rightRearMotor,controller);
		drive = new RobotDrive(this.leftFrontMotor.getController(),this.leftRearMotor.getController(),this.rightFrontMotor.getController(),this.rightRearMotor.getController());
		pidDrive = new PIDController(P, I, D, this, new PIDOutput() {	
			public void pidWrite(double output) {
				setDriveSpeed(output+currentDrive);
			}
		});
		pidDrive.setInputRange(-1.0d, 1.0d);
		pidDrive.setOutputRange(-1.0d, 1.0d);
		pidDrive.setPercentTolerance(15.0d);
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
//		if(driveSpeed!=targetDrive){
//			targetDrive = driveSpeed;
//			initialDrive = currentDrive;
//			timeDriveSet = System.currentTimeMillis();
//			if(targetDrive<initialDrive){
//				driveAccel = (SmartDashboard.getNumber("Translational Acceleration")/1000.0d)*-1;
//			}else if(targetDrive>=initialDrive){
//				driveAccel = (SmartDashboard.getNumber("Translational Acceleration")/1000.0d);
//			}
//		}
//		long timeSinceDriveSet = System.currentTimeMillis()-timeDriveSet;
//		if(driveAccel>0&&currentDrive>=targetDrive){
//			currentDrive = targetDrive;
//		}else if(driveAccel<0&&currentDrive<=targetDrive){
//			currentDrive = targetDrive;
//		}else{
//			currentDrive = initialDrive+(driveAccel*timeSinceDriveSet);
//		}
//		
//		if(turnSpeed!=targetTurn){
//			targetTurn = turnSpeed;
//			initialTurn = currentTurn;
//			timeTurnSet = System.currentTimeMillis();
//			if(targetTurn<initialTurn){
//				turnAccel = ROT_ACCEL*-1;
//			}else if(targetTurn>=initialTurn){
//				turnAccel = ROT_ACCEL;
//			}
//		}
//		long timeSinceTurnSet = System.currentTimeMillis()-timeTurnSet;
//		if(turnAccel>0&&currentTurn>=targetTurn){
//			currentTurn = targetTurn;
//		}else if(turnAccel<0&&currentTurn<=targetTurn){
//			currentTurn = targetTurn;
//		}else{
//			currentTurn = initialTurn+(turnAccel*timeSinceTurnSet);
//		}
////		System.out.println((SmartDashboard.getNumber("Accel")/1000));
//		System.out.println("<"+initialDrive+"> <"+currentDrive+"> <"+targetDrive+"> <"+driveAccel+">");
//		if(SmartDashboard.getBoolean("Accel")){
//			drive.arcadeDrive(currentDrive,turnSpeed);
//		}else{
//			drive.arcadeDrive(driveSpeed,turnSpeed);
//		}
//		drive.arcadeDrive(driveSpeed,turnSpeed);
//		pidDrive.setSetpoint(driveSpeed);
//		pidDrive.enable();
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
	
	private double getMaxDelta(double speed){
		if(Math.abs(speed)<SmartDashboard.getNumber("Range 1")){
			return SmartDashboard.getNumber("Max Delta 2");
		}else if(Math.abs(speed)<SmartDashboard.getNumber("Range 2")){
			return SmartDashboard.getNumber("Max Delta 2");
		}else if(Math.abs(speed)<=SmartDashboard.getNumber("Range 3")){
			return SmartDashboard.getNumber("Max Delta 3");
		}else{
			return 0.0d;
		}
	}
	
	protected void setDriveSpeed(double speed){
//		if(speed!=targetDrive){
//			targetDrive = speed;
//			initialDrive = currentDrive;
//			timeDriveSet = System.currentTimeMillis();
//		}
		currentDrive = speed;
	}
	
	protected void setTurnSpeed(double speed){
//		if(speed!=targetTurn){
//			targetTurn = speed;
//			initialTurn = currentTurn;
//			timeTurnSet = System.currentTimeMillis();
//		}
		currentTurn = speed;
	}
	
	public void update(){
		drive.arcadeDrive(currentDrive,currentTurn);
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

	public double pidGet() {
		return currentDrive;
	}
}
