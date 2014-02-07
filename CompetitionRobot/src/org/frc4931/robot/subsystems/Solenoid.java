package org.frc4931.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Solenoid extends Subsystem{
	private final DoubleSolenoid solenoid;
	public Solenoid(int extendChannel, int retractChannel) {
		solenoid = new DoubleSolenoid(extendChannel, retractChannel);
	}
	public void extend(){
		solenoid.set(DoubleSolenoid.Value.kForward);
	}
	public void retract(){
		solenoid.set(DoubleSolenoid.Value.kReverse);
	}
	public boolean isExtended(){
		return (solenoid.get()==DoubleSolenoid.Value.kForward);
	}
	public boolean isRetracted(){
		return (solenoid.get()==DoubleSolenoid.Value.kReverse);
	}

	protected void initDefaultCommand() {
	}

}
