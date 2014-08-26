package org.frc4931.robot.command.autonomous;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Command;

public class FollowWall extends Command{
	private final static double TOLERANCE=0.01;
	private final double targetDistance;
	
	private Ultrasonic ultrasonicSensor;
	
	public FollowWall(double targetDistance) {
		this.targetDistance = targetDistance;
	}
	
	private double getTargetDistance(){
		//TODO Implement this method
		return targetDistance;
	}
	
	private double getCurrentDistance(){
		return ultrasonicSensor.getRangeInches();
	}

	protected void end() {
		Subsystems.driveTrain.stop();
	}

	protected void execute() {
		Subsystems.driveTrain.setDriveSpeed(0.0);
		Subsystems.driveTrain.setTurnSpeed(0.0);
		
		if(getCurrentDistance()<getTargetDistance()-(getTargetDistance()*TOLERANCE)){
			Subsystems.driveTrain.setTurnSpeed(0.1);
		}else if(getCurrentDistance()>getTargetDistance()+(getTargetDistance()*TOLERANCE)){
			Subsystems.driveTrain.setTurnSpeed(-0.1);
		}else{
			Subsystems.driveTrain.setDriveSpeed(0.1);
		}
	}

	protected void initialize() {
		double leftDistance = Subsystems.leftUltrasonicSensor.getRangeInches();
		double rightDistance = Subsystems.rightUltrasonicSensor.getRangeInches();
		if(leftDistance<rightDistance){
			ultrasonicSensor = Subsystems.leftUltrasonicSensor;
		}else{
			ultrasonicSensor = Subsystems.rightUltrasonicSensor;
		}
	}

	protected void interrupted() {
		end();
	}

	protected boolean isFinished() {
		return false;
	}

}
