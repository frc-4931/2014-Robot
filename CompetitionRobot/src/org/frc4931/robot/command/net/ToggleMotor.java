package org.frc4931.robot.command.net;

import org.frc4931.zach.drive.SingleLimitMotor;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleMotor extends Command{
	private final double speed;
	private final SingleLimitMotor motor;
	private final long maxTime;
	private final long startTime;
	public ToggleMotor(SingleLimitMotor motor, double speed) {
		this.speed = Math.abs(speed);
		this.motor = motor;
		startTime = System.currentTimeMillis();
		maxTime = 5000;
	}

	public ToggleMotor(SingleLimitMotor motor, double speed, long time) {
		this.speed = Math.abs(speed);
		this.motor = motor;
		startTime = System.currentTimeMillis();
		maxTime = time;
	}
	
	protected void end() {
		System.out.println("Move finished in: <"+(System.currentTimeMillis()-startTime)+"> milliseconds.");
		motor.stop();
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		long currentTime = System.currentTimeMillis()-startTime;
		return (motor.update(speed)||currentTime>=maxTime);
	}

	protected void initialize() {
		motor.toggle();
	}

	protected void interrupted() {
		// TODO Auto-generated method stub
	}
}
