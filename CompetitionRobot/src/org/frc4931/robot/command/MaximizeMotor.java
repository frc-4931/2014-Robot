package org.frc4931.robot.command;

import org.frc4931.zach.drive.Motor;

import edu.wpi.first.wpilibj.command.Command;

public class MaximizeMotor extends Command{
	private final Motor motor;
	private final double speed;
	public MaximizeMotor(Motor motor, double speed){
		this.motor = motor;
		this.speed = Math.abs(speed);
	}
	protected void end() {
		motor.stop();
	}

	protected void execute() {
		motor.setSpeed(speed);
	}

	protected void initialize() {
	}

	protected void interrupted() {
		end();
	}

	protected boolean isFinished() {
		return motor.isMin();
	}

}
