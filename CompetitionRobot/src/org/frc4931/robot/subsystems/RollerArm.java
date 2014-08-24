package org.frc4931.robot.subsystems;

import org.frc4931.zach.drive.Solenoid;

public class RollerArm extends ToggableSubsystem{
	private final Solenoid leftSolenoid;
	private final Solenoid rightSolenoid;
	
	private State logicalState = State.STATE_UNKNOWN;
	public RollerArm(int leftSolenoidExtend, int leftSolenoidRetract
			, int rightSolenoidExtend, int rightSolenoidRetract) {
		//TODO Identify these constants.
		leftSolenoid = new Solenoid(leftSolenoidExtend, leftSolenoidRetract, 5, 6);
		rightSolenoid = new Solenoid(rightSolenoidExtend, rightSolenoidRetract);
		raise();
	}
	
	public void raise(){
		leftSolenoid.retract();
		rightSolenoid.retract();
	}
	
	public void lower(){
		leftSolenoid.extend();
		rightSolenoid.extend();
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

	public void setStateOne() {
		raise();
		logicalState = State.STATE_ONE;
	}

	public void setStateTwo() {
		lower();
		logicalState = State.STATE_TWO;
	}

	public State getPhysicalState() {
		if(isUp()){
			return State.STATE_ONE;
		}else if(isDown()){
			return State.STATE_TWO;
		}else{
			return State.STATE_UNKNOWN;
		}
	}

	public State getLogicalState() {
		return logicalState;
	}

}
