package frc.robot.commands.auton;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

public abstract class RotateAnglePIDCommand extends PIDCommand {

    PIDController gyroPIDController; 
    double angle;
    double gyroPIDOutput;

    /*
    * Constructor for this command
    * @param p = p value
             i = i value
             d = d value
             angle = target angle degree (clockwise, use 270 instead of -90)
    */
    public RotateAnglePIDCommand(double p, double i, double d, double angle){
        super(p, i, d);
        this.angle = angle;
    }

    /*
    * Has the PIDController return its value
    * @return returns the PIDController's value
    */
    @Override
    protected double returnPIDInput() {
        return gyroPIDController.get();
    }

    /*
    * Puts the input into the tankdrive so it will turn
    */
    @Override
    protected void usePIDOutput(double output) {
        Robot.drivetrain.tankDrive(output, -output);
    }

    /*
    * Intitalizes a new PIDCcontroller
    * Sets targetpoint
    * Enables PIDController
    */
    @Override 
    protected void initialize() {
        gyroPIDController = new PIDController (0, 0, 0, new GyroPIDSource(), new GyroPIDOutput());
        gyroPIDController.setSetpoint(angle);
        gyroPIDController.enable();

    }

    /*
    * Has the drive train turn when executed
    * @see usePIDOutput
    * @see returnPIDInput
    */
    @Override
    protected void execute() {
        usePIDOutput(returnPIDInput());
    }

    /*
    * Returns whether the drivetrain reached the target angle
    * @return returns whether the drivetrain reached the target angle
    */
    @Override
	protected boolean isFinished() {
		return angle == Robot.drivetrain.getGyroAngle();
    }

    /*
    * Resets the pidcontroller when the command ends
    */
    @Override
    protected void end() {
        Robot.drivetrain.stop();
        gyroPIDController.setPID(0, 0, 0);
        gyroPIDController.reset();
    }

    protected abstract double getAngle();

    private class GyroPIDSource implements PIDSource {

        @Override
        public void setPIDSourceType(PIDSourceType pidSource) {
        }

        @Override
        public PIDSourceType getPIDSourceType() {
            return PIDSourceType.kDisplacement;
        }

        @Override
        public double pidGet() {
            return getAngle();
		}
    }

    private class GyroPIDOutput implements PIDOutput {

        @Override
        public void pidWrite(double output) {
            gyroPIDOutput = output;
        }
    }
}
