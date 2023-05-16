package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot {
    public BallDrivebase drive;
    Gyro gyro;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry){
        drive = new BallDrivebase(hardwareMap, telemetry);
        gyro = new Gyro(hardwareMap);
    }
}
