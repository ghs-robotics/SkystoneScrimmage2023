package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.robot.drivebase.BallDrive;
import org.firstinspires.ftc.teamcode.robot.drivebase.Drivebase;

public class Robot {
    Drivebase drive;
    Gyro gyro;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry){
        drive = new BallDrive(hardwareMap, telemetry);
        gyro = new Gyro(hardwareMap);
    }
}
