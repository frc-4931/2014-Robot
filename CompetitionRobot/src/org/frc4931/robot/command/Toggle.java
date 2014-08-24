package org.frc4931.robot.command;

import org.frc4931.robot.CompetitionRobot;
import org.frc4931.robot.subsystems.ToggableSubsystem;
import org.frc4931.robot.subsystems.ToggableSubsystem.State;

public class Toggle extends CommandBase{
	private final ToggableSubsystem subsystem;
	private ToggableSubsystem.State initialState;
	private ToggableSubsystem.State targetState;
	
	public Toggle(ToggableSubsystem subsystem){
		requires(subsystem);
		
		this.subsystem = subsystem;
		
		this.setInterruptible(true);
	}
	
	protected void initialize(){
		super.initialize();
		
		initialState = subsystem.getPhysicalState();
		
		if(initialState.equals(State.STATE_UNKNOWN)){
			initialState = subsystem.getLogicalState();
		}
		
		if(initialState.equals(State.STATE_ONE)){
			targetState = State.STATE_TWO;
		}else if(initialState.equals(State.STATE_TWO)){
			targetState = State.STATE_ONE;
		}else{
			CompetitionRobot.output("<"+subsystem.getName()+"> state is unknown.");
			cancel();
		}
	}

	protected void execute() {
		super.execute();
		if(!subsystem.getPhysicalState().equals(targetState)){
			subsystem.setState(targetState);
		}
	}

	protected boolean isFinished(){
		return subsystem.getPhysicalState().equals(targetState);
	}
}
