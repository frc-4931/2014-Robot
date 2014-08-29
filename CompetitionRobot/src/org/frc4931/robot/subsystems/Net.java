package org.frc4931.robot.subsystems;

import org.frc4931.robot.CompetitionRobot;
import org.frc4931.zach.drive.LimitedMotor;

import edu.wpi.first.wpilibj.command.Subsystem;

/**@deprecated*/
public class Net extends Subsystem{
	public static final double DELAY = 0.75;
	public static final double CLOSE_SPEED = 0.4;
	public static final double OPEN_SPEED = 0.4;
	public boolean dashboardOpen = false;
	private final LimitedMotor motor;
	public Net(LimitedMotor motor) {
		this.motor = motor;
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
			return dashboardOpen;
		}
	}
	
	public boolean isClosed(){
		if(CompetitionRobot.NETS_ENABLED){
			if(motor.isHigh()){
				dashboardOpen = false;
			}
			return motor.isHigh();
		}else{
			return !dashboardOpen;
		}
	}
	
	public void setSpeed(double speed){
		if(CompetitionRobot.NETS_ENABLED){
			speed = Math.min(1, speed);
			speed = Math.max(-1, speed);
			motor.setSpeed(speed);
		}else{
			if(speed>0){
				dashboardOpen = false;
			}else if(speed<0){
				dashboardOpen = true;
			}
		}
	}
	
	public void stop(){
		motor.stop();
	}
	
	protected void initDefaultCommand() {
	}
}
