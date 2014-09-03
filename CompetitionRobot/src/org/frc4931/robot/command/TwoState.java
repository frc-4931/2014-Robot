package org.frc4931.robot.command;

public interface TwoState {
	public void setStateOne(double speed);
	public void setStateTwo(double speed);
	public void stop();
	
	public State getPhysicalState();
	public State getLogicalState();
	
	public String getName();
	public boolean isContinous();
	public static class State{
		public static final State ONE = new State(1);
		public static final State TWO = new State(2);

		public static final State LOW = ONE;
		public static final State HIGH = TWO;
		
		public static final State UP = ONE;
		public static final State DOWN = TWO;

		public static final State IN = ONE;
		public static final State OUT = TWO;

		public static final State CLOSED = ONE;
		public static final State OPEN = TWO;
		
		public static final State UNKNOWN = new State(-1);
		private final int value;
		
		private State(int value){
			this.value = value;
		}
		
		public boolean equals(State s){
			if(s==null) return false;
			if(s==this) return true;
			return (this.value==s.value);
		}
		
		public String toString(){
			return ""+value;
		}
	}
}
