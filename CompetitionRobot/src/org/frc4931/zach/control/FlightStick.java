package org.frc4931.zach.control;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * An abstract class the can be extended to allow calibrated retrieval of each joystick axis by name.
 * @author zach
 *
 */
public abstract class FlightStick extends Joystick implements Sendable{
	private final String type;
	protected final int port;
	public JoystickButton[] buttons;
	
	protected ITable table;
	
	/**
	 * Creates a new FlightStick in the given port.
	 * @param port the port the joystick is assigned to in the driver station
	 * @param joystickType the name of the joystick in the SmartDashboard.
	 */
	protected FlightStick(int port, String joystickType) {
		super(port);
		this.port = port;
		type = joystickType;
	}
	
	/**
	 * Gets the provided axis in the range -1.0 to 1.0.
	 * @param axis the axis to read
	 * @return the current value of the axis in the range -1.0 to 1.0.
	 */
	protected double getAxis(int axis){
		return getRawAxis(axis);
	}
	
	/**
	 * Gets the provided axis in the range 0.0 to 1.0.
	 * @param axis the axis to read
	 * @return the current value of the axis in the range 0.0 to 1.0.
	 */
	protected double getNormalizedAxis(int axis){
		return (getAxis(axis)+1)/2;
	}
	
	/**
	 * Gets the state of a button.
	 * @param button the button to read.
	 * @return the state of the button as a boolean.
	 */
	protected boolean getButton(int button){
		return getRawButton(button);
	}
	
	/*Abstract Methods*/
	//Roll is low to the left and high to the right.
	//Yaw is low to the left and high to the right.
	//Pitch is low to the back and high forward.
	//Throttle is low negative and high positive.
	
	/**
	 * Returns the pitch as double in the range -1.0 to 1.0.
	 * @return the current pitch of the joystick in the range -1.0 to 1.0.
	 */
	public abstract double getPitch();
	/**
	 * Returns the pitch as double in the range 0 to 1.0.
	 * @return the current pitch of the joystick in the range 0 to 1.0.
	 */
	public abstract double getNormalPitch();
	/**
	 * Returns the yaw as double in the range -1.0 to 1.0
	 * @return the current yaw of the joystick in the range -1.0 to 1.0.
	 */
	public abstract double getYaw();
	/**
	 * Returns the yaw as double in the range 0 to 1.0.
	 * @return the current yaw of the joystick in the range 0 to 1.0.
	 */
	public abstract double getNormalYaw();
	/**
	 * Returns the roll as double in the range -1.0 to 1.0
	 * @return the current roll of the joystick in the range -1.0 to 1.0.
	 */
	public abstract double getRoll();
	/**
	 * Returns the roll as double in the range 0 to 1.0.
	 * @return the current roll of the joystick in the range 0 to 1.0.
	 */
	public abstract double getNormalRoll();
	/**
	 * Returns the throttle as double in the range -1.0 to 1.0
	 * @return the current throttle of the joystick in the range -1.0 to 1.0.
	 */
	public abstract double getThrottle();
	/**
	 * Returns the throttle as double in the range 0 to 1.0.
	 * @return the current throttle of the joystick in the range 0 to 1.0.
	 */
	public abstract double getNormalThrottle();
	/**
	 * Called when the joystick is put to the dashboard.
	 */
	protected abstract void putDashboard();
	
	/*SmartDashboard Information*/
	//@Override
	public String getSmartDashboardType() {
		return type;
	}
	//@Override
	public ITable getTable() {
		return table;
	}
	//@Override
	public void initTable(ITable table) {
		 this.table = table;
		 if (table != null) {
			 putDashboard();
		 }
	}
}
