/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype.command;

import org.frc4931.prototype.Robot;

/**
 * A abstract command used to increase or decrease the duration of the kick.
 * 
 * @see IncreaseMaxKickSpeed
 * @see DecreaseMaxKickSpeed
 */
public abstract class ChangeKickDuration extends CommandBase {

    protected final double deltaInSeconds;

    protected ChangeKickDuration( double deltaInSeconds ) {
        this.deltaInSeconds = deltaInSeconds;
        requires(Robot.driveTrain);
    }

    protected void initialize() {
        Robot.print(toString());
    }

    protected void execute() {
        Robot.driveTrain.changeKickDuration(deltaInSeconds);
    }

    protected boolean isFinished() {
        return true; // finishes immediately
    }

    protected void end() {
        // nothing to do
    }
}
