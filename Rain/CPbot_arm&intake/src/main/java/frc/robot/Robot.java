/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.XboxController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    double motorSpeed = 0.5 ;
    private final VictorSP Motor_spiinner = new VictorSP(0);
    private final WPI_TalonSRX Motor_test = new WPI_TalonSRX(11);
    private final XboxController m_stick = new XboxController(0);
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    if(m_stick.getRawButton(4)){
      Motor_spiinner.set(-0.5);
    }else if(m_stick.getRawButton(1)){
      Motor_spiinner.set(motorSpeed);
    }else if(m_stick.getRawAxis(5)>0.15||m_stick.getRawAxis(5)<-0.15){
      Motor_test.set(m_stick.getRawAxis(5));
    }else if(m_stick.getRawAxis(1)>0.2||m_stick.getRawAxis(1)<-0.2){
      Motor_spiinner.set(m_stick.getRawAxis(1) );
    }else{
      Motor_spiinner.stopMotor();
      Motor_test.stopMotor();
    }
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
  
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    
    
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
