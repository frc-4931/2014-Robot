package org.frc4931.zach.io;

public class ReedSwitch extends DigitalSwitch{

	public ReedSwitch(int channel) {
		super (channel);
	}

	public boolean get() {
		return !input.get();
	}

}
