package org.frc4931.robot.command;

import org.frc4931.robot.subsystems.Solenoid;

import edu.wpi.first.wpilibj.command.Command;

public class ExtendSolenoid extends Command{
	private final Solenoid solenoid;
	private boolean done = false;
	public ExtendSolenoid(Solenoid solenoid) {
		this.solenoid = solenoid;
	}

	protected void end() {
	}

	protected void execute() {
		if(solenoid.isExtended()){
			solenoid.retract();
			done = true;
		}else{
			solenoid.extend();
			done = true;
		}
	}

	protected void initialize() {
	}

	protected void interrupted() {
		end();
	}

	protected boolean isFinished() {
		return done;
	}

}
