package org.frc4931.zach.diagnosticrobot.io;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.tables.ITable;

public class FlightStick extends Joystick implements Sendable{
	/*Axes definitions*/
	public static final int PITCH_AXIS = 2;
	public static final int ROLL_AXIS = 1;
	public static final int YAW_AXIS = 3;
	public static final int THROTTLE_AXIS = 4;
	
	public static final float AXIS_MAXIMUM = 1;
	public static final float AXIS_MINIMUM = -1;
	
	/*Button definitions*/
	//TODO Define buttons
	
	private String type;
	private int port;
	
	public FlightStick(int port) {
		super(port);
		this.port = port;
		type = "Generic Flight Stick";
	}
	
	public FlightStick(int port, String joystickType) {
		super(port);
		this.port = port;
		type = joystickType;
	}
	
	public double getAxis(int axis){
		return getRawAxis(axis);
	}
	
	public double getNormalizedAxis(int axis){
		return (getAxis(axis)+1)/2;
	}
	
	public boolean getButton(int button){
		return getRawButton(button);
	}
	
	/*SmartDashboard Information*/
	private ITable table;
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
			 table.putNumber("Channel", port);
			 table.putNumber("Pitch Axis", getAxis(PITCH_AXIS));
			 table.putNumber("Roll Axis", getAxis(ROLL_AXIS));
			 table.putNumber("Yaw Axis", getAxis(YAW_AXIS));
			 table.putNumber("Throttle Axis", getAxis(THROTTLE_AXIS));
			 table.putNumber("Throttle", getNormalizedAxis(THROTTLE_AXIS));
		 }
	}
}
