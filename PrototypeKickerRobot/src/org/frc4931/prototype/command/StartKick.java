/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype.command;

import org.frc4931.prototype.Robot;

/**
 * A command that is used to start the kick.
 */
public class StartKick extends CommandBase {

    public StartKick() {
        requires(Robot.driveTrain);
    }

    protected void initialize() {
        // Robot.printDebug(toString());
    }

    protected void execute() {
        Robot.driveTrain.driveStraight(1.0d);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
        // do nothing
    }

    public String toString() {
        return "Start kick (both motors at max power)";
    }
}
