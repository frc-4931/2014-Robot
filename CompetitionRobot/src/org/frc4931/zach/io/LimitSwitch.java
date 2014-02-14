package org.frc4931.zach.io;

public class LimitSwitch extends DigitalSwitch{

	public LimitSwitch(int channel) {
		super(channel);
	}

	public boolean get() {
		return input.get();
	}

}
