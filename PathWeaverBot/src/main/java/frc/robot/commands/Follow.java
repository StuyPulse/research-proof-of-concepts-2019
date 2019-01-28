// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.commands;

// import edu.wpi.first.wpilibj.command.Command;
// import frc.robot.Robot;
// import frc.robot.RobotMap;

// import java.io.File;
// import jaci.pathfinder.Pathfinder;
// import jaci.pathfinder.Trajectory;
// import jaci.pathfinder.followers.EncoderFollower;
// import edu.wpi.first.wpilibj.Notifier;
// import edu.wpi.first.wpilibj.command.Command;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// public class Follow extends Command {
//   EncoderFollower leftFollower; 
// 	EncoderFollower rightFollower; 
	
// 	File leftCSV; 
// 	File rightCSV; 
	
// 	Trajectory leftTraj; 
// 	Trajectory rightTraj;

// 	Notifier profileProcessor; 
// 	double dt; 
//   public Follow(String leftFile, String rightFile) {
//     // Use requires() here to declare subsystem dependencies
//     requires(Robot.drivetrain);
//     leftCSV = new File("/home/lvuser/deploy/" + leftFile + ".csv");
//     rightCSV = new File("/home/lvuser/deploy/" + rightFile + ".csv");
//     leftTraj = Pathfinder.readFromCSV(leftCSV);
//     rightTraj = Pathfinder.readFromCSV(rightCSV);
//     //System.out.println("CSV has been locked and loaded"); 
//     profileProcessor = new Notifier(new RunProfile());
//     dt = leftTraj.get(0).dt; 
//   }

//   // Called just before this Command runs the first time
//   @Override
//   protected void initialize() {
//     leftFollower = new EncoderFollower(leftTraj);
//     rightFollower = new EncoderFollower(rightTraj);
//     leftFollower.reset();
//     rightFollower.reset();
//     //Wheel diameter in feet
//     leftFollower.configureEncoder((int) Robot.drivetrain.getLeftEncoderTicks(), RobotMap.DRIVETRAIN_ENCODER_TICKS_PER_REVOLUTION, RobotMap.DRIVETRAIN_WHEEL_DIAMETER / 12);
//     rightFollower.configureEncoder((int) Robot.drivetrain.getRightEncoderTicks(), RobotMap.DRIVETRAIN_ENCODER_TICKS_PER_REVOLUTION, RobotMap.DRIVETRAIN_WHEEL_DIAMETER / 12);
//     leftFollower.configurePIDVA(SmartDashboard.getNumber("kp", 0.0), SmartDashboard.getNumber("ki", 0), SmartDashboard.getNumber("kd", 0.0), RobotMap.kv, SmartDashboard.getNumber("ka", 0));
//     rightFollower.configurePIDVA(SmartDashboard.getNumber("kp", 0.0), SmartDashboard.getNumber("ki", 0), SmartDashboard.getNumber("kd", 0.0), RobotMap.kv, SmartDashboard.getNumber("ka", 0));
//     profileProcessor.startPeriodic(dt);
//   }

//   // Called repeatedly when this Command is scheduled to run
//   @Override
//   protected void execute() {
//   }

//   // Make this return true when this Command no longer needs to run execute()
//   @Override
//   protected boolean isFinished() {
//     return leftFollower.isFinished() && rightFollower.isFinished();
//   }

//   // Called once after isFinished returns true
//   @Override
//   protected void end() {
//     profileProcessor.stop();
//     Robot.drivetrain.stop();
//   }

//   // Called when another command which requires one or more of the same
//   // subsystems is scheduled to run
//   @Override
//   protected void interrupted() {
//     end();
//   }
//   class RunProfile implements java.lang.Runnable {
//     int segmentNumber = 0; 
//     @Override
//     public void run() {
//       double leftOutput = leftFollower.calculate((int) Robot.drivetrain.getLeftEncoderTicks());
//       double rightOutput = rightFollower.calculate((int) Robot.drivetrain.getRightEncoderTicks());
//       double gyroHeading = 0;
//       double desiredHeading = Pathfinder.r2d(leftFollower.getHeading());
//       //Pathfinder is counter-clockwise while gyro is clockwise so gyro heading is added
//       double angleDifference = Pathfinder.boundHalfDegrees(desiredHeading + gyroHeading);
//       //kg is the turn gain and is the p for the angle loop
//       double turn = 0.08 * (-1.0 / 80.0) * angleDifference;
//       Robot.drivetrain.tankDrive(leftOutput + turn, rightOutput - turn);
//       //System.out.println(segmentNumber + "-Left Power: " + (leftOutput + turn) + " Right Power: " + (rightOutput - turn));
//       segmentNumber++; 
//     }
//   }
// }
