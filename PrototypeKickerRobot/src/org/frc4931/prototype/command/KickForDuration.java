/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype.command;

import org.frc4931.prototype.Robot;

/**
 * Controls the drive train to make the robot kick at the specified fraction of full power for the specified number of seconds.
 */
public class KickForDuration extends CommandBase {

    private final double timeInSeconds;

    /**
     * Create a new command to kick for the currently-set duration.
     */
    public KickForDuration() {
        this.timeInSeconds = Robot.driveTrain.getKickDurationInSeconds();
        setTimeout(timeInSeconds);
        requires(Robot.driveTrain);
    }

    protected void initialize() {
        Robot.print(toString());
    }

    protected void execute() {
        Robot.driveTrain.driveStraight(1.0d);
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        // Do nothing
    }

    public String toString() {
        return "Kick for " + timeInSeconds + " seconds";
    }

}
