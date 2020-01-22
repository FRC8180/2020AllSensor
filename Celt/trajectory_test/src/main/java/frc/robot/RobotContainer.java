/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import static edu.wpi.first.wpilibj.XboxController.Button;

import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.Chassis;
import frc.robot.Constants.ChassisConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.Constants.AutoConstants;
import frc.robot.Path;

public class RobotContainer {
  private final Chassis m_Chassis = new Chassis();
  //private final ExampleCommand m_autoCommand = new ExampleCommand(m_Chassis);
  private final Path m_path = new Path();

  XboxController m_Controller = new XboxController(OIConstants.Controller_Port);

  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_Chassis.setDefaultCommand(
      new RunCommand(() -> m_Chassis
        .arcadeDrive(m_Controller.getY(GenericHID.Hand.kLeft), m_Controller.getX(GenericHID.Hand.kRight)))
    );
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(m_Controller, Button.kBumperRight.value)
      .whenPressed(() -> m_Chassis.setMaxOutput(0.5))
      .whenReleased(() -> m_Chassis.setMaxOutput(1));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    Trajectory Traj = m_path.ExampleTrajectory();

    RamseteCommand ramseteCommand = new RamseteCommand(
      Traj,
      m_Chassis::getPose,
      new RamseteController(AutoConstants.Ramsete_B, AutoConstants.Ramsete_Zeta),
      new SimpleMotorFeedforward(ChassisConstants.ksVolt, ChassisConstants.kvVolt, ChassisConstants.kaVolt),
      ChassisConstants.m_ChassisKinematics,
      m_Chassis::getWheelSpeeds,
      new PIDController(ChassisConstants.Kp, ChassisConstants.Ki, ChassisConstants.Kd),
      new PIDController(ChassisConstants.Kp, ChassisConstants.Ki, ChassisConstants.Kd),
      // RamseteCommand passes volts to the callback
      m_Chassis::DriveByVolts,
      m_Chassis
    );
    return ramseteCommand.andThen(() -> m_Chassis.DriveByVolts(0, 0));
  }
}
