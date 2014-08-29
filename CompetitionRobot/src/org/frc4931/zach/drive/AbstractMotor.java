package org.frc4931.zach.drive;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.tables.ITable;
/**
 * An abstract motor class
 * @author zach
 *
 */
public abstract class AbstractMotor implements Sendable{
	protected final SpeedController controller;
	private final int channel;
	
	private ITable table;
	
	public AbstractMotor(int channel, SpeedControllerType type){
		this.channel = channel;
		if(type.equals(SpeedControllerType.TALON)){
			controller = new Talon(channel);
		}else if(type.equals(SpeedControllerType.VICTOR)){
			controller = new Victor(channel);
		}else if(type.equals(SpeedControllerType.JAGUAR)){
			controller = new Jaguar(channel);
		}else{
			controller = null;
			throw new NullPointerException("Motor type is null");
		}
	}
	
	/**
	 * Gets the current speed of the motor.
	 * @return the speed of the motor from -1.0 to 1.0.
	 */
	public double getSpeed(){
		return controller.get();
	}
	
	/**
	 * Gets the SpeedController of this motor.
	 * @return The SpeedController of this motor.
	 */
	public SpeedController getController(){
		return controller;
	}
	
	/**
	 * Stops this motor.
	 */
	public final void stop(){
		controller.set(0.0);
	}
	
	/*SmartDashboard Information*/
	public String getSmartDashboardType() {
		return "Motor";
	}

	public ITable getTable() {
		return table;
	}

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
