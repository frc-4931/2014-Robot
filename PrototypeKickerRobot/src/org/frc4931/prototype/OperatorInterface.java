/*
 * Copyright (c) FIRST 2008-2013. All Rights Reserved.
 * Open Source Software - may be modified and shared by FRC teams. The code
 * must be accompanied by the FIRST BSD license file in the root directory of
 * the project.
 */
package org.frc4931.prototype;

import org.frc4931.prototype.command.DecreaseKickDuration;
import org.frc4931.prototype.command.DecreaseMaxKickSpeed;
import org.frc4931.prototype.command.IncreaseKickDuration;
import org.frc4931.prototype.command.IncreaseMaxKickSpeed;
import org.frc4931.prototype.command.KickForDuration;
import org.frc4931.prototype.command.StartKick;
import org.frc4931.prototype.command.StopKick;
import org.frc4931.prototype.device.LogitechController;
import org.frc4931.prototype.device.LogitechController.DriveStyle;
import org.frc4931.prototype.device.LogitechController.Mode;

/**
 * This class is the glue that binds the controls on the physical operator interface to the commands and command groups that allow
 * control of the robot.
 * <p>
 * The single instance of this class is created as a static field in the {@link Robot#robotInit()} method.
 * </p>
 */
public final class OperatorInterface {

    private final LogitechController controller = new LogitechController("Controller", Robot.Controller.PORT, Mode.D,
                                                                         DriveStyle.TANK);

    public OperatorInterface() {
        // Run these commands each time the button is pressed ...

        /**
         * The buttons are as follows:
         * <ul>
         * <li>Button A DECREASES the maximum speed by {@link Robot.KickMotors.DELTA_MAX_KICK_SPEED 0.05}</li>
         * <li>Button B INCREASES the maximum speed by {@link Robot.KickMotors.DELTA_MAX_KICK_SPEED 0.05}</li>
         * <li>Press the right trigger to start the kick at the maximum speed, and release the trigger to stop</li>
         * <li>Button X DECREASES the kick duration by {@link Robot.KickMotors.DELTA_KICK_DURATION_IN_SECONDS 0.05 seconds}</li>
         * <li>Button Y INCREASES the kick duration by {@link Robot.KickMotors.DELTA_KICK_DURATION_IN_SECONDS 0.05 seconds}</li>
         * <li>Press the START button to start the kick at the current maximum speed for the current duration</li>
         * </ul>
         */
        controller.getAButton().whenPressed(new DecreaseMaxKickSpeed(Robot.KickMotors.DELTA_MAX_KICK_SPEED));
        controller.getBButton().whenPressed(new IncreaseMaxKickSpeed(Robot.KickMotors.DELTA_MAX_KICK_SPEED));
        controller.getXButton().whenPressed(new DecreaseKickDuration(Robot.KickMotors.DELTA_KICK_DURATION_IN_SECONDS));
        controller.getYButton().whenPressed(new IncreaseKickDuration(Robot.KickMotors.DELTA_KICK_DURATION_IN_SECONDS));
        controller.getRTButton().whenPressed(new StartKick());
        controller.getRTButton().whenReleased(new StopKick());
        controller.getStartButton().whenPressed(new KickForDuration());
    }

    public LogitechController getController() {
        return controller;
    }

}
