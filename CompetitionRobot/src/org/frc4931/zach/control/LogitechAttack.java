package org.frc4931.zach.control;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * A FlightStick specifically calibrated for the Logitech Attack 3 joystick. 
 * @author zach
 *
 */
public class LogitechAttack extends FlightStick{
	/*Axes Definitions*/
	public static final int PITCH_AXIS = 2;
	public static final int ROLL_AXIS = 1;
	public static final int THROTTLE_AXIS = 3;
	public static final int NUM_BUTTONS = 11;
	
	/**
	 * Creates a new Logitech Attack 3 on the given port.
	 * @param port the port the Logitech Attack 3 is assigned in the driver station.
	 */
	public LogitechAttack(int port) {
		super(port, "Logitech Attack 3");
		buttons = new JoystickButton[NUM_BUTTONS+1];
		for(int i=1; i<NUM_BUTTONS+1;i++){
			buttons[i] = new JoystickButton(this, i);
		}
	}

	//@Override
	public double getPitch() {
		return -1*getAxis(PITCH_AXIS);
	}

	//@Override
	public double getNormalPitch() {
		return 1-getNormalizedAxis(PITCH_AXIS);
	}

	//@Override
	public double getYaw() {
		return 0;
	}

	//@Override
	public double getNormalYaw() {
		return 0;
	}

	//@Override
	public double getRoll() {
		return getAxis(ROLL_AXIS);
	}

	//@Override
	public double getNormalRoll() {
		return getNormalizedAxis(ROLL_AXIS);
	}

	//@Override
	public double getThrottle() {
		return -1*getAxis(THROTTLE_AXIS);
	}

	//@Override
	public double getNormalThrottle() {
		return 1-getNormalizedAxis(THROTTLE_AXIS);
	}

	//@Override
	protected void putDashboard() {
		table.putNumber("Channel", port);
		table.putNumber("Pitch Axis", getPitch());
		table.putNumber("Roll Axis", getRoll());
		table.putNumber("Throttle Axis", getThrottle());
		table.putNumber("Pitch Normal", getNormalPitch());
		table.putNumber("Roll Normal", getNormalRoll());
		table.putNumber("Throttle Normal", getNormalThrottle());
	}
}
