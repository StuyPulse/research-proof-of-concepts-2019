package frc.util;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.util.Limelight;

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
        switch(pos) {
        case MIDDLE:
            this.x = pos.x();
            this.y = pos.y();
            break;
        case LEFT:
            break;
        }
    }

    private double[] calcOffsetFromL2Drop() {
        double horizOffset = NetworkTable.getInstance().getDefault().getTable("limelight").getEntry("tx").getDouble(0);
        double boxSize = NetworkTable.getInstance().getDefault().getTable("limelight").getEntry("tshort").getDouble(0);
        return new double[] {-1, -1};
    }

    public double[] updateCoordinates() {
        distTravelled = Robot.drivetrain.getDistance() - prevEncoderVal;
        gyroAngle = Robot.drivetrain.getGyroAngle();
        x = updateX(distTravelled, gyroAngle);
        y = updateY(distTravelled, gyroAngle);
        prevEncoderVal = Robot.drivetrain.getDistance();
        return new double[] {x,y};
    }

    public double updateX(double dist, double angle) {
        return dist * Math.cos(angle);
    }

    public double updateY(double dist, double angle) {
        return dist * Math.sin(angle);
    }

}
