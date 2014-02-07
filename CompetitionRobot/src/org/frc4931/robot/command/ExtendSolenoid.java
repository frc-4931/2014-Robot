package org.frc4931.robot.command;

import org.frc4931.robot.subsystems.Solenoid;

import edu.wpi.first.wpilibj.command.Command;

public class ExtendSolenoid extends Command{
	private final Solenoid solenoid;
	public ExtendSolenoid(Solenoid solenoid) {
		this.solenoid = solenoid;
	}

	protected void end() {
	}

	protected void execute() {
		if(solenoid.isExtended()){
			solenoid.retract();
		}else{
			solenoid.extend();
		}
		System.out.println("Extended");
	}

	protected void initialize() {
	}

	protected void interrupted() {
		end();
	}

	protected boolean isFinished() {
		return false;
	}

}
