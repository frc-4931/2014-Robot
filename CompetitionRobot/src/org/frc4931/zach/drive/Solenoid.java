package org.frc4931.zach.drive;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Solenoid {
	private final DoubleSolenoid solenoid;
	public final DigitalInput extendedSwitch;
	public final DigitalInput retractedSwitch;
	public Solenoid(int extendChannel, int retractChannel) {
		solenoid = new DoubleSolenoid(extendChannel, retractChannel);
		extendedSwitch = null;
		retractedSwitch = null;
	}
	public Solenoid(int extendChannel, int retractChannel, int extendSwitch, int retractSwitch){
		solenoid = new DoubleSolenoid(extendChannel, retractChannel);
		extendedSwitch = new DigitalInput(extendSwitch);
		retractedSwitch = new DigitalInput(retractSwitch);
	}
	public void extend(){
		System.out.println("Solenoid Extended");
		Subsystems.compressor.triggerCount++;
		solenoid.set(DoubleSolenoid.Value.kForward);
	}
	public void retract(){
		System.out.println("Solenoid Retracted");
		Subsystems.compressor.triggerCount++;
		solenoid.set(DoubleSolenoid.Value.kReverse);
	}
	public boolean isExtended(){
		if(extendedSwitch == null){
			return (solenoid.get()==DoubleSolenoid.Value.kForward);
		}else{
			return !extendedSwitch.get();
		}
	}
	public boolean isRetracted(){
		if(retractedSwitch == null){
			return (solenoid.get()==DoubleSolenoid.Value.kReverse);
		}else{
			return !retractedSwitch.get();
		}
	}

}
