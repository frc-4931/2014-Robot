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
	
	public AnalogOutput(int channel) {
		super(channel);
		this.channel = channel;
		type = "Generic PWM Output";
	}
	
	public AnalogOutput(int channel, String outputType) {
		super(channel);
		this.channel = channel;
		this.type = outputType;
	}
	
	public void set(float value){
		setPosition(value);
		output = value;
	}
	
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
