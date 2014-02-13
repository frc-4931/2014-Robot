package org.frc4931.robot.command.autonomous;

import org.frc4931.robot.command.drive.PIDDriveInterface;
import org.frc4931.vision.Blob;
import org.frc4931.vision.Camera;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

public class TrackBlob extends Command{
	private final PIDController pid;
	public TrackBlob(Blob blob) {
		pid = new PIDController(0,0,0,blob,new PIDDriveInterface(PIDDriveInterface.TURN_MODE));
		pid.setInputRange(0, Camera.XRES);
		pid.setOutputRange(-1, 1);
	}

	protected void initialize() {
		pid.setSetpoint(Camera.XRES/2);
		pid.enable();
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return pid.onTarget();
	}

	protected void end() {
		pid.disable();
	}

	protected void interrupted() {
		end();
	}

}
