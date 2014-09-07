package org.frc4931.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Ranger extends Subsystem implements PIDSource{
	private final AnalogChannel sensor;
	public Ranger(int channel) {
		sensor = new AnalogChannel(channel);
	}
	
	public AnalogChannel getSensor(){
		return sensor;
	}
	
	public double getRange(){
		return (sensor.getVoltage()*1000)/9.8;
	}

	protected void initDefaultCommand() {
	}

	public void putToDashboard() {
		SmartDashboard.putNumber("Rangefinder",getRange());
	}

	public double pidGet() {
		return getRange();
	}

}
