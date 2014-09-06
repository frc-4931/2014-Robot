package org.frc4931.robot.command.autonomous;

import org.frc4931.robot.CompetitionRobot;
import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.CommandBase;

import edu.wpi.first.wpilibj.Ultrasonic;

public class FollowWall extends CommandBase{
	private final static double TOLERANCE=1.0;
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

	protected void doExecute() {
		Subsystems.driveTrain.setDriveSpeed(-0.2);
		Subsystems.driveTrain.setTurnSpeed(0.0);
		double difference = getTargetDistance()-getCurrentDistance();
		double speed = Math.abs(difference/20.0);
		speed=Math.min(0.3,speed);
		speed=Math.max(0, speed);
		CompetitionRobot.output(""+speed);
		if(getCurrentDistance()<getTargetDistance()-TOLERANCE){
			Subsystems.driveTrain.setTurnSpeed(speed);
		}else if(getCurrentDistance()>getTargetDistance()+TOLERANCE){
			Subsystems.driveTrain.setTurnSpeed(-speed);
		}else{
			Subsystems.driveTrain.setDriveSpeed(0.4);
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

	protected boolean isFinished() {
		return Subsystems.ranger.getRange()<0.5;
	}

}
