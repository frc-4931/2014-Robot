package org.frc4931.zach.drive;

import edu.wpi.first.wpilibj.DigitalInput;

public class SingleLimitMotor extends Motor{
	public final DigitalInput limit;
	public int currentPosition=0;
	
	public SingleLimitMotor(int channel, int type, int limit){
		super(channel, type);
		this.limit = new DigitalInput(limit);
	}
}
