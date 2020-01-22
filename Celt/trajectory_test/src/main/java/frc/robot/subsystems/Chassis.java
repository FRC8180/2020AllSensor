package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;

import frc.robot.Constants.ChassisConstants;

public class Chassis extends SubsystemBase {
  private final SpeedControllerGroup m_Motor_L = new SpeedControllerGroup(new PWMVictorSPX(ChassisConstants.Motor_LA), new PWMVictorSPX(ChassisConstants.Motor_LB));
  private final SpeedControllerGroup m_Motor_R = new SpeedControllerGroup(new PWMVictorSPX(ChassisConstants.Motor_RA), new PWMVictorSPX(ChassisConstants.Motor_RB));
  private final DifferentialDrive m_drive = new DifferentialDrive(m_Motor_L, m_Motor_R);
  
  private final Encoder m_Encoder_L = new Encoder(ChassisConstants.Encoder_L[0], ChassisConstants.Encoder_L[1], ChassisConstants.Encoder_L_Reversed);
  private final Encoder m_Encoder_R = new Encoder(ChassisConstants.Encoder_R[0], ChassisConstants.Encoder_R[1], ChassisConstants.Encoder_R_Reversed);

  private final Gyro m_gyro = new ADXRS450_Gyro();

  private final DifferentialDriveOdometry m_odometry;

  public Chassis() {
    m_Encoder_L.setDistancePerPulse(ChassisConstants.Encoder_CountPerRevolution);
    m_Encoder_R.setDistancePerPulse(ChassisConstants.Encoder_CountPerRevolution);
    resetEncoders();
    m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
  }

  @Override
  public void periodic() {
    m_odometry.update(Rotation2d.fromDegrees(getHeading()), m_Encoder_L.getDistance(), m_Encoder_R.getDistance());
  }

  // Initiate
  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    m_odometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
  }
  public void resetEncoders(){
    m_Encoder_L.reset();
    m_Encoder_R.reset();
  }
  public void zeroHeading() {
    m_gyro.reset();
  }

  // Control part
  public void arcadeDrive(double forward, double rotation){
    m_drive.arcadeDrive(forward, rotation);
  }
  public void DriveByVolts(double leftVolts, double rightVolts){
    m_Motor_L.setVoltage(leftVolts);
    m_Motor_R.setVoltage(-rightVolts);
  }
  public void setMaxOutput(double maxOutput){
    m_drive.setMaxOutput(maxOutput);
  }

  // Assess values
  public Pose2d getPose(){
    return m_odometry.getPoseMeters();
  }
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(m_Encoder_L.getRate(), m_Encoder_R.getRate());
  }
  public double getHeading(){
    return Math.IEEEremainder(m_gyro.getAngle(), 360) * (ChassisConstants.Gyro_Reversed ? -1.0 : 1.0);
  }
  public double getTurnRate() {
    return m_gyro.getRate() * (ChassisConstants.Gyro_Reversed ? -1.0 : 1.0);
  }
}
