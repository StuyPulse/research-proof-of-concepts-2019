package frc.robot;

import frc.util.Gamepad;

public class OI {
	public Gamepad driverGamepad;
	public Gamepad operatorGamepad;
	
	public OI() {
		 driverGamepad = new Gamepad(RobotMap.DRIVER_GAMEPAD_PORT);
	     operatorGamepad = new Gamepad(RobotMap.OPERATOR_GAMEPAD_PORT);
    }
}