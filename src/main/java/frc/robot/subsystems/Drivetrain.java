/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.*;
/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class Drivetrain extends Subsystem {
  // Drivetrain Motors
  private CANSparkMax leftTopMotor,
                      rightTopMotor,
                      leftMiddleMotor,
                      rightMiddleMotor,
                      leftBottomMotor,
                      rightBottomMotor;
  public static AHRS navX;
  private CANEncoder leftEncoder;
  private CANEncoder rightEncoder;
  private DifferentialDrive differentialDrive;
  public Drivetrain() {
    leftTopMotor = new CANSparkMax(3, MotorType.kBrushless);
    rightTopMotor = new CANSparkMax(6, MotorType.kBrushless);
    leftMiddleMotor = new CANSparkMax(2, MotorType.kBrushless);
    rightMiddleMotor = new CANSparkMax(5, MotorType.kBrushless);
    leftBottomMotor = new CANSparkMax(3, MotorType.kBrushless);
    rightBottomMotor = new CANSparkMax(4, MotorType.kBrushless);
    
    leftMiddleMotor.follow(leftBottomMotor);
    leftTopMotor.follow(leftBottomMotor);
    rightMiddleMotor.follow(rightBottomMotor);
    rightTopMotor.follow(rightBottomMotor);

    rightTopMotor.setInverted(true);
    rightMiddleMotor.setInverted(true);
    rightBottomMotor.setInverted(true);
    leftTopMotor.setInverted(true);
    leftMiddleMotor.setInverted(true);
    leftBottomMotor.setInverted(true);

    leftEncoder = leftBottomMotor.getEncoder();
    rightEncoder = rightBottomMotor.getEncoder();
    /// Brake Mode
    leftTopMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    leftMiddleMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    leftBottomMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    rightTopMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    rightMiddleMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    rightBottomMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);

    navX = new AHRS(SPI.Port.kMXP);
    differentialDrive = new DifferentialDrive(leftBottomMotor, rightBottomMotor);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public void tankDrive(double left, double right){
    differentialDrive.tankDrive(left, right);
  }
  public void stop(){
    differentialDrive.tankDrive(0, 0);
  }
  public double getGyroAngle() {
    return navX.getAngle();
  }
}
