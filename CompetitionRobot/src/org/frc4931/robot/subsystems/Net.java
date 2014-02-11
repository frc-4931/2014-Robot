package org.frc4931.robot.subsystems;

import org.frc4931.robot.command.net.ToggleNet;
import org.frc4931.zach.drive.SingleLimitMotor;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Net extends Subsystem{
	public static final double DELAY = 0.1;
	public final SingleLimitMotor motor;
	public Net(SingleLimitMotor motor) {
		this.motor = motor;
	}
	
	public Net(int motorChannel, int motorType, int switchChannel) {
		this.motor = new SingleLimitMotor(motorChannel,motorType,switchChannel);
	}
	
	public void open(double speed){
		if(isClosed()){
			Scheduler.getInstance().add(new ToggleNet(this, speed));
		}
	}
	
	public void close(double speed){
		if(isOpen()){
			Scheduler.getInstance().add(new ToggleNet(this, speed));
		}
	}
	
	public void reset(){
		while(!motor.limit.get()){
			motor.setSpeed(0.25);
		}
		motor.setSpeed(0);
	}
	
	public boolean isOpen(){
		return (motor.currentPosition==1);
	}
	
	public boolean isClosed(){
		return (motor.currentPosition==-1);
	}
	
	protected void initDefaultCommand() {
	}
}
