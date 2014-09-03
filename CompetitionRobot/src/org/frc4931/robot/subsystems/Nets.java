package org.frc4931.robot.subsystems;

import org.frc4931.robot.command.SetState;
import org.frc4931.robot.command.Toggle;
import org.frc4931.robot.command.TwoState;
import org.frc4931.robot.command.net.AddCommandAfterDelay;
import org.frc4931.zach.drive.LimitedMotor;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Provides coordination for both of the net segments on the robot.
 * @author Zach Anderson
 *
 */
public class Nets extends Subsystem implements TwoState{
	public static final double DELAY = 0.5;
	public static final double OPEN_SPEED = 0.4;
	public static final double CLOSE_SPEED = 0.4;

	private TwoState.State logicalState = State.UNKNOWN;
	
	public final Net leftNet;
	public final Net rightNet;
	
	/**
	 * Constructs a new {@link Nets} using the specified motors.
	 * @param leftMotor the motor connected to the left net segment.
	 * @param rightMotor the motor connected to the right net segment.
	 */
	public Nets(LimitedMotor leftMotor, LimitedMotor rightMotor){
		leftNet = new Net(leftMotor);
		rightNet = new Net(rightMotor);
	}

	/**
	 * Opens this {@link Nets}.
	 */
	public void setOpen(){
		Scheduler.getInstance().add(new SetState(leftNet, State.OPEN, OPEN_SPEED));
		Scheduler.getInstance().add(new AddCommandAfterDelay(new SetState
				(rightNet, State.OPEN, OPEN_SPEED), DELAY));
		logicalState = State.OPEN;
	}
	
	/**
	 * Closes this {@link Nets}.
	 */
	public void setClosed(){
		Scheduler.getInstance().add(new SetState(rightNet, State.CLOSED, CLOSE_SPEED));
		Scheduler.getInstance().add(new AddCommandAfterDelay(new SetState
				(leftNet, State.CLOSED, CLOSE_SPEED), DELAY));
		logicalState = State.CLOSED;
	}
	
	public void toggle(){
		Scheduler.getInstance().add(new Toggle(this));
	}
	
	/**
	 * Tests if this {@link Nets} is open.
	 * @return true if both net segments are open; false otherwise.
	 */
	public boolean isOpen(){
		return leftNet.isOpen()&&rightNet.isOpen();
	}
	
	/**
	 * Tests if this {@link Nets} is closed.
	 * @return true if both net segments are closed; false otherwise.
	 */
	public boolean isClosed(){
		return leftNet.isClosed()&&rightNet.isClosed();
	}
	
	protected void initDefaultCommand() {
	}

	public void setStateOne(double speed) {
		setOpen();
	}

	public void setStateTwo(double speed) {
		setClosed();
	}

	public State getPhysicalState() {
		if(isOpen()){
			return State.OPEN;
		}else if(isClosed()){
			return State.CLOSED;
		}else{
			return State.UNKNOWN;
		}
	}

	public State getLogicalState() {
		return logicalState;
	}

	public boolean isContinous() {
		return false;
	}

	public void stop() {
		leftNet.stop();
		rightNet.stop();
	}

}
