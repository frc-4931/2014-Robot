package org.frc4931.zach.io;

import edu.wpi.first.wpilibj.DigitalInput;

public abstract class DigitalSwitch {
	public static final int NORMALLY_OPEN = 1;
	public static final int NORMALLY_CLOSED = 0;
	
	protected final DigitalInput input;
	protected DigitalSwitch(int channel) {
		input = new DigitalInput(channel);
	}
	public abstract boolean get();
}
