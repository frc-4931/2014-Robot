package org.frc4931.robot.command.autonomous;

import org.frc4931.robot.command.CommandBase;
import org.frc4931.robot.command.drive.PIDTurnInterface;
import org.frc4931.vision.Blob;
import org.frc4931.vision.Camera;

import edu.wpi.first.wpilibj.PIDController;

/**
 * Tracks a blob to the center of a camera by changing the turn speed.
 * @author Zach Anderson
 *
 */
public class TrackBlob extends CommandBase{
	private final PIDController pid;
	
	/**
	 * Constructs the command with the given blob.
	 * @param blob the blob to track.
	 */
	public TrackBlob(Blob blob) {
		pid = new PIDController(0,0,0,blob,new PIDTurnInterface());
		pid.setInputRange(0, Camera.XRES);
		pid.setOutputRange(-1, 1);
	}

	/**
	 * Sets up and starts the PID loop.
	 */
	protected void initialize() {
		pid.setSetpoint(Camera.XRES/2);
		pid.enable();
		super.initialize();
	}
	
	protected void doExecute(){
		
	}

	protected boolean isFinished() {
		return pid.onTarget();
	}

	/**
	 * Closes the PID Loop.
	 */
	protected void end() {
		pid.disable();
		super.end();
	}

}
