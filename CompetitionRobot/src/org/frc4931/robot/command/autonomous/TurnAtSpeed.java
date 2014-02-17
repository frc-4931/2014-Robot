package org.frc4931.robot.command.autonomous;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class TurnAtSpeed extends Command{
	private final double speed;
	public TurnAtSpeed(double speed) {
		requires(Subsystems.driveTrain);
		this.speed = speed;
	}

	protected void initialize() {
	}

	protected void execute() {
		Subsystems.driveTrain.arcadeDrive(0, speed);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Subsystems.driveTrain.arcadeDrive(0, 0);
	}

	protected void interrupted() {
		end();
	}

}