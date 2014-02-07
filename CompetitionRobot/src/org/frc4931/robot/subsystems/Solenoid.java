package org.frc4931.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Solenoid extends Subsystem{
	private final DoubleSolenoid solenoid;
	public Solenoid(int extendChannel, int retractChannel) {
		solenoid = new DoubleSolenoid(extendChannel, retractChannel);
		System.out.println("kForward"+DoubleSolenoid.Value.kForward);
		System.out.println("kForward"+DoubleSolenoid.Value.kReverse);
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
	public Value get(){
		return solenoid.get();
	}

	protected void initDefaultCommand() {
	}

}
