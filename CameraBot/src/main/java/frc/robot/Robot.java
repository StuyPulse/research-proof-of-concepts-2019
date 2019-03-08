/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.HashMap;
import java.util.Map;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.SendableCameraWrapper;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.utils.Gamepad;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private WPI_TalonSRX leftTopMotor = new WPI_TalonSRX(1);
  private WPI_TalonSRX rightTopMotor = new WPI_TalonSRX(3);
  private WPI_TalonSRX leftBottomMotor = new WPI_TalonSRX(2);
  private WPI_TalonSRX rightBottomMotor = new WPI_TalonSRX(4);
  private UsbCamera usbCamera;
  private Gamepad driverGamepad = new Gamepad(0);

  private DifferentialDrive differentialDrive;

  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private void updateSmartDashboard() {
    Map<String, Object> cameraStreamProperties = new HashMap<>();
    cameraStreamProperties.put("Show Crosshairs", true);
    cameraStreamProperties.put("Show Controls", true);
    Shuffleboard.getTab("Camera Stream")
        .add("Camera Stream", SendableCameraWrapper.wrap(new UsbCamera("Definitely Not The NSA", RobotMap.CAMERA_PATH)))
        .withWidget(BuiltInWidgets.kCameraStream)
        .withProperties(cameraStreamProperties);
}

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    //updateSmartDashboard();
    leftTopMotor.setNeutralMode(NeutralMode.Brake);
    rightTopMotor.setNeutralMode(NeutralMode.Brake);
    leftBottomMotor.setNeutralMode(NeutralMode.Brake);
    rightBottomMotor.setNeutralMode(NeutralMode.Brake);

    leftTopMotor.setInverted(true);
    rightTopMotor.setInverted(true);
    leftBottomMotor.setInverted(true);
    rightBottomMotor.setInverted(true);
    
    differentialDrive = new DifferentialDrive(leftTopMotor, rightTopMotor);
    //usbCamera = new UsbCamera("hi", 0);
    //CameraServer.getInstance().startAutomaticCapture(usbCamera);
    CameraServer.getInstance().startAutomaticCapture(0);

  }
  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    double rightTrigger = driverGamepad.getRawRightTriggerAxis();
    double leftTrigger = driverGamepad.getRawLeftTriggerAxis();

    //Values Used for Curvature Drive
    double rightTriggerSquared = rightTrigger * Math.abs(rightTrigger);
    double leftTriggerSquared = leftTrigger * Math.abs(leftTrigger);
    double leftJoystickX = driverGamepad.getLeftX();

    if (Math.abs(rightTrigger + leftTrigger) > 0.05) {
      differentialDrive.curvatureDrive(rightTriggerSquared - leftTriggerSquared, leftJoystickX, false);
    } else {
      differentialDrive.curvatureDrive(rightTriggerSquared - leftTriggerSquared, leftJoystickX, true);
    }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
