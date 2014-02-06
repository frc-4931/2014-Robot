package org.frc4931.robot.subsystems;

import org.frc4931.robot.command.net.ToggleNet;
import org.frc4931.zach.drive.SingleLimitMotor;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Net extends Subsystem{
	public static final int LEFT = 1;
	public static final int RIGHT = -1;
	public static final double DELAY = 0.1;
	public final SingleLimitMotor motor;
	private final int netSide;
	public Net(SingleLimitMotor motor, int netSide) {
		this.motor = motor;
		this.netSide = netSide;
	}
	
	public Net(int motorChannel, int motorType, int switchChannel, int netSide) {
		this.motor = new SingleLimitMotor(motorChannel,motorType,switchChannel);
		this.netSide = netSide;
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
	
	public boolean isOpen(){
		return (motor.currentPosition==1*netSide);
	}
	
	public boolean isClosed(){
		return (motor.currentPosition==-1*netSide);
	}
	
	protected void initDefaultCommand() {
	}
}
