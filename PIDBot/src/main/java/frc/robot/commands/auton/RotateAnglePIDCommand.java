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

    public RotateAnglePIDCommand(double p, double i, double d, double angle){
        super(p, i, d);
        this.angle = angle;
    }

    @Override
    protected double returnPIDInput() {
        return gyroPIDController.get();
    }

    @Override
    protected void usePIDOutput(double output) {
        Robot.drivetrain.tankDrive(output, -output);
    }

    @Override 
    protected void initialize() {
        gyroPIDController = new PIDController (0, 0, 0, new GyroPIDSource(), new GyroPIDOutput());
        gyroPIDController.setSetpoint(angle);
        gyroPIDController.enable();
        usePIDOutput(returnPIDInput());
    }

    @Override
    protected void execute() {
        gyroPIDController.reset();
        gyroPIDController.enable();

    }

    @Override
	protected boolean isFinished() {
		return angle == Robot.drivetrain.getGyroAngle();
    }

    @Override
    protected void end() {
        Robot.drivetrain.stop();
        gyroPIDController.setPID(0, 0, 0);
        gyroPIDController.disable();
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
