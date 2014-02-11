package org.frc4931.robot.subsystems;

import org.frc4931.zach.drive.Solenoid;

import edu.wpi.first.wpilibj.command.Subsystem;

public class RollerArm extends Subsystem{
	private final Solenoid leftSolenoid;
	private final Solenoid rightSolenoid;
	public RollerArm(int leftSolenoidExtend, int leftSolenoidRetract, int rightSolenoidExtend, int rightSolenoidRetract) {
		leftSolenoid = new Solenoid(leftSolenoidExtend, leftSolenoidRetract, 5, 6);
		rightSolenoid = new Solenoid(rightSolenoidExtend, rightSolenoidRetract);
		leftSolenoid.retract();
		rightSolenoid.retract();
	}
	
	public void lower(){
		leftSolenoid.extend();
		rightSolenoid.extend();
	}
	
	public void raise(){
		leftSolenoid.retract();
		rightSolenoid.retract();
	}
	
	public boolean isDown(){
		System.out.println(leftSolenoid.extendedSwitch.get());
		return leftSolenoid.isExtended()&&rightSolenoid.isExtended();
	}
	
	public boolean isUp(){
		System.out.println(leftSolenoid.extendedSwitch.get());
		return leftSolenoid.isRetracted()&&rightSolenoid.isRetracted();
	}

	protected void initDefaultCommand() {
	}

}
