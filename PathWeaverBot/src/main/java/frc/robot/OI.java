/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.GearShiftCommand;
import frc.util.Gamepad;
import frc.util.Gamepad.GamepadSwitchMode;

/**
 * Add your docs here.
 */
public class OI {
    public Gamepad driverGamepad;
    public OI(){
        driverGamepad = new Gamepad(0, GamepadSwitchMode.SWITCH_X);
        System.out.println("l");
        System.out.println(driverGamepad.getDPadDown());

        driverGamepad.getDPadDown().whenPressed(new GearShiftCommand());
    }

}
