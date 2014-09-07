package org.frc4931.robot.command.autonomous;

import org.frc4931.robot.CompetitionRobot;
import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.CommandBase;
import org.frc4931.zach.utils.Transform;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FollowWall extends CommandBase{
	private final static double TOLERANCE= 0.0;
	private static final double MAX_ANGLE = 10.0;
	private final double targetDistance;
	
	private double startAngle;
	private Ultrasonic ultrasonicSensor;
	
	private Direction direction;
	
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
		if(!Subsystems.robot.isAutonomous()){
			CompetitionRobot.output("Command Canceld");
			this.cancel();
			return;
		}
		double driveSpeed = Subsystems.ranger.getRange()/100;
		driveSpeed = Transform.clamp(0, 0.4, driveSpeed);
		Subsystems.driveTrain.setDriveSpeed(driveSpeed);
		Subsystems.driveTrain.setTurnSpeed(0.0);
		
		double angleDifference = startAngle-Subsystems.imu.getAngle();
		SmartDashboard.putNumber("Angle Difference", angleDifference);
		double difference = getTargetDistance()-getCurrentDistance();
		double turnRate = Math.abs(difference/60.0);
		turnRate=Math.min(0.3,turnRate);
		turnRate=Math.max(0, turnRate);
		
//		CompetitionRobot.output(""+speed);
		
//		if(Math.abs(Subsystems.imu.getAngle()-startAngle)<MAX_ANGLE){
		
//		if(getCurrentDistance()<(getTargetDistance()-TOLERANCE)){
//			//turn away from wall
//			if(Math.abs(angleDifference)>MAX_ANGLE){
//				turnRate=0;
//			}
//		}else if(getCurrentDistance()>(getTargetDistance()+TOLERANCE)){
//			//turn towards wall
//			if(Math.abs(angleDifference)>MAX_ANGLE){
//				turnRate=0;
//			}
//		}
		
		if(getCurrentDistance()<(getTargetDistance()-TOLERANCE)){
			turnRate=turnRate;
			CompetitionRobot.output(""+turnRate);
		}else if(getCurrentDistance()>(getTargetDistance()+TOLERANCE)){
			turnRate = -turnRate;
			CompetitionRobot.output(""+turnRate);
		}else{
			turnRate = 0;
			CompetitionRobot.output("In Tolerace");
		}
		
		if(turnRate<0&&Math.abs(angleDifference)>=MAX_ANGLE&&angleDifference>0){
			turnRate=0;
		}
		if(turnRate>0&&Math.abs(angleDifference)>=MAX_ANGLE&&angleDifference<0){
			turnRate=0;
		}
		
		if(direction==Direction.LEFT){
//			CompetitionRobot.output("Invert directions");
			turnRate = -turnRate;
		}
		Subsystems.driveTrain.setTurnSpeed(turnRate);
		
//		CompetitionRobot.output(""+(-speed));
//		Subsystems.driveTrain.setTurnSpeed(-speed);
//	}else{
//		Subsystems.driveTrain.setDriveSpeed(0.5);
//	}
//}else{
//	Subsystems.driveTrain.setDriveSpeed(0.4);
//	CompetitionRobot.output("Max angle exceeded");
//}
	}

	protected void initialize() {
		Subsystems.imu.resetGyro();
		startAngle = Subsystems.imu.getAngle();
		double leftDistance = Subsystems.leftUltrasonicSensor.getRangeInches();
		double rightDistance = Subsystems.rightUltrasonicSensor.getRangeInches();

		if(leftDistance<rightDistance){
			ultrasonicSensor = Subsystems.leftUltrasonicSensor;
			direction = Direction.LEFT;
		}else{
			ultrasonicSensor = Subsystems.rightUltrasonicSensor;
			direction = Direction.RIGHT;
		}
	}

	protected boolean isFinished() {
		//return true;
		return Subsystems.ranger.getRange()<36.0;
	}

	private static final class Direction{
		public static final Direction LEFT = new Direction(0);
		public static final Direction RIGHT = new Direction(1);
		
		private final int value;
		
		private Direction(int value){
			this.value = value;
		}
		
		public boolean equals(Object o){
			return o==this;
		}
	}
}
