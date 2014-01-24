package org.frc4931.zach.drive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.tables.ITable;
/**
 * A class that represents the physical drive train.
 * @author zach
 */
public class DriveTrain extends RobotDrive implements Sendable{
	private final Motor rightMotor;
	private final Motor leftMotor;
	
	private ITable table;
	
	/**
	 * Creates a new drive train with a given left and right motor.
	 * @param leftMotor the Motor object that represents the left motor.
	 * @param rightMotor the Motor object that represents the right motor.
	 */
	public DriveTrain(Motor leftMotor, Motor rightMotor){
		super(leftMotor.getController(), rightMotor.getController());
		this.rightMotor=rightMotor;
		this.leftMotor=leftMotor;
	}
	
	/**Directly control left and right motor speed
	 * @param leftSpeed the speed of the left motor in the range -1.0 to 1.0
	 * @param rightSpeed the speed of the right motor in the range -1.0 to 1.0.
	 */
	public void directDrive(double leftSpeed, double rightSpeed){
		tankDrive(leftSpeed, rightSpeed);
	}
	
	/*SmartDashboard Information*/
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
