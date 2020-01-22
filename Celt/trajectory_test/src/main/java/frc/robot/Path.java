package frc.robot;

import java.util.List;

import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;

import frc.robot.Constants.ChassisConstants;
import frc.robot.Constants.AutoConstants;

public final class Path{
    // Voltage constraint
    private static final DifferentialDriveVoltageConstraint autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
        new SimpleMotorFeedforward(ChassisConstants.ksVolt, ChassisConstants.kvVolt, ChassisConstants.kaVolt),
        ChassisConstants.m_ChassisKinematics,
        10
    );

    // Config for trajectory
    private static final TrajectoryConfig config = new TrajectoryConfig(AutoConstants.MaxSpeed, AutoConstants.MaxAccel)
        // Add kinematics to ensure max speed is actually obeyed
        .setKinematics(ChassisConstants.m_ChassisKinematics)
        // Apply the voltage constraint
        .addConstraint(autoVoltageConstraint);

    public Trajectory ExampleTrajectory(){
        // An example trajectory
        Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
            new Pose2d(0, 0, new Rotation2d(0)),
            List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
            new Pose2d(3, 0, new Rotation2d(0)),
            config);
        return exampleTrajectory;
    }
}