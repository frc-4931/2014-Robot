package org.frc4931.robot.command.autonomous;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class DriveAtSpeed extends Command{
	private final double speed;
	public DriveAtSpeed(double speed) {
		requires(Subsystems.driveTrain);
		this.speed = speed;
	}

	protected void initialize() {
	}

	protected void execute() {
		Subsystems.driveTrain.arcadeDrive(speed, 0);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Subsystems.driveTrain.stop();
	}

	protected void interrupted() {
		end();
	}

}
