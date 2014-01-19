package org.frc4931.zach.diagnosticrobot.drive;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.tables.ITable;

public class Motor implements Sendable{
	public SpeedController controller;
	private int channel;
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
		}
	}
	public void setSpeed(double speed){
		controller.set(speed);
	}
	public double getSpeed(){
		return controller.get();
	}
	
	/*SmartDashboard Information*/
	private String type;
	private ITable table;
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
