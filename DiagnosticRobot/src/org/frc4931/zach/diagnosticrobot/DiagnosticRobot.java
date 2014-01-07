package org.frc4931.zach.diagnosticrobot;

import org.frc4931.zach.diagnosticrobot.io.AnalogInput;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DiagnosticRobot extends IterativeRobot{
	//@Override
	public void robotInit(){
		SmartDashboard.putData("Fader", new AnalogInput(0));
	}
}
