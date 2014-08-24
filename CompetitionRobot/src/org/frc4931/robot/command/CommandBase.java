package org.frc4931.robot.command;

import org.frc4931.robot.CompetitionRobot;

import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command{
	protected CommandBase(){
		CompetitionRobot.output("Command <"+getName()+"> created.");
	}
	
	protected void initialize(){
		CompetitionRobot.output("Command <"+getName()+"> initialized.");
	}
	
	protected void execute(){
		CompetitionRobot.output("Command <"+getName()+"> executed.");
	}
	
	protected void interrupted() {
		CompetitionRobot.output("Command <"+getName()+"> canceled.");
		end();
	}
	
	protected void end() {
		CompetitionRobot.output("Command <"+getName()+"> has ended.");
	}
}
