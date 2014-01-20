package org.frc4931.zach.diagnosticrobot.drive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.tables.ITable;

public class DriveTrain extends RobotDrive implements Sendable{
	private Motor rightMotor;
	private Motor leftMotor;
	public DriveTrain(Motor leftMotor, Motor rightMotor){
		super(leftMotor.getController(), rightMotor.getController());
		this.rightMotor=rightMotor;
		this.leftMotor=leftMotor;
	}
	
	public void directDrive(double leftSpeed, double rightSpeed){
		tankDrive(leftSpeed, rightSpeed);
	}
	
	/*SmartDashboard Information*/
	private ITable table;
	//@Override
	public String getSmartDashboardType() {
		return "Drive Train";
	}
	//@Override
	public ITable getTable() {
		return table;
	}
	//@Override
	public void initTable(ITable table) {
		 this.table = table;
		 if (table != null) {
			 table.putNumber("Left Speed", leftMotor.getSpeed());
			 table.putNumber("Right Speed", rightMotor.getSpeed());
		 }
	}
}
