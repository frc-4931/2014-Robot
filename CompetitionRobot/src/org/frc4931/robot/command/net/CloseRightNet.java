package org.frc4931.robot.command.net;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class CloseRightNet extends Command{
	private final double speed;
	
	public CloseRightNet(double speed) {
		requires(Subsystems.rightNet);
		this.speed = Math.abs(speed);
	}
	
	protected void execute() {
		Subsystems.rightNet.close(speed);
	}

	protected void end() {
		Subsystems.rightNet.motor.stop();
	}
	
	protected boolean isFinished() {
		Subsystems.rightNet.isClosed();
		return false;
	}
	
	protected void interrupted() {
		end();
	}

	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

}
