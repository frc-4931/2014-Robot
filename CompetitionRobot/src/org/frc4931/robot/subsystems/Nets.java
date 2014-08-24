package org.frc4931.robot.subsystems;


public class Nets extends ToggableSubsystem{
	private ToggableSubsystem.State logicalState = State.STATE_UNKNOWN;
	
	private static final double SPEED_PERCANTAGE_DIFFERENCE = 0.1;
	
	private final Net rightNet;
	private final Net leftNet;
	
	public Nets(Net right, Net left) {
		rightNet = right;
		leftNet = left;
		close();
	}

	//TODO Implement net open using a delay.
	private void open(){
		rightNet.setSpeed(-1*Net.OPEN_SPEED);
		leftNet.setSpeed(-1*(Net.OPEN_SPEED-(Net.CLOSE_SPEED*SPEED_PERCANTAGE_DIFFERENCE)));
		logicalState  = State.STATE_ONE;
	}
	
	//TODO Implement net close using a delay.
	private void close(){
		rightNet.setSpeed(Net.CLOSE_SPEED);
		leftNet.setSpeed(Net.CLOSE_SPEED-(Net.CLOSE_SPEED*SPEED_PERCANTAGE_DIFFERENCE));
		logicalState = State.STATE_TWO;
	}
	
	private boolean isOpen(){
		return rightNet.isOpen()&&leftNet.isOpen();
	}
	
	private boolean isClosed(){
		return rightNet.isClosed()&&leftNet.isClosed();
	}
	
	protected void initDefaultCommand() {
	}

	protected void setStateOne() {
		open();
	}

	protected void setStateTwo() {
		close();
	}

	public State getPhysicalState() {
		if(isOpen()){
			return State.STATE_ONE;
		}else if(isClosed()){
			return State.STATE_TWO;
		}else{
			return State.STATE_UNKNOWN;
		}
	}

	public State getLogicalState() {
		return logicalState;
	}

}
