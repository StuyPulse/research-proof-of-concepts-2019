package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.RobotMap;

public class Motor extends Subsystem {
    private WPI_TalonSRX motor;

    public Motor() {
        motor = new WPI_TalonSRX(RobotMap.MOTOR_PORT);
    }

    public boolean isBallAcquired() {
        return motor.getOutputCurrent() > RobotMap.CURRENT_BOUNDARY;
    }
}
