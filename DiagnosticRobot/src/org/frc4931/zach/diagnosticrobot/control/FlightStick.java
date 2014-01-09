package org.frc4931.zach.diagnosticrobot.control;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.tables.ITable;

public abstract class FlightStick extends Joystick implements Sendable{	
	private String type;
	protected int port;
	
	protected FlightStick(int port, String joystickType) {
		super(port);
		this.port = port;
		type = joystickType;
	}
	
	protected double getAxis(int axis){
		return getRawAxis(axis);
	}
	
	protected double getNormalizedAxis(int axis){
		return (getAxis(axis)+1)/2;
	}
	
	protected boolean getButton(int button){
		return getRawButton(button);
	}
	
	/*Abstract Methods*/
	//Roll is low to the left and high to the right.
	//Yaw is low to the left and high to the right.
	//Pitch is low to the back and high forward.
	//Throttle is low negative and high positive.
	
	public abstract double getPitch();
	public abstract double getNormalPitch();
	public abstract double getYaw();
	public abstract double getNormalYaw();
	public abstract double getRoll();
	public abstract double getNormalRoll();
	public abstract double getThrottle();
	public abstract double getNormalThrottle();
	protected abstract void putDashboard();
	
	/*SmartDashboard Information*/
	protected ITable table;
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
