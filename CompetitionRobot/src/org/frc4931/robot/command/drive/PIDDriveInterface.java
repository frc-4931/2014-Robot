package org.frc4931.robot.command.drive;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.PIDOutput;

public class PIDDriveInterface implements PIDOutput{
	public static final int TURN_MODE = 1;
	public static final int DRIVE_MODE = 2;
	private final int mode;
	
	public PIDDriveInterface(int mode) {
		this.mode = mode;
	}

	public void pidWrite(double output) {
		if(mode==TURN_MODE){
			Subsystems.driveTrain.arcadeDrive(0, output);
		}else if(mode == DRIVE_MODE){
			Subsystems.driveTrain.arcadeDrive(output, 0);
		}else{
			Subsystems.driveTrain.arcadeDrive(0, 0);
		}
	}

}
