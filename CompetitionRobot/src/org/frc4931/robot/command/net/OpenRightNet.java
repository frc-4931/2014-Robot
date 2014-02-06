package org.frc4931.robot.command.net;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class OpenRightNet extends Command{
	private final double speed;
	
	public OpenRightNet(double speed) {
		requires(Subsystems.rightNet);
		this.speed = Math.abs(speed);
	}
	
	protected void execute() {
		Subsystems.rightNet.open(speed);
	}

	protected void end() {
		Subsystems.rightNet.motor.stop();
	}
	
	protected boolean isFinished() {
		Subsystems.rightNet.isOpen();
		return false;
	}
	
	protected void interrupted() {
		end();
	}

	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

}
