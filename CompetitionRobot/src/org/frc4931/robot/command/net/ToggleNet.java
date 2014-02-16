package org.frc4931.robot.command.net;

import org.frc4931.robot.subsystems.Net;
import org.frc4931.zach.drive.SingleLimitMotor;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleNet extends Command{
	private final double speed;
	private final SingleLimitMotor motor;
	private final long maxTime;
	private final long startTime;
	public ToggleNet(Net net, double speed) {
		requires(net);
		this.speed = Math.abs(speed);
		this.motor = null;//net.motor;
		startTime = System.currentTimeMillis();
		maxTime = 5000;
	}

	public ToggleNet(SingleLimitMotor motor, double speed, long time) {
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
