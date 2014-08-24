package org.frc4931.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class ToggableSubsystem extends Subsystem{
	
	public void setState(State targetState) {
		if(targetState == State.STATE_ONE){
			setStateOne();
		}else if(targetState == State.STATE_TWO){
			setStateTwo();
		}
	}
	
	public abstract State getPhysicalState();
	public abstract State getLogicalState();
	
	protected abstract void setStateOne();
	protected abstract void setStateTwo();
	
	public static class State{
		public static final State STATE_ONE = new State(1);
		public static final State STATE_TWO = new State(2);
		public static final State STATE_UNKNOWN = new State(-1);
		
		private final int value;
		private State (int value){
			this.value = value;
		}
		
		public boolean equals(State state){
			return (value == state.value);
		}
	}
}