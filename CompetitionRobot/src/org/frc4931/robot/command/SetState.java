package org.frc4931.robot.command;

import org.frc4931.robot.CompetitionRobot;
import org.frc4931.robot.command.TwoState.State;

import edu.wpi.first.wpilibj.command.Subsystem;

public final class SetState extends CommandBase{
	private final TwoState object;
	
	private final TwoState.State targetState;
	
	private final double speed;
	
	public SetState(TwoState object, TwoState.State targetState) {
		this(object, targetState, object.isContinous());
	}
	
	public SetState(TwoState object, TwoState.State targetState, double speed){
		this(object, targetState, speed, object.isContinous());
	}
	
	public SetState(TwoState object, TwoState.State targetState, boolean continuous) {
		this(object, targetState, 0, continuous);
	}
	
	public SetState(TwoState object, TwoState.State targetState, double speed, boolean continous){
		super(continous);
		this.object = object;
		this.targetState = targetState;
		this.speed = speed;
		
		if(object instanceof Subsystem){
			requires((Subsystem)object);
		}
	}
	
	protected void doExecute() {
		CompetitionRobot.output("Executing SetState on "+object.getName());
		if(targetState.equals(State.ONE)){
			object.setStateOne(speed);
		}else if(targetState.equals(State.TWO)){
			object.setStateTwo(speed);
		}
	}

	protected boolean isFinished() {
		CompetitionRobot.output(""+object.getPhysicalState().toString()+" "+object.getPhysicalState().equals(targetState));
		return object.getPhysicalState().equals(targetState);
	}
	
	protected void end(){
		object.stop();
		super.end();
	}
}
