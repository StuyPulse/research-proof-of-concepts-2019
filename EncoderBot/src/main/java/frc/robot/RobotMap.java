/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class RobotMap {
    public static final double DRIVETRAIN_WHEEL_DIAMETER = 6.0;
    public static final int DRIVETRAIN_ENCODERS_PULSES_PER_REVOLUTION = 256;
    public static final int DRIVETRAIN_ENCODERS_FACTOR = 4;
    public static final double DRIVETRAIN_ENCODERS_INCHES_PER_REVOLUTION = Math.PI * DRIVETRAIN_WHEEL_DIAMETER;

    /**
     * DRIVETRAIN_RAW_MULTIPLIER: We multiply by 4 because the encoder has 4
     * Quadrants, and each Quadrant passes 256 pulses.
     **/

    // Extra factor imperically determined
    private static final double DRIVETRAIN_EMPERICAL_RAW_MULTIPLIER = (63.7 / 63.0) * 61.1 / ((463.544 + 461.814) / 2.0);//163/1246.0;

    public static final double DRIVETRAIN_RAW_MULTIPLIER = 
            DRIVETRAIN_EMPERICAL_RAW_MULTIPLIER * DRIVETRAIN_ENCODERS_INCHES_PER_REVOLUTION
            / (DRIVETRAIN_ENCODERS_PULSES_PER_REVOLUTION * DRIVETRAIN_ENCODERS_FACTOR);
    public static final int DRIVETRAIN_ENCODER_TICKS_PER_REVOLUTION = 1024; 
    
    /**
     * Motion Profile Constants
     */
    public static final double maxVelocity = 17.5; 
    public static final double kv = 1 / maxVelocity;
}
