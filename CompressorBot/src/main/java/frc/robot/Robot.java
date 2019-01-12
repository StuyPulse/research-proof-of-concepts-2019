/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Compressor;
import frc.util.Gamepad;

public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private OI oi;
  private Gamepad driverGamepad;
  private Gamepad operatorGamepad;
  private Compressor compressor;

  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    operatorGamepad = new Gamepad(RobotMap.OPERATOR_GAMEPAD_PORT);
    driverGamepad = new Gamepad(RobotMap.DRIVER_GAMEPAD_PORT);
    compressor = new Compressor(0);

  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

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

  @Override
  public void teleopPeriodic() {
    //Press the right trigger of the gamepad to enable the compressor.
    //if(driverGamepad.getRawRightBumper()) {
      compressor.setClosedLoopControl(true);
      compressor.start();
     //System.out.println("Right working");
     System.out.println("Compressor Drive Current too high: " + compressor.getCompressorCurrentTooHighFault());
     System.out.println("Compressor Current too high: " + compressor.getCompressorCurrentTooHighStickyFault());
     System.out.println("Compressor not connected: " + compressor.getCompressorNotConnectedFault());
     System.out.println("Compressor (sticky) not connected: " + compressor.getCompressorNotConnectedStickyFault());
     System.out.println("Compressor shorted: " + compressor.getCompressorShortedFault());
     System.out.println("Compressor sticky connected: " + compressor.getCompressorShortedStickyFault());

    //}
    //System.out.println(driverGamepad.getRawRightBumper());
    //Press the left trigger of the gamepad to disable the compressor.
    //if(driverGamepad.getRawLeftBumper()) {
      //compressor.setClosedLoopControl(false);
      //compressor.stop();
      //System.out.println("left working");
    //}
    //Prints out a message to show the status of the compressor.
    System.out.println("Status of the Compressor: " + compressor.enabled());
    System.out.println("Pressure Switch Value " + compressor.getPressureSwitchValue());
  }

  @Override
  public void testPeriodic() {
  }
}
