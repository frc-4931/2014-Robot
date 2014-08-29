package org.frc4931.zach.drive;
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
	public static final int TALON_SPEED_CONTROLLER = 1;
	public static final int VICTOR_SPEED_CONTROLLER = 2;
	public static final int JAGUAR_SPEED_CONTROLLER = 3;
	
	protected final SpeedController controller;
	private final int channel;
	
	private final String type;
	private ITable table;
	
	/**
	 * Creates a motor class of the specified type on the given channel.
	 * @param channel the PWM output that the motor controller is connected to.
	 * @param type the name displayed in the SmartDashboard.  
	 * It must contain the case insensitive string "talon", "jaguar", 
	 * or "victor" depending on the type of the motor controller.
	 */
	public Motor(int channel, SpeedControllerType type){
		this.type = ""+type;
		this.channel = channel;
		if(type.equals(SpeedControllerType.TALON)){
			controller = new Talon(channel);
		}else if(type.equals(SpeedControllerType.VICTOR)){
			controller = new Victor(channel);
		}else if(type.equals(SpeedControllerType.JAGUAR)){
			controller = new Jaguar(channel);
		}else{
			controller = null;
		}
	}
	
	/**
	 * @deprecated
	 */
	public Motor(int channel, int type){
		this.type = ""+type;
		this.channel = channel;
		switch(type){
			case TALON_SPEED_CONTROLLER:
				controller = new Talon(channel);
				break;
			case VICTOR_SPEED_CONTROLLER:
				controller = new Victor(channel);
				break;
			case JAGUAR_SPEED_CONTROLLER:
				controller = new Jaguar(channel);
				break;
			default:
				controller = null;
				break;
		}
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
	public static class SpeedControllerType{
		public static final SpeedControllerType TALON = new SpeedControllerType(0);
		public static final SpeedControllerType VICTOR = new SpeedControllerType(1);
		public static final SpeedControllerType JAGUAR = new SpeedControllerType(2);
		
		private final int value;
		
		private SpeedControllerType(int value){
			this.value = value;
		}
		
		public boolean equals(SpeedControllerType motor){
			if(this == motor) return true;
			if(motor == null) return false;
			return this.value == motor.value;
		}
	}
}
