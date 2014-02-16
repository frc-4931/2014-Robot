package org.frc4931.robot.command.limitednet;

import org.frc4931.zach.drive.LimitedMotor;

import edu.wpi.first.wpilibj.command.Command;

public class Open extends Command{
	private final LimitedMotor motor;
	public Open(LimitedMotor motor) {
		this.motor = motor;
		// TODO Auto-generated constructor stub
	}

	protected void initialize() {
	}

	protected void execute() {
		motor.setSpeed(-0.4);
//		motor.setSpeed(-1.0d);
	}

	protected boolean isFinished() {
		return motor.isLow();
//		return false;
	}

	protected void end() {
		motor.stop();
	}

	protected void interrupted() {
		end();
	}

}
