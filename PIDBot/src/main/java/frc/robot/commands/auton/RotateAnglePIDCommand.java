package frc.robot.commands.auton;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

public abstract class RotateAnglePIDCommand extends PIDCommand {

    PIDController rotatePIDController; 
    double angle;
    double gyroPIDOutput;

    public RotateAnglePIDCommand(double p, double i, double d, double angle){
        super(p, i, d);
        this.angle = angle;
    }

    @Override
    protected double returnPIDInput() {
        return rotatePIDController.get();
    }

    @Override
    protected void usePIDOutput(double output) {
    }

    @Override 
    protected void initialize() {
        
    }

    @Override
	protected boolean isFinished() {
		return false;
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
