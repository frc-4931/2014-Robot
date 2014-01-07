package org.frc4931.zach.diagnosticrobot.utils;

import edu.wpi.first.wpilibj.Timer;

public class Countdown {
	public static void count(int start){
		for(int i=start;i>0;i--){
			System.out.println(i);
			Timer.delay(1);
		}
	}
}
