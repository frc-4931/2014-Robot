package org.frc4931.zach.utils;

public class Transform {
	public static double map(double inMin, double inMax, double outMin, double outMax, double value){
		return (value-inMin)/(inMax-inMin)*(outMax-outMin)+outMin;
	}
}
