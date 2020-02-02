/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public final class Constants {
    
    //Joystick index defined
    public static final byte buttonY = 4;
    public static final byte buttonA = 1;
    public static final byte buttonX = 3;
    public static final byte buttonB = 2;
    public static final byte buttonRB = 6;
    public static final byte buttonLB = 5;
    public static final byte buttonBack = 7;
    public static final byte buttonOption = 8;
    public static final byte buttonJR = 10;
    public static final byte buttonJL = 9;
    public static final byte axisJRX = 4;
    public static final byte axisJRY = 5;
    public static final byte axisJLX = 0;
    public static final byte axisJLY = 1;
    public static final byte axisRT = 3;
    public static final byte axisLT = 2;

    //Joystick Setting
    public static final boolean axisRXInvert = false;
    public static final boolean axisRYInvert = true;
    public static final boolean axisLXInvert = false;
    public static final boolean axisLYInvert = true;

    public static final double axisRXExp = 1;
    public static final double axisRXScale = 1;
    public static final double axisRYExp = 1;
    public static final double axisRYScale = 1;
    public static final double axisLXExp = 1;
    public static final double axisLXScale = 1;
    public static final double axisLYExp = 1;
    public static final double axisLYScale = 1;

    //Shooter Setting
    public static final byte shooterUpperMotorID = 1;
    public static final byte shooterLowerMotorID = 2;
    public static final boolean shooterUpperMotorInvert = false;
    public static final boolean shooterLowerMotorInvert = false;
    public static final int shooterUpperEncoderPPR = 2048;
    public static final int shooterLowerEncoderPPR = 2048;
    public static final byte shooterUpperEncoderPinA = 7;
    public static final byte shooterUpperEncoderPinB = 8;
    public static final boolean shooterUpperEncoderDirectionInvert = true;
    public static final byte shooterLowerEncoderPinA = 9;
    public static final byte shooterLowerEncoderPinB = 10;
    public static final boolean shooterLowerEncoderDirectionInvert = false;
    
    public static final int chassisLiftTalonSRX = 1;
    public static final int chassisFollower1TalonSRX =0;
    public static final int chassisRightTalonSRX = 2;
    public static final int chassisFolower2TalonSRX = 3;

    public static int _smoothing =0;
    public static int _pov = -1;

    /* 
    * Which PID slot to pull gains from. Starting 2018, you can choose from
	 * 0,1,2 or 3. Only the first two (0,1) are visible in web-based
	 * configuration.
     * */
    public static final int kSlotIdx = 0;

	/**
	 * Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For
	 * now we just want the primary one.
	 */
	public static final int kPIDLoopIdx = 0;

	/**
	 * set to zero to skip waiting for confirmation, set to nonzero to wait and
	 * report to DS if action fails.
	 */
    public static final int kTimeoutMs = 30;
    	/**
	 * Gains used in Motion Magic, to be adjusted accordingly
     * Gains(kp, ki, kd, kf, izone, peak output);
     */
    public static final Gain kGains = new Gain(0.2, 0.0, 0.0, 0.2, 0, 1.0);
}
