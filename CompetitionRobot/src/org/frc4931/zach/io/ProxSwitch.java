package org.frc4931.zach.io;

public class ProxSwitch extends DigitalSwitch{

	public ProxSwitch(int channel) {
		super(channel);
	}

	public boolean get() {
		return !input.get();
	}

}
