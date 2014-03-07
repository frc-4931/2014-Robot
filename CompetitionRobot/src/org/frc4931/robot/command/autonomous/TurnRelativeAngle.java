package org.frc4931.robot.command.autonomous;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.drive.PIDTurnInterface;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnRelativeAngle extends Command{
	private final PIDController pid;
	private final double targetAngle;
	public TurnRelativeAngle(double angle) {
		requires(Subsystems.driveTrain);
		requires(Subsystems.imu);
		targetAngle = Subsystems.imu.getAngle()+angle;
		pid = new PIDController(1,0,0,Subsystems.imu.getGyro(),new PIDTurnInterface());
		pid.setOutputRange(-0.25, 0.25);
		pid.setInputRange(0, 360);
		pid.setPercentTolerance(5.0d);
		pid.setContinuous();
		SmartDashboard.putData("Turn PID",pid);
	}

	protected void end() {
		Subsystems.driveTrain.stop();
	}

	protected void execute() {
		SmartDashboard.putNumber("Angle", Subsystems.imu.getAngle());
	}

	protected void initialize() {
		pid.setSetpoint(targetAngle);
		pid.enable();
	}

	protected void interrupted() {
		end();
	}

	protected boolean isFinished() {
		return pid.onTarget();
	}

}
