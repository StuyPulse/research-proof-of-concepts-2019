package frc.robot.commands.auton;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

public class pidCommand extends PIDCommand {

    private PIDController pidController;
    double target;

    public pidCommand(double p, double i, double d, double target){
        super(p, i, d);
        this.target = target / Robot.drivetrain.DRIVETRAIN_RAW_MULTIPLIER;
        setSetpoint(this.target);
        pidController = getPIDController(); 
    }

    @Override
    protected double returnPIDInput() {
        return pidController.get();
    }

    @Override
    protected void usePIDOutput(double output) {
        Robot.drivetrain.tankDrive(output, output);
    }

    @Override
    protected void initialize() {
        pidController.enable();

    }

    @Override
    protected void execute() {
        usePIDOutput(returnPIDInput());
    }

    @Override
    protected boolean isFinished() {
        return(Math.abs(target - Robot.drivetrain.getMaxEncoder()) <= .1) ;
    }

    @Override
    protected void end() {
        Robot.drivetrain.stop();
        pidController.disable();
    }
}