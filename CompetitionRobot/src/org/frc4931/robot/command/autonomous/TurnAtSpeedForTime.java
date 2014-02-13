package org.frc4931.robot.command.autonomous;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class TurnAtSpeedForTime extends Command{
	private final double speed;
	private final double time;
	public TurnAtSpeedForTime(double speed, double time) {
		requires(Subsystems.driveTrain);
		this.speed = speed;
		this.time = time;
	}

	protected void initialize() {
	}

	protected void execute() {
		Subsystems.driveTrain.arcadeDrive(0, speed);
	}

	protected boolean isFinished() {
		return timeSinceInitialized()>time;
	}

	protected void end() {
		Subsystems.driveTrain.arcadeDrive(0, 0);
	}

	protected void interrupted() {
		end();
	}

}