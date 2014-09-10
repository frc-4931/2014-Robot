package org.frc4931.robot.subsystems;

import org.frc4931.zach.io.Accel;

import edu.wpi.first.wpilibj.ADXL345_I2C.Axes;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IMU extends Subsystem implements PIDSource{
	private final Gyro gyro;
	private final Accel accel;
	public IMU(int gyroChannel) {
		gyro = new Gyro(gyroChannel);
		accel = new Accel(Accel.FOURG);
	}
	
	public double getAngle(){
		return gyro.getAngle();
	}
	
    /**
     * Get the orientation of the robot relative to the last time the gyro was {@link #reset() reset}. This resulting value is
     * always greater than -180 degrees and less than or equal to 180 degrees.
     *
     * @return the orientation angle; always > -180 and <= 180.
     */
    public double getOrientationAngle() {
        return getOrientationAngle(0.0d);
    }

    /**
     * Get the orientation of the robot relative to the given angle. This resulting value is always greater than -180 degrees and
     * less than or equal to 180 degrees.
     *
     * @param relativeToAngle the angle relative to which the orientation should be computed; can be negative or positive
     * @return the orientation angle; always > -180 and <= 180.
     */
    public double getOrientationAngle( double relativeToAngle ) {
        double currentAngle = getAngle() + relativeToAngle;
        while (currentAngle < -180.0) {
            currentAngle += 360.0;
        }
        while (currentAngle > 180.0) {
            currentAngle -= 360.0;
        }
        return currentAngle;
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
		SmartDashboard.putNumber("Angle", getAngle());
		SmartDashboard.putNumber("Accelerometer X", accel.getAcceleration(Accel.X));
		SmartDashboard.putNumber("Accelerometer Y", accel.getAcceleration(Accel.Y));
		SmartDashboard.putNumber("Accelerometer Z", accel.getAcceleration(Accel.Z));
	}

	public double pidGet() {
		if(getAngle()<0){
			return (getAngle()%360)+360;
		}else{
			return getAngle()%360;
		}
	}

}
