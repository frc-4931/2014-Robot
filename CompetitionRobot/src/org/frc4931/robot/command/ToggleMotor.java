package org.frc4931.robot.command;

import org.frc4931.zach.drive.SingleLimitMotor;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleMotor extends Command{
	private final double speed;
	
	private SingleLimitMotor motor;
	private boolean waitingForSwitch = false;
	private int moveDirection;

	public ToggleMotor(double speed) {
		this.speed = Math.abs(speed);
	}

	protected void end() {
		motor.stop();
	}

	protected void execute() {
		motor.setSpeed(speed*moveDirection);
	}

	protected boolean isFinished() {
		return waitingForSwitch&&motor.limit.get();
	}

	protected void initialize() {
		if(!motor.limit.get()){
			System.out.println("Motor is lost, aborting.");
			end();
		}else{
			System.out.println("Motor is at "+motor.currentPosition);
			moveDirection = motor.currentPosition*-1;
		}
	}

	protected void interrupted() {
		// TODO Auto-generated method stub
	}
}
