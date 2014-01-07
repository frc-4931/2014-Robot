package org.frc4931.zach.diagnosticrobot.io;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.tables.ITable;

public class AnalogOutput extends PWM implements Sendable{
	public static final float LOGIC_HIGH = 5;
	public static final float LOGIC_LOW = 0;
	private int channel;
	private float output;
	private String type;
	
	/**
	 * Creates an analog output on the specified channel.
	 * @param channel - the channel to create the output on.
	 */
	public AnalogOutput(int channel) {
		super(channel);
		this.channel = channel;
		type = "Generic PWM Output";
	}
	/**
	 * Create an analog output on the specified channel.
	 * @param channel - the channel to create the output on.
	 * @param outputType - the name of the object as displayed in the SmartDashboard.
	 */
	public AnalogOutput(int channel, String outputType) {
		super(channel);
		this.channel = channel;
		this.type = outputType;
	}
	
	/**
	 * Sets the current output on the channel.
	 * @param value - the desired output from 0 to 1.
	 */
	public void set(float value){
		setPosition(value);
		output = value;
	}
	
	/**
	 * sets the current output to a specified voltage.
	 * @param voltage - the desired voltage from LOGIC_LOW to LOGIC_HIGH.
	 */
	public void setVoltage(float voltage){
		voltage = Math.min(voltage, LOGIC_HIGH);
		voltage = Math.max(voltage, LOGIC_LOW);
		float normalized = voltage/LOGIC_HIGH;
		int rawValue = (int) (normalized)*255;
		setRaw(rawValue);
		output = normalized;
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
			 table.putNumber("Current Output (v)", output*LOGIC_HIGH);
		 }
	}
}
