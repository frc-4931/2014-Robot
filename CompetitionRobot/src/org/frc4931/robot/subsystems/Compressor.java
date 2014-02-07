package org.frc4931.robot.subsystems;

import org.frc4931.robot.command.pneumatics.CheckPressure;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Compressor extends Subsystem{
	private final Relay relay;
	private final DigitalInput pressureSwitch;
	
	public Compressor(Relay relay, DigitalInput pressureSwitch) {
		this.relay = relay;
		this.pressureSwitch = pressureSwitch;
	}
	
	public Compressor(int relay, int pressureSwitch){
		this.relay = new Relay(relay);
		this.pressureSwitch = new DigitalInput(pressureSwitch);
	}
	
	public void init(){
		Scheduler.getInstance().add(new CheckPressure());
	}
	
	public void activate(){
		relay.set(Relay.Value.kForward);
	}
	
	public void deactive(){
		relay.set(Relay.Value.kOff);
	}
	
	public boolean testPressure(){
		return !pressureSwitch.get();
	}
	protected void initDefaultCommand() {
		
	}

}
