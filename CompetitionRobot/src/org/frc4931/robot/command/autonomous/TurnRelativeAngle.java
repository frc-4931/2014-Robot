package org.frc4931.robot.command.autonomous;

import org.frc4931.robot.Subsystems;
import org.frc4931.robot.command.CommandBase;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Turns by a given angle using the robot's gyroscope and a PID loop.
 * @author Zach Anderson
 *
 */
public class TurnRelativeAngle extends CommandBase implements PIDSource{
	private final PIDController pid;
	private final double difference;
	
	/**
	 * Constructs the command.
	 * @param angle the angle to turn by.
	 */
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
	
	/**
	 * Sets up and starts the PID loop.
	 */
	protected void initialize() {
		double targetAngle = (Subsystems.imu.getAngle()+difference);
		if(targetAngle<0){
			targetAngle = targetAngle%360+360;
		}else if(targetAngle>0){
			targetAngle = targetAngle%360;
		}
		pid.setSetpoint(targetAngle);
		pid.enable();
		super.initialize();
	}

	/**
	 * Closes the PID loop and stops the drive train.
	 */
	protected void end() {
		pid.disable();
		Subsystems.driveTrain.stop();
		super.end();
	}

	protected void execute() {
		SmartDashboard.putNumber("Angle", Subsystems.imu.getAngle());
	}
	
	protected boolean isFinished() {
		return pid.onTarget();
	}

	/**
	 * Gets the current value of the gyroscope, in the range 0, 360
	 */
	public double pidGet() {
		return Subsystems.imu.getAngle()%360;
	}

}
