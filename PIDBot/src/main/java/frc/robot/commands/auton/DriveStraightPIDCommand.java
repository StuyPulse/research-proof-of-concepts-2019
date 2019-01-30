package frc.robot.commands.auton;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class DriveStraightPIDCommand extends PIDCommand{

    public PIDController speedPIDController;
    double target;

    /*
    * Constructor of the command
    * @param p = p value
             i = i value
             d = d value
             target = target distance (in inches)    
    */
    public DriveStraightPIDCommand(double p, double i, double d, double target) {
        super(p, i, d);
        this.target = target / Robot.drivetrain.DRIVETRAIN_RAW_MULTIPLIER;
    }

    /*
    * Has the PIDController return its value
    * @return returns the value the pid controller returns after it is given PID values
    */
    @Override
    protected double returnPIDInput() {
        return speedPIDController.get();
    }

    /*
    * Puts the input into tankDrive to have the robot move at different speeds
    * @param input = the value from the PIDController
    */
    @Override
    protected void usePIDOutput(double input) {
        Robot.drivetrain.tankDrive(input, input);
    }

    /*
    * Sets PIDController to the controller on motors
    * Sets smartdashboard values
    * Enables the PIDController
    */
    protected void initialize(){
        speedPIDController = getPIDController(); 
        speedPIDController.setSetpoint(target);
        speedPIDController.setPID(SmartDashboard.getNumber("DriveStraight-P", 0), 
            SmartDashboard.getNumber("DriveStraight-I", 0),
            SmartDashboard.getNumber("DriveStraight-D", 0));
        speedPIDController.enable();
    }

    /*
    * Has the robot move when the command is executed
    * @see usePIDOutput
    * @see returnPIDInput
    */
    protected void execute(){
        usePIDOutput(returnPIDInput());
    }

    /*
    * Tells you whether or not the bot has reached the target area
    * @return returns whether or not the robot has moved close to the target area
    */
    protected boolean isFinished(){
        return(Math.abs(target - Robot.drivetrain.getMaxEncoder()) <= .1) ;
    }

    /*
    * Resets the PIDController once isFinished returns true
    * @see isFinished
    */
    protected void end(){
        Robot.drivetrain.stop();
        speedPIDController.setPID(0, 0, 0);
        speedPIDController.reset();
    }
}
