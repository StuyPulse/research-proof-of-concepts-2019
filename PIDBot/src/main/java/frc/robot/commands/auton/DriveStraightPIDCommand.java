package frc.robot.commands.auton;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class DriveStraightPIDCommand extends PIDCommand{

    public PIDController speedPIDController;
    double target;

    public DriveStraightPIDCommand(double p, double i, double d, double target) {
        super(p, i, d);
        this.target = target / Robot.drivetrain.DRIVETRAIN_RAW_MULTIPLIER;
    }

    @Override
    protected double returnPIDInput() {
        return speedPIDController.get();
    }

    @Override
    protected void usePIDOutput(double output) {
        Robot.drivetrain.tankDrive(output, output);
    }

    protected void initialize(){
        speedPIDController = getPIDController(); 
        speedPIDController.setSetpoint(target);
        speedPIDController.setPID(SmartDashboard.getNumber("DriveStraight-P", 0), 
            SmartDashboard.getNumber("DriveStraight-I", 0),
            SmartDashboard.getNumber("DriveStraight-D", 0));
        speedPIDController.enable();
    }

    protected void execute(){
        usePIDOutput(returnPIDInput());
    }

    protected boolean isFinished(){
        return(Math.abs(target - Robot.drivetrain.getMaxEncoder()) <= .1) ;
    
    }

    protected void end(){
        Robot.drivetrain.stop();
        speedPIDController.setPID(0, 0, 0);
        speedPIDController.disable();
    }
}
