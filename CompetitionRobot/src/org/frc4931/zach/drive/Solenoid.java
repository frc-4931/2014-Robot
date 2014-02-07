package org.frc4931.zach.drive;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Solenoid {
	private final DoubleSolenoid solenoid;
	public Solenoid(int extendChannel, int retractChannel) {
		solenoid = new DoubleSolenoid(extendChannel, retractChannel);
	}
	public void extend(){
		System.out.println("Solenoid Extended");
		solenoid.set(DoubleSolenoid.Value.kForward);
	}
	public void retract(){
		System.out.println("Solenoid Retracted");
		solenoid.set(DoubleSolenoid.Value.kReverse);
	}
	public boolean isExtended(){
		return (solenoid.get()==DoubleSolenoid.Value.kForward);
	}
	public boolean isRetracted(){
		return (solenoid.get()==DoubleSolenoid.Value.kReverse);
	}

}
