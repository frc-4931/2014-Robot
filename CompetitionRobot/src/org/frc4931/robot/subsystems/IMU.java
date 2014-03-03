package org.frc4931.robot.subsystems;

import org.frc4931.zach.io.Accel;

import edu.wpi.first.wpilibj.ADXL345_I2C.Axes;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IMU extends Subsystem{
	private final Gyro gyro;
	private final Accel accel;
	public IMU(int gyroChannel) {
		gyro = new Gyro(gyroChannel);
		accel = new Accel(Accel.FOURG);
	}
	
	public double getAngle(){
		return gyro.getAngle();
	}
	
	public double getAcceleration(Axes axis){
		return accel.getAcceleration(axis);
	}
	
	public Gyro getGyro(){
		return gyro;
	}
	
	public Accel getAccel(){
		return accel;
	}
	
	public void resetGyro(){
		gyro.reset();
	}
	
	public void resetAccel(){
		accel.zero(Accel.X);
		accel.zero(Accel.Y);
	}
	
	public void reset(){
		resetGyro();
		resetAccel();
	}

	protected void initDefaultCommand() {
	}
	
	public void putToDashboard(){
		SmartDashboard.putData("Gyroscope",gyro);
		SmartDashboard.putNumber("Accelerometer X", getAcceleration(Accel.X));
		SmartDashboard.putNumber("Accelerometer Y", getAcceleration(Accel.Y));
		SmartDashboard.putNumber("Accelerometer Z", getAcceleration(Accel.Z));
	}

}
