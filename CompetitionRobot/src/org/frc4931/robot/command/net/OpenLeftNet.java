package org.frc4931.robot.command.net;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class OpenLeftNet extends Command{
	private final double speed;
	
	public OpenLeftNet(double speed) {
		requires(Subsystems.leftNet);
		this.speed = Math.abs(speed);
	}
	
	protected void execute() {
		Subsystems.leftNet.open(speed);
	}

	protected void end() {
		Subsystems.leftNet.motor.stop();
	}
	
	protected boolean isFinished() {
		Subsystems.leftNet.isOpen();
		return false;
	}
	
	protected void interrupted() {
		end();
	}

	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

}
