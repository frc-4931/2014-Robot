package org.frc4931.robot.subsystems;

import org.frc4931.robot.CompetitionRobot;
import org.frc4931.zach.drive.LimitedMotor;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Net extends Subsystem{
	public static final double DELAY = 0.75;
	public static final double CLOSE_SPEED = 0.4;
	public static final double OPEN_SPEED = 0.4;
	public boolean dashboardOpen = false;
	private final LimitedMotor motor;
	public Net(LimitedMotor motor) {
		this.motor = motor;
	}
	
	public Net(int motorChannel, int motorType, int lowChannel, int highChannel) {
		this.motor = new LimitedMotor(motorChannel, motorType, lowChannel, highChannel);
	}
	
	public void reset(){
		while(isOpen()){
			motor.setSpeed(-0.25);
		}
		motor.setSpeed(0);
	}
	
	public boolean isOpen(){
		if(CompetitionRobot.NETS_ENABLED){
			if(motor.isLow()){
				dashboardOpen = true;
			}
			return motor.isLow();
		}else{
			dashboardOpen = true;
			return true;
		}
	}
	
	public boolean isClosed(){
		if(CompetitionRobot.NETS_ENABLED){
			if(motor.isHigh()){
				dashboardOpen = false;
			}
			return motor.isHigh();
		}else{
			dashboardOpen = false;
			return true;
		}
	}
	
	public void setSpeed(double speed){
		if(CompetitionRobot.NETS_ENABLED){
			speed = Math.min(1, speed);
			speed = Math.max(-1, speed);
			motor.setSpeed(speed);
		}
	}
	
	public void stop(){
		motor.stop();
	}
	
	protected void initDefaultCommand() {
	}
}
