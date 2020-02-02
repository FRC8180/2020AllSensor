/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  
  private final PIDController upperPID;
  private final PIDController lowerPID;
  private boolean upperPIDEnable = false;
  private boolean lowerPIDEnable = false;
  private double upperPIDSetpoint = 0;
  private double lowerPIDSetpoint = 0;

  private WPI_TalonSRX upperMotor;
  private WPI_TalonSRX lowerMotor;
  private Encoder upperEncoder;
  private Encoder lowerEncoder;
  private Timer timer;

  private double upperPreviousRotation = 0;
  private double upperPreviousTime = 0;
  private double lowerPreviousRotation = 0;
  private double lowerPreviousTime = 0;

  public Shooter() {
    upperPID = new PIDController(1.5, 0.15, 0.05);
    lowerPID = new PIDController(1.5, 0.15, 0.05);
    upperMotor = new WPI_TalonSRX(Constants.shooterUpperMotorID);
    lowerMotor = new WPI_TalonSRX(Constants.shooterLowerMotorID);
    upperMotor.setInverted(Constants.shooterUpperMotorInvert);
    lowerMotor.setInverted(Constants.shooterLowerMotorInvert);
    upperEncoder = new Encoder(Constants.shooterUpperEncoderPinA,Constants.shooterUpperEncoderPinB,Constants.shooterUpperEncoderDirectionInvert);
    lowerEncoder = new Encoder(Constants.shooterLowerEncoderPinA,Constants.shooterLowerEncoderPinB,Constants.shooterLowerEncoderDirectionInvert);
    timer = new Timer();
    timer.reset();
    timer.start();
  }

  @Override
  public void periodic() {
    if(upperPIDEnable){
      upperPIDOutput(upperPID.calculate(getUpperPIDMeasurment(), upperPIDSetpoint));
    }
    if(lowerPIDEnable){
      lowerPIDOutput(lowerPID.calculate(getLowerPIDMeasurment(), lowerPIDSetpoint));
    }
  }

  @Override
  public void setDefaultCommand(Command defaultCommand) {
    // TODO Auto-generated method stub
    super.setDefaultCommand(defaultCommand);
  }

  public void upperPIDEnable(){
    upperPIDEnable = true;
    upperPID.reset();
  }

  public void lowerPIDEnable(){
    lowerPIDEnable = true;
    lowerPID.reset();
  }

  public void upperPIDDisable(){
    upperPIDEnable = false;
    upperPIDOutput(0);
  }
  
  public void lowerPIDDisable(){
    lowerPIDEnable = false;
    lowerPIDOutput(0);
  }

  public void setUpperSetpoint(double setpoint){
    upperPIDSetpoint = setpoint;
  }

  public void setLowerSetpoint(double setpoint){
    lowerPIDSetpoint = setpoint;
  }

  public void upperPIDOutput(double output){
    upperMotor.setVoltage(output);
  }

  public void lowerPIDOutput(double output){
    lowerMotor.setVoltage(output);
  }

  public double getUpperPIDMeasurment(){
    double rotation = upperEncoder.get();
    double time = timer.get();
    double deltaRotation = rotation - upperPreviousRotation;
    double deltaTime = time - upperPreviousTime;
    upperPreviousRotation = rotation;
    upperPreviousTime = time;
    double RPS = deltaRotation / deltaTime / Constants.shooterUpperEncoderPPR;
    return RPS;
  }

  public double getLowerPIDMeasurment(){
    double rotation = lowerEncoder.get();
    double time = timer.get();
    double deltaRotation = rotation - lowerPreviousRotation;
    double deltaTime = time - lowerPreviousTime;
    lowerPreviousRotation = rotation;
    lowerPreviousTime = time;
    double RPS = deltaRotation / deltaTime / Constants.shooterLowerEncoderPPR;
    return RPS;
  }


  
}
