package org.frc4931.zach.io;

public class PressureSwitch extends DigitalSwitch{

	public PressureSwitch(int channel) {
		super(channel);
	}
	
	public boolean get(){
		return !input.get();
	}

}
