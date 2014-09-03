package org.frc4931.robot.command;

import org.frc4931.robot.CompetitionRobot;
import org.frc4931.robot.command.TwoState.State;
import org.frc4931.robot.subsystems.ToggableSubsystem;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Toggles a specified ToggableSubsystem from one state to the other.
 * <p>
 * If continuous is false, <code>susbsytem.setState()</code> will only be called 
 * once, the first time this command is executed.  If it is true, 
 * <code>subsystem.setState()</code> will be called every time this command is executed.  
 * Regardless, this command not end until <code>subsystem</code> reports it is in the
 * correct state.
 * </p>
 * @author Zach Anderson
 *
 */
public class Toggle extends CommandBase{
	private final TwoState object;
	private final double speed;

	private TwoState.State initialState;
	private TwoState.State targetState;
	
	public Toggle(TwoState object){
		this(object, 0, object.isContinous());
	}
	
	public Toggle(TwoState object, double speed){
		this(object, speed, true);
	}
	/**
	 * Constructs a new command to toggle the specified {@link ToggableSubsystem}
	 * @param subsystem the subsystem to toggle.
	 * @param continuous the desired operation of this command as described above.
	 * @see Toggle
	 */
	public Toggle(TwoState object, boolean continuous){
		this(object, 0, continuous);
	}
	
	public Toggle(TwoState object, double speed, boolean continuous){
		super(continuous);
		this.object = object;
		this.speed = speed;
		
		if(object instanceof Subsystem){
			requires((Subsystem)object);
		}
	}
	
	protected void initialize(){
		initialState = object.getPhysicalState();
		
		CompetitionRobot.output("Initial state is "+initialState.toString());
		if(initialState.equals(State.UNKNOWN)){
			initialState = object.getLogicalState();
		}
		
		if(initialState.equals(State.ONE)){
			targetState = State.TWO;
		}else if(initialState.equals(State.TWO)){
			targetState = State.ONE;
		}else{
			CompetitionRobot.output("<"+object.getName()+"> state is unknown.");
			cancel();
		}
		CompetitionRobot.output(initialState.toString()+"Target state for "+object.getName()+" is "+targetState.toString());
		super.initialize();
	}
	
	protected void doExecute() {
		if(!object.getPhysicalState().equals(targetState)){
			if(targetState.equals(State.ONE)){
				CompetitionRobot.output("Setting state of "+object.getName() + "to one");
				object.setStateOne(speed);
			}else if(targetState.equals(State.TWO)){
				object.setStateTwo(speed);
				CompetitionRobot.output("Setting state of "+object.getName() + "to two");
			}
		}
	}

	protected boolean isFinished(){
		CompetitionRobot.output("Toggle Finished? "+object.getPhysicalState().toString()+", "+object.getPhysicalState().equals(targetState));
		return object.getPhysicalState().equals(targetState);
	}
	
	protected void end(){
		object.stop();
		super.end();
	}
}
