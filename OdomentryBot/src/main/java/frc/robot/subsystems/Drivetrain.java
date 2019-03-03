package frc.robot.subsystems;

import com.kuailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.RobotMap;

public class Drivetrain {

    private Encoder left, right;
    private AHRS gyro;

    public Drivetrain() {
        left = new Encoder(RobotMap.LEFT_ENCODER);
        right = new Encoder(RobotMap.RIGHT_ENCODER);
        gyro = new AHRS(RobotMap.NAVX);
    }

    public double getDistance() {
        return (left.getDistance() + right.getDistance()) / 2;
    }

    public double getGyroAngle() {
        return gyro.getAngle();
    }
    
}
