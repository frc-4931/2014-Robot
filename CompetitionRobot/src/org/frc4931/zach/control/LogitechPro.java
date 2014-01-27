package org.frc4931.zach.control;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * A FlightStick specifically calibrated for the Logitech Extreme 3D Pro joystick. 
 * @author zach
 *
 */
public class LogitechPro extends FlightStick{
	private static final int NUM_BUTTONS = 12;
	
	/*Axes Definitions*/
	public static final int PITCH_AXIS = 2;
	public static final int ROLL_AXIS = 1;
	public static final int YAW_AXIS = 3;
	public static final int THROTTLE_AXIS = 4;
	
	/**
	 * Creates a new Logitech Extreme 3D Pro on the given port.
	 * @param port the port the Logitech Extreme 3D Pro is assigned in the driver station.
	 */
	public LogitechPro(int port){
		super(port, "Logitech Extreme 3D Pro");
		buttons = new JoystickButton[NUM_BUTTONS];
		for(int i=0; i<NUM_BUTTONS;i++){
			buttons[i] = new JoystickButton(this, i+1);
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
		return getAxis(YAW_AXIS);
	}

	//@Override
	public double getNormalYaw() {
		return getNormalizedAxis(YAW_AXIS);
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
		table.putNumber("Yaw Axis", getYaw());
		table.putNumber("Throttle Axis", getThrottle());
		table.putNumber("Normal Pitch", getNormalPitch());
		table.putNumber("Normal Roll", getNormalRoll());
		table.putNumber("Normal Yaw", getNormalYaw());
		table.putNumber("Normal Throttle", getNormalThrottle());
	}

}
