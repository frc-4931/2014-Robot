package org.frc4931.robot.subsystems;

import org.frc4931.robot.CompetitionRobot;
import org.frc4931.zach.drive.Motor;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Roller extends Subsystem{
	public static final int INWARD_DIRECTION = 1;
	public static final int OUTWARD_DIRECTION = -1;
	
	public static final double SPEED = 0.5d;
	
	public double currentState = 0;
	
	private final Motor rollerMotor;

	public Roller(int rollerMotor, int motorType) {
		this.rollerMotor = new Motor(rollerMotor, motorType);
	}
	
	public void rollIn(){
//		currentState = INWARD_DIRECTION*OperatorInterface.joysticks[0].getNormalThrottle();
		currentState = INWARD_DIRECTION*SPEED;
	}
	
	public void rollOut(){
//		currentState = OUTWARD_DIRECTION*OperatorInterface.joysticks[0].getNormalThrottle();
		currentState = OUTWARD_DIRECTION*SPEED;
	}
	
	public void roll(){
		if(CompetitionRobot.ROLLER_ENABLED==true){
			rollerMotor.setSpeed(currentState);
		}else{
			rollerMotor.setSpeed(0);
		}
	}
	
	public void stop(){
		CompetitionRobot.output("Roller Stopped");
		currentState = 0;
		rollerMotor.stop();
	}

	protected void initDefaultCommand() {
	}
	
	public void putToDashboard(){
		SmartDashboard.putNumber("Roller Speed", currentState);
	}

}
