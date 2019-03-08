package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.kauailabs.navx.frc.AHRS;

public class Drivetrain extends Subsystem {

    private static final double DRIVETRAIN_EMPIRICAL_RAW_MULTIPLIER = (63.7 / 63.0) * 61.1 / ((463.544 + 461.814) / 2.0);
    private static final double DRIVETRAIN_ENCODERS_INCHES_PER_REVOLUTION = Math.PI * 6.0;
    public static final double DRIVETRAIN_RAW_MULTIPLIER = DRIVETRAIN_EMPIRICAL_RAW_MULTIPLIER * DRIVETRAIN_ENCODERS_INCHES_PER_REVOLUTION / 1024;

    private CANSparkMax leftTopMotor,
                        leftMiddleMotor,
                        leftBottomMotor,
                        rightTopMotor,
                        rightMiddleMotor,
                        rightBottomMotor;

    private CANEncoder leftEncoder;
    private CANEncoder rightEncoder;

    private DifferentialDrive differentialDrive;

    public static AHRS navX;

    private Solenoid gearShift;

    public Drivetrain() {
        leftTopMotor = new CANSparkMax(3, MotorType.kBrushless);
        leftMiddleMotor = new CANSparkMax(2, MotorType.kBrushless);
        leftBottomMotor = new CANSparkMax(1, MotorType.kBrushless);
        rightTopMotor = new CANSparkMax(6, MotorType.kBrushless);
        rightMiddleMotor = new CANSparkMax(5, MotorType.kBrushless);
        rightBottomMotor = new CANSparkMax(4, MotorType.kBrushless);

        leftEncoder = leftBottomMotor.getEncoder();
        rightEncoder = rightBottomMotor.getEncoder();

        leftMiddleMotor.follow(leftBottomMotor);
        leftTopMotor.follow(leftBottomMotor);
        rightBottomMotor.follow(rightBottomMotor);
        rightTopMotor.follow(rightBottomMotor);

        leftTopMotor.setInverted(true);
        leftMiddleMotor.setInverted(true);
        leftBottomMotor.setInverted(true);
        rightTopMotor.setInverted(false);
        rightMiddleMotor.setInverted(false);
        rightBottomMotor.setInverted(false);

        leftTopMotor.setIdleMode(IdleMode.kBrake);
        leftMiddleMotor.setIdleMode(IdleMode.kBrake);
        leftBottomMotor.setIdleMode(IdleMode.kBrake);
        rightTopMotor.setIdleMode(IdleMode.kBrake);
        rightMiddleMotor.setIdleMode(IdleMode.kBrake);
        rightBottomMotor.setIdleMode(IdleMode.kBrake);

        differentialDrive = new DifferentialDrive(leftBottomMotor, rightBottomMotor);

        navX = new AHRS(SPI.Port.kMXP);

        gearShift = new Solenoid(7);
    }

    public double getMaxEncoder() {
        double left = getLeftEncoderDistance();
        double right = getRightEncoderDistance();

        if (Math.abs(left) > Math.abs(right)) {
            return left;
        } else {
            return right;
        }
    }

    public double getLeftRawEncoderDistance() {
        return leftEncoder.getPosition();
    }

    public double getRightRawEncoderDistance() {
        return rightEncoder.getPosition();
    }

    public double getLeftEncoderDistance() {
        return DRIVETRAIN_RAW_MULTIPLIER * leftEncoder.getPosition();
    }

    public double getRightEncoderDistance() {
        return DRIVETRAIN_RAW_MULTIPLIER * rightEncoder.getPosition();
    }

    public void tankDrive (double left, double right) {
        differentialDrive.tankDrive(left, right);
    }

    public void stop() {
        differentialDrive.tankDrive(0, 0);
    }

    public double getGyroAngle() {
        return navX.getAngle();
    }

    public void resetGyro() {
        navX.reset();
    }

    public void highGearShift() {
        gearShift.set(false);
    }

    public void lowGearShift() {
        gearShift.set(true);
    }

	@Override
	protected void initDefaultCommand() {
		
	}
}