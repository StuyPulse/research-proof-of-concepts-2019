package frc.robot.util;

import frc.robot.RobotMap;
import edu.wpi.first.shuffleboard.SimpleWidget;

public class Odomentry {

    double x, y;
    double prevX, prevY;
    double distTravelled;
    double prevEncoderVal;
    double gyroAngle;

    public enum StartingPosition {

        LEFT(RobotMap.LEFT_STARTING_POSITION[0], RobotMap.LEFT_STARTING_POSITION[1]),
        RIGHT(RobotMap.RIGHT_STARTING_POSITION[0], RobotMap.RIGHT_STARTING_POSITION[1]),
        MIDDLE(RobotMap.MIDDLE_STARTING_POSITION[0], RobotMap.MIDDLE_STARTING_POSITION[1]);

        final double x;
        final double y;
        
        StartingPosition(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double x() { return x; }
        public double y() { return y; }

    }

    public Odomentry(StartingPosition pos) {
        this.x = pos.x();
        this.y = pos.y();
        
    }

    public double[] updateCoordinates() {
        distTravelled = Robot.drivetrain.getDistance() - prevEncoderVal;
        gyroAngle = Robot.drivetrain.getGyroAngle();
        x = updateX(distTravelled, gyroAngle);
        y = updateY(distTravelled, gyroAngle);
        prevEncoderVal = Robot.drivetrain.getDistance();
        return new double[] {x,y};
    }

    public double updateX(int dist, int angle) {
        return dist * Math.cos(angle);
    }

    public double updateY(int dist, int angle) {
        return dist * Math.sin(angle);
    }

}