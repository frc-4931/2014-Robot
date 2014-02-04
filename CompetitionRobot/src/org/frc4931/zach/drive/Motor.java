package org.frc4931.zach.drive;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.tables.ITable;
/**
 * A class that represents a physical motor and its associated speed controller
 * @author zach
 *
 */
public class Motor implements Sendable{
	private final SpeedController controller;
	private final int channel;
	
	private final String type;
	private ITable table;
	
	private final DigitalInput upperLim;
	private final DigitalInput lowerLim;
	
	/**
	 * Creates a motor class of the specified type on the given channel.
	 * @param channel the PWM output that the motor controller is connected to.
	 * @param type the name displayed in the SmartDashboard.  
	 * It must contain the case insensitive string "talon", "jaguar", 
	 * or "victor" depending on the type of the motor controller.
	 */
	public Motor(int channel, String type){
		this.type = type;
		this.channel = channel;
		if(type.toLowerCase().indexOf("talon")!=-1){
			System.out.println("Created Talon on channel "+channel);
			controller = new Talon(channel);
		}else if(type.toLowerCase().indexOf("jaguar")!=-1){
			System.out.println("Created Jaguar on channel "+channel);
			controller = new Jaguar(channel);
		}else if(type.toLowerCase().indexOf("victor")!=-1){
			System.out.println("Created Victor on channel "+channel);
			controller = new Victor(channel);
		}else{
			controller = null;
		}
		upperLim = null;
		lowerLim = null;
	}
	
	public Motor(int channel, String type, DigitalInput upperLimitSwitch, DigitalInput lowerLimitSwitch){
		this.type = type;
		this.channel = channel;
		if(type.toLowerCase().indexOf("talon")!=-1){
			System.out.println("Created Talon on channel "+channel);
			controller = new Talon(channel);
		}else if(type.toLowerCase().indexOf("jaguar")!=-1){
			System.out.println("Created Jaguar on channel "+channel);
			controller = new Jaguar(channel);
		}else if(type.toLowerCase().indexOf("victor")!=-1){
			System.out.println("Created Victor on channel "+channel);
			controller = new Victor(channel);
		}else{
			controller = null;
		}
		upperLim = upperLimitSwitch;
		lowerLim = lowerLimitSwitch;
	}
	
	/**Set the speed of the motor as a double between -1.0 and 1.0.
	 * 
	 * @param speed the speed to set the motor to, between -1.0 and 1.0.
	 */
	public void setSpeed(double speed){
		controller.set(speed);
	}
	
	/**
	 * Returns the current speed of the motor as a double from -1.0 to 1.0.
	 * @return the speed of the motor from -1.0 to 1.0.
	 */
	public double getSpeed(){
		return controller.get();
	}
	
	/**
	 * Returns the class that represents the physical motor controller.
	 * @return the SpeedController that is attached to the motor.
	 */
	public SpeedController getController(){
		return controller;
	}
	
	public boolean isMax(){
		return upperLim.get();
	}
	
	public boolean isMin(){
		return lowerLim.get();
	}
	
	public void stop(){
		setSpeed(0);
	}
	
	/*SmartDashboard Information*/
	//@Override
	public String getSmartDashboardType() {
		return type;
	}
	//@Override
	public ITable getTable() {
		return table;
	}
	//@Override
	public void initTable(ITable table) {
		 this.table = table;
		 if (table != null) {
			 table.putNumber("Speed", getSpeed());
			 table.putNumber("Channel", channel);
		 }
	}
}
