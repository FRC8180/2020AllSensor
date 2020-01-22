# Driven with trajectory

----
## Constants
以下參數需要調整：

1. Trackwidth：左右車輪間的距離（單位：公尺）
2. WheelDiameters：車輪直徑（單位：公尺）
3. ksVolt, kvVolt, kaVolt, Kp, Ki, Kd：需要用輔助程式尋找，請參考[這裡]
(https://docs.wpilib.org/en/latest/docs/software/examples-tutorials/trajectory-tutorial/characterizing-drive.html)
4. Ramsete\_B, Ramsete\_Zeta：有常用的一組參數，請參考[這裡]
(https://docs.wpilib.org/en/latest/docs/software/advanced-control/trajectories/ramsete.html#constructing-the-ramsete-controller-object)

----
## Path
可以新增method來儲存路徑，可以在RobotContainer內呼叫

----
## Chassis
對底盤的基本定義