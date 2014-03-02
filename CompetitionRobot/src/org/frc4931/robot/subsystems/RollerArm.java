package org.frc4931.robot.subsystems;

import org.frc4931.robot.CompetitionRobot;
import org.frc4931.zach.drive.Solenoid;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RollerArm extends Subsystem{
	private final Solenoid leftSolenoid;
	private final Solenoid rightSolenoid;
	private boolean dashboardPosition;
	public RollerArm(int leftSolenoidExtend, int leftSolenoidRetract, int rightSolenoidExtend, int rightSolenoidRetract) {
		leftSolenoid = new Solenoid(leftSolenoidExtend, leftSolenoidRetract, 5, 6);
		rightSolenoid = new Solenoid(rightSolenoidExtend, rightSolenoidRetract);
		leftSolenoid.retract();
		rightSolenoid.retract();
	}
	
	public void lower(){
		if(CompetitionRobot.ARM_ENABLED){
			leftSolenoid.extend();
			rightSolenoid.extend();
		}
		dashboardPosition = true;
	}
	
	public void raise(){
		if(CompetitionRobot.ARM_ENABLED){
			leftSolenoid.retract();
			rightSolenoid.retract();
		}
		dashboardPosition = false;
	}
	
	public boolean isDown(){
		if(CompetitionRobot.ARM_ENABLED){
			return leftSolenoid.isExtended()&&rightSolenoid.isExtended();
		}else{
			return dashboardPosition;
		}
	}
	
	public boolean isUp(){
		if(CompetitionRobot.ARM_ENABLED){
			return leftSolenoid.isRetracted()&&rightSolenoid.isRetracted();
		}else{
			return !dashboardPosition;
		}
	}

	protected void initDefaultCommand() {
	}
	
	public void putToDashboard(){
		SmartDashboard.putBoolean("Arm Status", dashboardPosition);
	}

}
