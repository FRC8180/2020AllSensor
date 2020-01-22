package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

public final class Constants {
    public static final class ChassisConstants{
        // chassis parameters
        public final static double Trackwidth = 0.69; // ***Distance between left and right wheels
        public final static DifferentialDriveKinematics m_ChassisKinematics = new DifferentialDriveKinematics(Trackwidth);
        public final static double WheelDiameters = 0.15; // ***

        // Motors
        public final static int Motor_RA = 1;
        public final static int Motor_RB = 2;
        public final static int Motor_LA = 3;
        public final static int Motor_LB = 4;
        public final static boolean Motor_RA_Reversed = false;
        public final static boolean Motor_RB_Reversed = false;
        public final static boolean Motor_LA_Reversed = false;
        public final static boolean Motor_LB_Reversed = false;
        // ***The values are determined via the Robot Characterization Toolsuit
        public final static double ksVolt = 0.1;
        public final static double kvVolt = 0.1;
        public final static double kaVolt = 0.1;
        // ***PID values
        public final static double Kp = 0.05;
        public final static double Ki = 0;
        public final static double Kd = 100;

        // Encoders
        public final static int[] Encoder_L = new int[]{0, 1};
        public final static int[] Encoder_R = new int[]{0, 1};
        public final static boolean Encoder_L_Reversed = false;
        public final static boolean Encoder_R_Reversed = true;
        public final static int Encoder_CountPerRevolution = 4096;
        public final static double Encoder_DistancePerPulse = (WheelDiameters * Math.PI) / (double)Encoder_CountPerRevolution;

        // Gyro
        public final static boolean Gyro_Reversed = false;
    }

    public static final class OIConstants{
        public static final int Controller_Port = 0;
    }

    public static final class AutoConstants{
        // unit: meters
        public static final double MaxSpeed = 3;
        public static final double MaxAccel = 3;

        // Reasonable baseline values
        public static final double Ramsete_B = 2;
        public static final double Ramsete_Zeta = 0.7;
    }

}
