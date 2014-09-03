package org.frc4931.zach.drive;

import org.frc4931.robot.command.TwoState;

import edu.wpi.first.wpilibj.DigitalInput;

public class LimitedMotor extends AbstractMotor implements TwoState{
	private final DigitalInput highLimit;
	private final DigitalInput lowLimit;
	
	private TwoState.State logicalState = State.UNKNOWN;
	/**
	 * Constructs a new LimitedMotor with the on the using the specified channels and motor controller.
	 * @param channel the pwm channel the motor controller is attached to. 
	 * @param type the type of motor controller to use.
	 * @param lowLimit the digital input channel of the low limit switch.
	 * @param highLimit the digital input channel of the high limit switch.
	 */
	public LimitedMotor(int channel, AbstractMotor.SpeedControllerType type, int lowLimit, int highLimit) {
		super(channel, type);
		this.highLimit = new DigitalInput(highLimit);
		this.lowLimit = new DigitalInput(lowLimit);
	}
	
	/**
	 * Tests if this motor has hit the high limit.
	 * @return true if this motor has hit the high limit; false otherwise.
	 */
	public boolean isHigh(){
		return !highLimit.get();
	}
	
	/**
	 * Tests if this motor has hit the high limit.
	 * @return true if this motor has hit the low limit; false otherwise.
	 */
	public boolean isLow(){
		return lowLimit.get();
	}
	
	/**
	 * Move this motor towards the high limit at the specified speed.  This motor will not move
	 * if it is already at the high limit.
	 * @param speed the speed to turn the motor at.
	 */
	public void setHigh(double speed){
		if(!isHigh()){
			setSpeed(speed);
			logicalState = State.HIGH;
		}else{
			stop();
		}
	}
	
	/**
	 * Move this motor towards the low limit at the specified speed.  This motor will not move
	 * if it is already at the low limit.
	 * @param speed the speed to turn the motor at.
	 */
	public void setLow(double speed){
		if(!isLow()){
			setSpeed(-speed);
			logicalState = State.LOW;
		}else{
			stop();
		}	
	}
	
	/**
	 * Don't call this outside of LimitedMotor
	 */
	public void setSpeed(double speed){
		controller.set(speed);
	}

	public State getPhysicalState() {
		if(isLow()){
			return State.ONE;
		}else if(isHigh()){
			return State.TWO;
		}else{
			return State.UNKNOWN;
		}
	}

	public State getLogicalState() {
		return logicalState;
	}

	public void setStateOne(double speed) {
		setLow(speed);
	}

	public void setStateTwo(double speed) {
		setHigh(speed);
	}

	public String getName() {
		return "Limited Motor";
	}

	public boolean isContinous() {
		return true;
	}

}
