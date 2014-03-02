package org.frc4931.robot.command.drive;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.PIDOutput;

public class PIDTurnInterface implements PIDOutput{
	public void pidWrite(double output) {
		Subsystems.driveTrain.setTurnSpeed(output);
	}
}
