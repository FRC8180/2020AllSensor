/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants;
import frc.robot.Instrum;
import frc.robot.Robot;

import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
public class Chassis extends SubsystemBase {
  StringBuilder _sb = new StringBuilder();
  private WPI_TalonSRX ChassisLeft;
  private WPI_TalonSRX ChassisRight;
  private WPI_TalonSRX Follower1;
  private WPI_TalonSRX Follower2;
  public Chassis() {
   ChassisLeft = new WPI_TalonSRX(Constants.chassisLiftTalonSRX);
   ChassisRight = new WPI_TalonSRX(Constants.chassisRightTalonSRX);
   Follower1 = new WPI_TalonSRX(Constants.chassisFollower1TalonSRX);
   Follower2 = new WPI_TalonSRX(Constants.chassisFolower2TalonSRX);
   ChassisLeft.configFactoryDefault();
   ChassisRight.configFactoryDefault();
   Follower1.configFactoryDefault();
   Follower2.configFactoryDefault();
   ChassisRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx,
   Constants.kTimeoutMs);
   ChassisLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx,
   Constants.kTimeoutMs);
   ChassisLeft.configNeutralDeadband(0.001, Constants.kTimeoutMs);
   ChassisRight.configNeutralDeadband(0.001, Constants.kTimeoutMs);
   
   ChassisLeft.setSensorPhase(false);
   ChassisLeft.setInverted(false);
   ChassisRight.setSensorPhase(false);
   ChassisRight.setInverted(false);
  
   ChassisLeft.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
   ChassisRight.setStatusFramePeriod(StatusFrameEnhanced.Status_14_Turn_PIDF1, 10, Constants.kTimeoutMs);

   ChassisLeft.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);
   ChassisRight.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);
   
   ChassisLeft.configNominalOutputForward(0, Constants.kTimeoutMs);
   ChassisLeft.configNominalOutputReverse(0, Constants.kTimeoutMs);
   ChassisLeft.configPeakOutputForward(1, Constants.kTimeoutMs);
   ChassisLeft.configPeakOutputReverse(-1, Constants.kTimeoutMs);

   ChassisRight.configNominalOutputForward(0, Constants.kTimeoutMs);
   ChassisRight.configNominalOutputReverse(0, Constants.kTimeoutMs);
   ChassisRight.configPeakOutputForward(1, Constants.kTimeoutMs);
   ChassisRight.configPeakOutputReverse(-1, Constants.kTimeoutMs);

   ChassisLeft.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
   ChassisLeft.config_kF(Constants.kSlotIdx, Constants.kGains.kF, Constants.kTimeoutMs);
   ChassisLeft.config_kP(Constants.kSlotIdx, Constants.kGains.kP, Constants.kTimeoutMs);
   ChassisLeft.config_kI(Constants.kSlotIdx, Constants.kGains.kI, Constants.kTimeoutMs);
   ChassisLeft.config_kD(Constants.kSlotIdx, Constants.kGains.kD, Constants.kTimeoutMs);

   ChassisRight.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
   ChassisRight.config_kF(Constants.kSlotIdx, Constants.kGains.kF, Constants.kTimeoutMs);
   ChassisRight.config_kP(Constants.kSlotIdx, Constants.kGains.kP, Constants.kTimeoutMs);
   ChassisRight.config_kI(Constants.kSlotIdx, Constants.kGains.kI, Constants.kTimeoutMs);
   ChassisRight.config_kD(Constants.kSlotIdx, Constants.kGains.kD, Constants.kTimeoutMs);
  
   ChassisLeft.configMotionCruiseVelocity(15000, Constants.kTimeoutMs);
	 ChassisLeft.configMotionAcceleration(6000, Constants.kTimeoutMs);
   ChassisRight.configMotionCruiseVelocity(15000, Constants.kTimeoutMs);
	 ChassisRight.configMotionAcceleration(6000, Constants.kTimeoutMs);
 
   
   ChassisLeft.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
   ChassisRight.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
  
  }

  @Override
  public void periodic() {
    double leftYstick =Robot.m_oi.getRawLY()*-1.0;
    double rightYstick = Robot.m_oi.getRawRY()*-1.0;
    if (Math.abs(leftYstick)<0.10){leftYstick=0;}
    if (Math.abs(rightYstick)<0.10){rightYstick=0;}
    // This method will be called once per scheduler run
    double motorOutput = ChassisLeft.getMotorOutputPercent();
    
    _sb.append("\tOut%:");
		_sb.append(motorOutput);
		_sb.append("\tVel:");
		_sb.append(ChassisLeft.getSelectedSensorVelocity(Constants.kPIDLoopIdx));


    if (Robot.m_oi.getRawButton(Constants.buttonA)){
      double targetPos =rightYstick*4096*10.0;
      ChassisLeft.set(ControlMode.MotionMagic,targetPos);
      _sb.append("\terr:");
     _sb.append(ChassisLeft.getClosedLoopError(Constants.kPIDLoopIdx));
     _sb.append("\ttrg:");
     _sb.append(targetPos);
    }
    else {
      ChassisLeft.set(ControlMode.PercentOutput,leftYstick);
    }
    if (Robot.m_oi.getRawButton(Constants.buttonB)){
      ChassisLeft.setSelectedSensorPosition(0);
    }
    
    int pov = Robot.m_oi.getRawPOV();
    if (Constants._pov==pov){
    }
    else if (Constants._pov==0){
      Constants._smoothing = Constants._smoothing+1;
      if (Constants._smoothing>8){
        Constants._smoothing=8;
        ChassisLeft.configMotionSCurveStrength(Constants._smoothing);
        System.out.println(Constants._smoothing);
      }
       Constants._pov=pov;

       Instrum.Process(ChassisLeft, _sb);
    }
  }

 

  @Override
  public void setDefaultCommand(Command defaultCommand) {
    // TODO Auto-generated method stub
    super.setDefaultCommand(defaultCommand);
  }
}
