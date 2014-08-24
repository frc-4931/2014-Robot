package org.frc4931.zach.utils;

import org.frc4931.robot.CompetitionRobot;

import edu.wpi.first.wpilibj.Timer;

public class Countdown {
	public static void count(int start){
		for(int i=start;i>0;i--){
			CompetitionRobot.output("Countdown: "+i);
			Timer.delay(1);
		}
	}
}
