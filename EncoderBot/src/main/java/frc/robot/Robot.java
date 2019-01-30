/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.OI;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    public static Drivetrain drivetrain;
    public static OI oi;

    /**
     * This function is run when the robot is first started up and should be used
     * for any initialization code.
     */
    @Override
    public void robotInit() {
        drivetrain = new Drivetrain();
        oi = new OI();
        //Driver = new Gamepad(0, GamepadSwitchMode.SWITCH_X);
    }

    /**
     * This function is called every robot packet, no matter the mode. Use this for
     * items like diagnostics that you want ran during disabled, autonomous,
     * teleoperated and test.
     *
     * <p>
     * This runs after the mode specific periodic functions, but before LiveWindow
     * and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable chooser
     * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
     * remove all of the chooser code and uncomment the getString line to get the
     * auto name from the text box below the Gyro
     *
     * <p>
     * You can add additional auto modes by adding additional comparisons to the
     * switch structure below with additional strings. If using the SendableChooser
     * make sure to add them to the chooser code above as well.
     */
    @Override
    public void autonomousInit() {
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        /*
          double speed = 0;
           if(Driver.getRawRightTrigger() || Driver.getRawLeftTrigger()){
                if(Driver.getRawRightTrigger()){
                     speed = 0.2;
                } else{
                    speed = -0.2; 
                } 
            }
          drivetrain.curvatureDrive(speed, Math.pow(Driver.getLeftX(), 3));
         */
        
        // Preliminary Values from the triggers used for Curvature Drive
        double rightTrigger = oi.driverGamepad.getRawRightTriggerAxis();
        double leftTrigger = oi.driverGamepad.getRawLeftTriggerAxis();

        // Values Used for Curvature Drive
        double rightTriggerSquared = rightTrigger * Math.abs(rightTrigger);
        double leftTriggerSquared = leftTrigger * Math.abs(leftTrigger);
        double leftJoystickX = oi.driverGamepad.getLeftX();

        if (Math.abs(rightTrigger + leftTrigger) > 0.05) {
            Robot.drivetrain.curvatureDrive(rightTriggerSquared - leftTriggerSquared, leftJoystickX, false);
        } else {
            Robot.drivetrain.curvatureDrive(rightTriggerSquared - leftTriggerSquared, leftJoystickX, true);
        }
        if(oi.driverGamepad.getRawBottomButton()) {
            drivetrain.lowGearShift();
        } else {
            drivetrain.highGearShift();
        }
        // System.out.println("TOTAL DISTANCE: " + drivetrain.getDistance());
        // System.out.println("LeftBottom: " + drivetrain.getLeftDistance());
        // System.out.println("RightBottom: " + drivetrain.getRightDistance());
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }
}
