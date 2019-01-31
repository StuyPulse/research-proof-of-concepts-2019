package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Motor extends Subsystem {
    private WPI_TalonSRX motor;

    @Override
    public void initDefaultCommand() {
        
    }

    public Motor() {
        motor = new WPI_TalonSRX(RobotMap.MOTOR_PORT);
    }

    // public boolean isBallAcquired() {
    //     return motor.getOutputCurrent() > SmartDashboard.getNumber("Threshold (get)", Robot.threshold)/*RobotMap.CURRENT_BOUNDARY*/;
    // }

    // public double getOutputCurrent() {
    //     return motor.getOutputCurrent();
    // }

    public void move(double speed) {
        motor.set(speed);
    }
}
