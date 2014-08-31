package org.frc4931.robot;

import org.frc4931.robot.subsystems.Compressor;
import org.frc4931.robot.subsystems.DriveTrain;
import org.frc4931.robot.subsystems.IMU;
import org.frc4931.robot.subsystems.Nets;
import org.frc4931.robot.subsystems.Ranger;
import org.frc4931.robot.subsystems.Roller;
import org.frc4931.robot.subsystems.RollerArm;

import edu.wpi.first.wpilibj.PIDController;

public class Subsystems {
	public static CompetitionRobot robot;
	public static DriveTrain driveTrain;
	public static Compressor compressor;
	public static Nets nets;
	public static RollerArm arm;
	public static Roller roller;
	public static Ranger ranger;
	public static IMU imu;
	public static PIDController pid;
	public static PIDController turnPID;
}
