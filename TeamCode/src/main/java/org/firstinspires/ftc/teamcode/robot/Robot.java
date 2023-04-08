package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.cv.Camera;

public class Robot {
    public Drivebase drive;
    public Gyro gyro;

    public Camera cam;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry){
        drive = new Drivebase(hardwareMap, telemetry);
        gyro = new Gyro(hardwareMap);

        cam = new Camera(hardwareMap, telemetry);
    }
}
