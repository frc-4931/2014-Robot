package org.frc4931.robot.subsystems;

import org.frc4931.robot.command.limitednet.Close;
import org.frc4931.robot.command.limitednet.Open;
import org.frc4931.zach.drive.LimitedMotor;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Net extends Subsystem{
	public static final double DELAY = 0.75;
	public final LimitedMotor motor;
	public Net(LimitedMotor motor) {
		this.motor = motor;
	}
	
	public Net(int motorChannel, int motorType, int lowChannel, int highChannel) {
//		this.motor = new SingleLimitMotor(motorChannel,motorType,switchChannel);
		this.motor = new LimitedMotor(motorChannel, motorType, lowChannel, highChannel);
	}
	
	public void open(double speed){
		if(isClosed()){
//			Scheduler.getInstance().add(new ToggleNet(this, speed));
			Scheduler.getInstance().add(new Open(motor));
		}
	}
	
	public void close(double speed){
		if(isOpen()){
//			Scheduler.getInstance().add(new ToggleNet(this, speed));
			Scheduler.getInstance().add(new Close(motor));
		}
	}
	
	public void reset(){
		System.out.println("Reset");
//		System.out.println(motor.lowLimit.get());
		while(!motor.isLow()){
			motor.setSpeed(-0.25);
//			System.out.println(motor.highLimit.get());
		}
		motor.setSpeed(0);
	}
	
	public boolean isOpen(){
//		return (motor.currentPosition==1);
		return motor.isLow();
	}
	
	public boolean isClosed(){
//		return (motor.currentPosition==-1);
		return motor.isHigh();
	}
	
	protected void initDefaultCommand() {
	}
}
