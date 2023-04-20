package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot {
    public Drivebase drive;
    Gyro gyro;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry){
        drive = new Drivebase(hardwareMap, telemetry);
        gyro = new Gyro(hardwareMap);
    }
}
