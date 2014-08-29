package org.frc4931.zach.drive;

import org.frc4931.robot.CompetitionRobot;

import edu.wpi.first.wpilibj.DigitalInput;

public class SingleLimitMotor extends AbstractMotor{
	public final DigitalInput limit;
	public int currentPosition = -1;
	public int moveDirection = 0;
	public boolean waitingForSwitch=false;
	
	public SingleLimitMotor(int channel, AbstractMotor.SpeedControllerType type, int limit){
		super(channel, type);
		this.limit = new DigitalInput(limit);
	}
	
	public void toggle(){
		if(!limit.get()){
			CompetitionRobot.output("Motor is lost, assuming motor is at "+currentPosition);
		}
		CompetitionRobot.output("Motor is at "+currentPosition);
		moveDirection = currentPosition*-1;
	}
	
	public boolean update(double speed){
		if(moveDirection!=0){
			if(!limit.get()){
				waitingForSwitch = true;
			}
			if(waitingForSwitch&&limit.get()){
				currentPosition = moveDirection;
				moveDirection = 0;
				waitingForSwitch = false;
				return true;
			}else{
				controller.set(speed*moveDirection);
				return false;
			}
		}
		return false;
	}
	
	
}
