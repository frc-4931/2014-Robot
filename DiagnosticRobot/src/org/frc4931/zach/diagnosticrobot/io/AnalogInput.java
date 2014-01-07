package org.frc4931.zach.diagnosticrobot.io;

import org.frc4931.zach.diagnosticrobot.utils.Countdown;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.tables.ITable;

public class AnalogInput extends AnalogChannel implements Sendable{
	private int channel;
	private int offset;
	private int offsetMax;
	private boolean calibrated;
	private String type;
	
	public AnalogInput(int channel) {
		super(channel);
		this.channel = channel;
		calibrated = false;
		type = "Generic Analog Input";
	}
	
	public AnalogInput(int port, String inputType) {
		super(port);
		channel = port;
		calibrated = false;
		type = inputType;
	}
	
	public void calibrate(){
		//TODO Use a button to control calibration.
		System.out.println("Minimize Sensor");
		Countdown.count(5);
		int rawMin = getValue();
		System.out.println("Value Saved");
		System.out.println("Maximize Sensor");
		Countdown.count(5);
		int rawMax = getValue();
		System.out.println("Value Saved");
		offset = 0-rawMin;
		offsetMax = rawMax+offset;
		calibrated = true;
	}
	
	/**
	 * Returns the value of the input as a float from 0 to 1.
	 * @return the value of the input from 0 to 1.  If the sensor has not been calibrated, the result is 0.
	 */
	public float getNormalized(){
		if(calibrated){
			float offsetRaw = getValue()+offset;
			float value = offsetRaw/offsetMax;
			return value;
		}else{
			System.out.println("Sensor not calibrated, cannot normalize value");
			return 0;
		}
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
			 table.putNumber("Channel", channel);
			 table.putNumber("Normalized Value", getNormalized());
		 }
	}
}
