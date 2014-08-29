package org.frc4931.robot.subsystems;

import org.frc4931.zach.drive.LimitedMotor;

/**
 * The Nets class provides coordination for both of the net segments on the robot.
 * @author Zach Anderson
 *
 */
public class Nets extends ToggableSubsystem{
	private ToggableSubsystem.State logicalState = State.STATE_UNKNOWN;
	
	//TODO Make these private
	public final LimitedMotor leftMotor;
	public final LimitedMotor rightMotor;
	
	/**
	 * Constructs a new Nets using the specified motors.
	 * @param leftMotor the motor connected to the left net segment.
	 * @param rightMotor the motor connected to the right net segment.
	 */
	public Nets(LimitedMotor leftMotor, LimitedMotor rightMotor){
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
	}

	/**
	 * Opens this Nets.
	 */
	//TODO Implement net open using a delay.
	private void open(){
		leftMotor.setHigh(0.4);
		rightMotor.setHigh(0.3);
		logicalState  = State.STATE_ONE;
	}
	
	/**
	 * Closes this Nets.
	 */
	//TODO Implement net close using a delay.
	private void close(){
		leftMotor.setLow(0.4);
		rightMotor.setLow(0.3);
		logicalState = State.STATE_TWO;
	}
	
	/**
	 * Tests if this Nets is open.
	 * @return true if both net segments are open; false otherwise.
	 */
	private boolean isOpen(){
		return leftMotor.isHigh()&&rightMotor.isHigh();
	}
	
	/**
	 * Tests if this Nets is closed.
	 * @return true if both net segments are closed; false otherwise.
	 */
	private boolean isClosed(){
		return leftMotor.isLow()&&rightMotor.isLow();
	}
	
	protected void initDefaultCommand() {
	}

	protected void setStateOne() {
		open();
	}

	protected void setStateTwo() {
		close();
	}

	public State getPhysicalState() {
		if(isOpen()){
			return State.STATE_ONE;
		}else if(isClosed()){
			return State.STATE_TWO;
		}else{
			return State.STATE_UNKNOWN;
		}
	}

	public State getLogicalState() {
		return logicalState;
	}

}
