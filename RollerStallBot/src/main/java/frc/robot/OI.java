/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.motorMove;
import frc.util.Gamepad;

public class OI {
    public OI() {
        Gamepad operatorGamepad = new Gamepad(0, Gamepad.GamepadSwitchMode.SWITCH_D);

        operatorGamepad.getRightButton().whenPressed(new motorMove());
    }
}