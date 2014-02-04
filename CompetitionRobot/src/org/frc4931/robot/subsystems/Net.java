package org.frc4931.robot.subsystems;

import org.frc4931.zach.drive.LimitedMotor;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Net extends Subsystem{
	private final LimitedMotor leftMotor;
	private final LimitedMotor rightMotor;
	public Net(LimitedMotor left, LimitedMotor right) {
		leftMotor = left;
		rightMotor = right;
	}
	
	public void open(double speed){
		speed = Math.abs(speed);
		leftMotor.setSpeed(speed);
		rightMotor.setSpeed(speed);
	}
	public void close(double speed){
		speed = Math.abs(speed)*-1;
		leftMotor.setSpeed(speed);
		rightMotor.setSpeed(speed);
	}
	
	public boolean isOpen(){
		return leftMotor.isHigh()&&rightMotor.isHigh();
	}
	public boolean isClosed(){
		return leftMotor.isLow()&&rightMotor.isLow();
	}
	
	protected void initDefaultCommand() {
	}
}
