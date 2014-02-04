package org.frc4931.robot.command;

import org.frc4931.zach.drive.LimitedMotor;

import edu.wpi.first.wpilibj.command.Command;

public class MinimizeMotor extends Command{
	private final LimitedMotor motor;
	private final double speed;
	public MinimizeMotor(LimitedMotor motor, double speed){
		this.motor = motor;
		this.speed = Math.abs(speed);
	}
	protected void end() {
		motor.stop();
	}

	protected void execute() {
		motor.setSpeed(-1*speed);
	}

	protected void initialize() {
	}

	protected void interrupted() {
		end();
	}

	protected boolean isFinished() {
		return motor.isLow();
	}

}
