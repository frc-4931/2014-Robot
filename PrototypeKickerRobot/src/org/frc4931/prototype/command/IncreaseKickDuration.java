/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype.command;

/**
 * A command used to increase the duration of the kick.
 */
public class IncreaseKickDuration extends ChangeKickDuration {

    public IncreaseKickDuration( double delta ) {
        super(delta);
    }

    public String toString() {
        return "Increase the maximum kick duration by " + deltaInSeconds + " seconds";
    }
}
