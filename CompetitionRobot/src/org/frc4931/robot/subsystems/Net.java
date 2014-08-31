package org.frc4931.robot.subsystems;

import org.frc4931.robot.command.TwoState;
import org.frc4931.zach.drive.LimitedMotor;

public class Net implements TwoState{
	private final LimitedMotor motor;
	public Net(LimitedMotor motor) {
		this.motor = motor;
	}
	
	public boolean isOpen(){
		return motor.isLow();
	}
	
	public boolean isClosed(){
		return motor.isHigh();
	}
	
	public void open(double speed){
		motor.setHigh(speed);
	}
	
	public void close(double speed){
		motor.setLow(speed);
	}
	
	public void stop(){
		motor.stop();
	}

	public void setStateOne(double speed) {
		close(speed);
	}

	public void setStateTwo(double speed) {
		open(speed);
	}

	public State getPhysicalState() {
		return motor.getPhysicalState();
	}

	public State getLogicalState() {
		return motor.getLogicalState();
	}

	public String getName() {
		return "Net Segment";
	}

	public boolean isContinous() {
		return true;
	}
}
