/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

//import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import frc.robot.commands.chassis.BasicDrive;
import frc.robot.commands.climber.BasicClimb;
import frc.robot.commands.intake.BasicIntake;
//import frc.robot.commands.shooter.BasicShoot;
import frc.robot.commands.spinner.BasicSpin;

import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Intake;
//import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Spinner;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final XboxController driverJoystick = new XboxController(0);
  
  // Subsystem defined here!!!
  private final Chassis m_chassis = new Chassis();
  private final Climber m_climber = new Climber();
  private final Intake  m_intake  = new Intake();
  //private final Shooter m_shooter = new Shooter();
  private final Spinner m_spinner = new Spinner();

  // Command defined here!!!
  //private final AutoMove m_autoCommand = new AutoMove(m_auto)
  private final BasicDrive m_basicDrive = new BasicDrive(m_chassis);
  private final BasicClimb m_basicClimb = new BasicClimb(m_climber);
  private final BasicIntake m_basicIntake = new BasicIntake(m_intake);
  //private final BasicShoot m_basicShoot = new BasicShoot(m_shooter);
  private final BasicSpin m_basicSpin = new BasicSpin(m_spinner);

  // Button defined here!!!
  private final JoystickButton buttonY = new JoystickButton(driverJoystick,Constants.buttonY);
  private final JoystickButton buttonA = new JoystickButton(driverJoystick,Constants.buttonA);
  private final JoystickButton buttonX = new JoystickButton(driverJoystick,Constants.buttonX);
  private final JoystickButton buttonB = new JoystickButton(driverJoystick,Constants.buttonB);
  
  private final JoystickButton buttonRB = new JoystickButton(driverJoystick,Constants.buttonRB);
  private final JoystickButton buttonLB = new JoystickButton(driverJoystick,Constants.buttonLB);
  private final JoystickButton buttonBack = new JoystickButton(driverJoystick,Constants.buttonBack);
  private final JoystickButton buttonOption = new JoystickButton(driverJoystick,Constants.buttonOption);
  private final JoystickButton buttonJR = new JoystickButton(driverJoystick,Constants.buttonJR);
  private final JoystickButton buttonJL = new JoystickButton(driverJoystick,Constants.buttonJL);

  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    // Set Default Command!
    //m_chassis.setDefaultCommand(m_basicDrive);
    //m_climber.setDefaultCommand();
    //m_intake.setDefaultCommand();
    //m_shooter.setDefaultCommand(m_basicShoot);
    //m_spinner.setDefaultCommand();
    //m_shooter.setDefaultCommand(m_basicShoot);
  }

  private void configureButtonBindings() {
    //Trig command defined here!!!!
    //buttonY.whenPressed(m_basicSpin);
    //buttonX.whenPressed(() -> m_chassis.(func));
  }

  /*
  public Command getAutonomousCommand() {
     //An ExampleCommand will run in autonomous
    //return m_autoCommand;
  }
  */
  
  
  public int getRawPOV(){
    return driverJoystick.getPOV();
  }

  public double getRawAxis(int AxisNumber){
    return driverJoystick.getRawAxis(AxisNumber);
  }

  public boolean getRawButton(int ButtonNumber){
    return driverJoystick.getRawButton(ButtonNumber);
  }

  public double getRawRX(){
    double rawData = getRawAxis(Constants.axisJRX);
    if(rawData > 0 && rawData < 1){
      rawData = Constants.axisRXScale * Math.pow(Math.abs(rawData),Constants.axisRXExp);
    }else if(rawData < 0 && rawData > -1){
      rawData = Constants.axisRXScale * -Math.pow(Math.abs(rawData),Constants.axisRXExp);
    }
    if(Constants.axisRXInvert){
      rawData = rawData * -1;
    }
    return rawData;
  }

  public double getRawRY(){
    double rawData = getRawAxis(Constants.axisJRY);
    if(rawData > 0 && rawData < 1){
      rawData = Constants.axisRYScale * Math.pow(Math.abs(rawData),Constants.axisRYExp);
    }else if(rawData < 0 && rawData > -1){
      rawData = Constants.axisRYScale * -Math.pow(Math.abs(rawData),Constants.axisRYExp);
    }
    if(Constants.axisRYInvert){
      rawData = rawData * -1;
    }
    return rawData;
  }

  public double getRawLX(){
    double rawData = getRawAxis(Constants.axisJLX);
    if(rawData > 0 && rawData < 1){
      rawData = Constants.axisLXScale * Math.pow(Math.abs(rawData),Constants.axisLXExp);
    }else if(rawData < 0 && rawData > -1){
      rawData = Constants.axisLXScale * -Math.pow(Math.abs(rawData),Constants.axisLXExp);
    }
    if(Constants.axisLXInvert){
      rawData = rawData * -1;
    }
    return rawData;
  }

  public double getRawLY(){
    double rawData = getRawAxis(Constants.axisJLY);
    if(rawData > 0 && rawData < 1){
      rawData = Constants.axisLYScale * Math.pow(Math.abs(rawData),Constants.axisLYExp);
    }else if(rawData < 0 && rawData > -1){
      rawData = Constants.axisLYScale * -Math.pow(Math.abs(rawData),Constants.axisLYExp);
    }
    if(Constants.axisLYInvert){
      rawData = rawData * -1;
    }
    return rawData;
  }

  public void SetRumble(double Intensity){
    driverJoystick.setRumble(RumbleType.kLeftRumble, Intensity);
    driverJoystick.setRumble(RumbleType.kRightRumble, Intensity);
  }

  public void SetRumble(double leftIntensity,double rightIntensity){
    driverJoystick.setRumble(RumbleType.kLeftRumble, leftIntensity);
    driverJoystick.setRumble(RumbleType.kRightRumble, rightIntensity);
  }

}
