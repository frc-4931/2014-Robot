package org.frc4931.zach.drive;

/**
 * A motor that has no limits, physical or otherwise. 
 * @author Zach Anderson
 *
 */
public class ContinuousMotor extends AbstractMotor{

	/**
	 * Constructs a new ContinousMotor using the specified channel and type.
	 * @param channel the PWM output of the speed controller.
	 * @param type the type of speed controller.
	 * @throws NullPointerException if type is null.
	 */
	public ContinuousMotor(int channel, SpeedControllerType type) {
		super(channel, type);
	}
	
	/**Set the speed of the motor.
	 * 
	 * @param speed the speed to set the motor, between -1.0 and 1.0.
	 */
	public void setSpeed(double speed){
		controller.set(speed);
	}
}
