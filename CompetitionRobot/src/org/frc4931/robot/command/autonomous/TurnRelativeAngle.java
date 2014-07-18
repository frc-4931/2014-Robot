package org.frc4931.robot.command.autonomous;

import org.frc4931.robot.Subsystems;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnRelativeAngle extends Command implements PIDSource{
	private final PIDController pid;
	private final double difference;
	public TurnRelativeAngle(double angle) {
		requires(Subsystems.driveTrain);
		requires(Subsystems.imu);
		difference = angle;
//		pid = new PIDController(0.1,0,0,this,new PIDTurnInterface());
		pid = Subsystems.turnPID;
		pid.setOutputRange(-0.4, 0.4);
		pid.setInputRange(0, 360);
		pid.setPercentTolerance(1.0d);
		pid.setContinuous(true);
//		SmartDashboard.putData("Turn PID",pid);
	}

	protected void end() {
		pid.disable();
		Subsystems.driveTrain.stop();
	}

	protected void execute() {
		SmartDashboard.putNumber("Angle", Subsystems.imu.getAngle());
	}

	protected void initialize() {
		double targetAngle = (Subsystems.imu.getAngle()+difference);
		if(targetAngle<0){
			targetAngle = targetAngle%360+360;
		}else if(targetAngle>0){
			targetAngle = targetAngle%360;
		}
		pid.setSetpoint(targetAngle);
		pid.enable();
	}

	protected void interrupted() {
		end();
	}

	protected boolean isFinished() {
		return pid.onTarget();
	}

	public double pidGet() {
		return Subsystems.imu.getAngle()%360;
	}

}
