/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.GenericHID.HIDType;
//import edu.wpi.first.wpilibj.buttons.JoystickButton;
//import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;


import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.DriverStation;


//import javax.annotation.meta.When;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * This is a simple example to show how the REV Color Sensor V3 can be used to
 * detect pre-configured colors.
 */
public class Robot extends TimedRobot {
  /**
   * Change the I2C port below to match the connection of your color sensor
   */
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  
  double motorSpeed = 0.5;
  int counter =0;
  String target;
  //Time spinnerTimer;
  private final WPI_TalonSRX Motor_spiinner = new WPI_TalonSRX(0);
  private final XboxController m_stick = new XboxController(0);

  /**
   * A Rev Color Sensor V3 object is constructed with an I2C port as a 
   * parameter. The device will be automatically initialized with default 
   * parameters.
   */
   private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

  /**
   * A Rev Color Match object is used to register and detect known colors. This can 
   * be calibrated ahead of time or during operation.
   * 
   * This object uses a simple euclidian distance to estimate the closest match
   * with given confidence range.
   */
  private final ColorMatch m_colorMatcher = new ColorMatch();

  /**
   * Note: Any example colors should be calibrated as the user needs, these
   * are here as a basic example.
   */
  private final Color kBlueTarget = ColorMatch.makeColor(0.219, 0.465, 0.315);// 143 427 429
  private final Color kGreenTarget = ColorMatch.makeColor(0.236, 0.483, 0.280);//197 561 240
  private final Color kRedTarget = ColorMatch.makeColor(0.269, 0.463, 0.265);// 561 232 114
  private final Color kYellowTarget = ColorMatch.makeColor(0.276, 0.489, 0.233);//361 524 113

  @Override
  public void robotInit() {
    counter =0;
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);    
  }

  @Override
  public void robotPeriodic() {
    /**
     * The method GetColor() returns a normalized color value from the sensor and can be
     * useful if outputting the color to an RGB LED or similar. To
     * read the raw color, use GetRawColor().
     * 
     * The color sensor works best when within a few inches from an object in
     * well lit conditions (the built in LED is a big help here!). The farther
     * an object is the more light from the surroundings will bleed into the 
     * measurements and make it difficult to accurately determine its color.
     */
    Color detectedColor = m_colorSensor.getColor();

    /**
     * Run the color match algorithm on our detected color
     */

     
    String gameData;
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    String colorString;
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    if(gameData.length() > 0 && m_stick.getRawButton(6)){
      switch (gameData.charAt(0)){
        case 'B' :
        while(match.color != kBlueTarget){
          Motor_spiinner.set(motorSpeed);
          detectedColor = m_colorSensor.getColor();
          match = m_colorMatcher.matchClosestColor(detectedColor);
  
        }
          Motor_spiinner.stopMotor();
          break;
        case 'G' :
        while(match.color != kGreenTarget){
          Motor_spiinner.set(motorSpeed);
          detectedColor = m_colorSensor.getColor();
          match = m_colorMatcher.matchClosestColor(detectedColor);
  
        }
          Motor_spiinner.stopMotor();
          break;
        case 'R' :
        while(match.color != kRedTarget){
          Motor_spiinner.set(motorSpeed);
          detectedColor = m_colorSensor.getColor();
          match = m_colorMatcher.matchClosestColor(detectedColor);
        }
          Motor_spiinner.stopMotor();
          break;
        case 'Y' :
        while(match.color != kYellowTarget){
          Motor_spiinner.set(motorSpeed);
          detectedColor = m_colorSensor.getColor();
          match = m_colorMatcher.matchClosestColor(detectedColor);
  
        }
          Motor_spiinner.stopMotor();
          break;
        default :
          Motor_spiinner.stopMotor();
          break;
      }
    }
    if(m_stick.getRawButton(5)){
      //spinnerTimer.wait();
      counter = 0;
      if (match.color == kBlueTarget) {
        colorString = "Blue";
  
      } else if (match.color == kRedTarget) {
        colorString = "Red";
  
      } else if (match.color == kGreenTarget) {
        colorString = "Green";
  
      } else if (match.color == kYellowTarget) {
        colorString = "Yellow";
  
      } else {
        colorString = "Unknown";
      }
      target = colorString;
      while(counter<13){

        detectedColor = m_colorSensor.getColor();
        match = m_colorMatcher.matchClosestColor(detectedColor);
        if (match.color == kBlueTarget) {
          colorString = "Blue";
    
        } else if (match.color == kRedTarget) {
          colorString = "Red";
    
        } else if (match.color == kGreenTarget) {
          colorString = "Green";
    
        } else if (match.color == kYellowTarget) {
          colorString = "Yellow";
    
        } else {
          colorString = "Unknown";
        }
        Motor_spiinner.set(motorSpeed);
        if(colorString != target){
          SmartDashboard.putNumber("Red", detectedColor.red);
          SmartDashboard.putNumber("Green", detectedColor.green);
          SmartDashboard.putNumber("Blue", detectedColor.blue);
          SmartDashboard.putNumber("Confidence", match.confidence);
          SmartDashboard.putString("Detected Color", colorString);
          counter++;
          SmartDashboard.putNumber("counter",counter);
          detectedColor = m_colorSensor.getColor();
          match = m_colorMatcher.matchClosestColor(detectedColor);
          target = colorString;
        }
      }
     Motor_spiinner.stopMotor();

    }
    
    
    /*
    if (m_stick.getRawButton(2) && m_stick.getRawButton(6)){
      while(match.color != kRedTarget){
        Motor_spiinner.set(motorSpeed);
        detectedColor = m_colorSensor.getColor();
        match = m_colorMatcher.matchClosestColor(detectedColor);
      }
        Motor_spiinner.stopMotor();
    }
    if (m_stick.getRawButton(1) && m_stick.getRawButton(6)){
      while(match.color != kGreenTarget){
        Motor_spiinner.set(motorSpeed);
        detectedColor = m_colorSensor.getColor();
        match = m_colorMatcher.matchClosestColor(detectedColor);

      }
        Motor_spiinner.stopMotor();
    }
    if (m_stick.getRawButton(4) && m_stick.getRawButton(6)){
      while(match.color != kYellowTarget){
        Motor_spiinner.set(motorSpeed);
        detectedColor = m_colorSensor.getColor();
        match = m_colorMatcher.matchClosestColor(detectedColor);

      }
        Motor_spiinner.stopMotor();
    }  
    if (m_stick.getRawButton(3) && m_stick.getRawButton(6)){
      while(match.color != kBlueTarget){
        Motor_spiinner.set(motorSpeed);
        detectedColor = m_colorSensor.getColor();
        match = m_colorMatcher.matchClosestColor(detectedColor);

      }
        Motor_spiinner.stopMotor();
    }
    */
    
    if (match.color == kBlueTarget) {
      colorString = "Blue";

    } else if (match.color == kRedTarget) {
      colorString = "Red";

    } else if (match.color == kGreenTarget) {
      colorString = "Green";

    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";

    } else {
      colorString = "Unknown";
    }

    /**
     * Open Smart Dashboard or Shuffleboard to see the color detected by the 
     * sensor.
     */

     
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);
  }
}
