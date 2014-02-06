package org.frc4931.robot.subsystems;

import org.frc4931.robot.command.ToggleMotor;
import org.frc4931.zach.drive.SingleLimitMotor;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Net extends Subsystem{
	private final SingleLimitMotor leftMotor;
	private final SingleLimitMotor rightMotor;
	public Net(SingleLimitMotor left, SingleLimitMotor right) {
		leftMotor = left;
		rightMotor = right;
	}
	
	public void open(double speed){
		if(isClosed()){
			Scheduler.getInstance().add(new ToggleMotor(leftMotor, speed));
			Scheduler.getInstance().add(new ToggleMotor(rightMotor, speed));
		}
	}
	
	public void close(double speed){
		if(isOpen()){
			Scheduler.getInstance().add(new ToggleMotor(leftMotor, speed));
			Scheduler.getInstance().add(new ToggleMotor(rightMotor, speed));
		}
	}
	
	public boolean isOpen(){
		return (leftMotor.currentPosition==1||rightMotor.currentPosition==1);
	}
	
	public boolean isClosed(){
		return (leftMotor.currentPosition==-1||rightMotor.currentPosition==-1);
	}
	
	protected void initDefaultCommand() {
	}
}
