package org.frc4931.robot.subsystems;

import org.frc4931.robot.CompetitionRobot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Compressor extends Subsystem{
	private final Relay relay;
	private final DigitalInput pressureSwitch;
	public int triggerCount = 0;
	public boolean isOn = false;
	
	public Compressor(Relay relay, DigitalInput pressureSwitch) {
		this.relay = relay;
		this.pressureSwitch = pressureSwitch;
	}
	
	public Compressor(int relay, int pressureSwitch){
		this.relay = new Relay(relay);
		this.pressureSwitch = new DigitalInput(pressureSwitch);
	}
	
	public void init(){
		//Scheduler.getInstance().add(new CheckPressure());
	}
	
	public void activate(){
		isOn = true;
		if(CompetitionRobot.COMPRESSOR_ENABLED){
			relay.set(Relay.Value.kForward);
			System.out.println("enable Compressor");
		}
	}
	
	public void deactive(){
		isOn = false;
		relay.set(Relay.Value.kOff);
		System.out.println("disable Compressor");
	}
	
	/**
	 * Returns true if needs pressure
	 * @return
	 */
	public boolean testPressure(){
		System.out.println(pressureSwitch.get());
//		return (!pressureSwitch.get()&&(triggerCount>10));
		if(CompetitionRobot.COMPRESSOR_ENABLED){
			return !pressureSwitch.get();
		}else{
			return SmartDashboard.getBoolean("Pressure Switch");
		}
	}
	protected void initDefaultCommand() {
		
	}
	
	public void putToDashboard(){
		SmartDashboard.putBoolean("Compressor Status", isOn);
	}

}
