package org.frc4931.zach.io;

import org.frc4931.robot.CompetitionRobot;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.ADXL345_I2C.Axes;
import edu.wpi.first.wpilibj.ADXL345_I2C.DataFormat_Range;
import edu.wpi.first.wpilibj.Timer;

public class Accel {
	public static final Axes X = ADXL345_I2C.Axes.kX;
	public static final Axes Y = ADXL345_I2C.Axes.kY;
	public static final Axes Z = ADXL345_I2C.Axes.kZ;
	public static final DataFormat_Range FOURG = ADXL345_I2C.DataFormat_Range.k4G;
	private final ADXL345_I2C accel;
	private double deadzoneHigh = 0;
	private double deadzoneLow = 0;
	public Accel(DataFormat_Range range) {
		accel = new ADXL345_I2C(1, range);
	}
	public void zero(Axes axis){
		double runningHigh = 4.0;
		double runningLow = -4.0;
		for(int i = 0; i < 20; i++){
			double momentAcceleration = accel.getAcceleration(axis);
			if(momentAcceleration<runningLow){
				runningLow = momentAcceleration;
			}
			if(momentAcceleration>runningHigh){
				runningHigh = momentAcceleration;
			}
			Timer.delay(0.05);
		}
		CompetitionRobot.output(axis.value+" axis zeroed, deadzone is ["+runningLow+","+runningHigh+"].");
		deadzoneHigh = runningHigh;
		deadzoneLow = runningLow;
	}
	public double getAcceleration(Axes axis){
		double rawAccel = accel.getAcceleration(axis);
		if(deadzoneHigh==0&&deadzoneLow==0){
			return rawAccel;
		}
		if(rawAccel <= deadzoneHigh&&rawAccel >= deadzoneLow){
			return 0.0d;
		}else if(rawAccel > deadzoneHigh||rawAccel < deadzoneLow){
			return rawAccel;
		}else{
			return 0.0d;
		}
	}
}
