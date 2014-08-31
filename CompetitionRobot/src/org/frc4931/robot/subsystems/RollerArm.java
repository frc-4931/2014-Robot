package org.frc4931.robot.subsystems;

import org.frc4931.robot.command.TwoState;
import org.frc4931.zach.drive.Solenoid;

public class RollerArm implements TwoState{
	private final Solenoid leftSolenoid;
	private final Solenoid rightSolenoid;
	
	private State logicalState = State.UNKNOWN;
	public RollerArm(int leftSolenoidExtend, int leftSolenoidRetract
			, int rightSolenoidExtend, int rightSolenoidRetract) {
		leftSolenoid = new Solenoid(leftSolenoidExtend, leftSolenoidRetract);
		rightSolenoid = new Solenoid(rightSolenoidExtend, rightSolenoidRetract);
		raise();
	}
	
	public void raise(){
		leftSolenoid.retract();
		rightSolenoid.retract();
		logicalState = State.ONE;
	}
	
	public void lower(){
		leftSolenoid.extend();
		rightSolenoid.extend();
		logicalState = State.TWO;
	}
	
	public boolean isDown(){
		return leftSolenoid.isExtended()&&rightSolenoid.isExtended();
	}
	
	public boolean isUp(){
		return leftSolenoid.isRetracted()&&rightSolenoid.isRetracted();
	}

	protected void initDefaultCommand() {
	}
	
	public void putToDashboard(){
	}

	public void setStateOne(double speed) {
		raise();
	}

	public void setStateTwo(double speed) {
		lower();
	}

	public State getPhysicalState() {
		if(isUp()){
			return State.ONE;
		}else if(isDown()){
			return State.TWO;
		}else{
			return State.UNKNOWN;
		}
	}

	public State getLogicalState() {
		return logicalState;
	}

	public String getName() {
		return "Roller Arm";
	}

	public boolean isContinous() {
		return false;
	}

}
