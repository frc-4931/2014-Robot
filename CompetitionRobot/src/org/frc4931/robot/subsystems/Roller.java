package org.frc4931.robot.subsystems;

import org.frc4931.zach.drive.Motor;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Roller extends Subsystem{
	public static final int INWARD_DIRECTION = 1;
	public static final int OUTWARD_DIRECTION = -1;
	
	public static final double SPEED = 0.1d;
	
	public double currentState = 0;
	
	private final Motor rollerMotor;

	public Roller(int rollerMotor, int motorType) {
		this.rollerMotor = new Motor(rollerMotor, motorType);
	}
	
	public void rollIn(){
		currentState = INWARD_DIRECTION*SPEED;
	}
	
	public void rollOut(){
		currentState = OUTWARD_DIRECTION*SPEED;
	}
	
	public void roll(){
		rollerMotor.setSpeed(currentState);
	}
	
	public void stop(){
		currentState = 0;
		rollerMotor.stop();
	}

	protected void initDefaultCommand() {
	}

}
