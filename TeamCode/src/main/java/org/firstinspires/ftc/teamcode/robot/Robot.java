package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.cv.Camera;
import org.firstinspires.ftc.teamcode.robot.drivebase.BallDrivebase;
import org.firstinspires.ftc.teamcode.robot.drivebase.MecanumDrivebase;

public class Robot {
    public MecanumDrivebase mecDrive;
    public BallDrivebase ballDrive;
    public Gyro gyro;

    public Camera cam;

    public Robot(HardwareMap hardwareMap, Telemetry telemetry){
        mecDrive = new MecanumDrivebase(hardwareMap, telemetry);
        gyro = new Gyro(hardwareMap);

        cam = new Camera(hardwareMap, telemetry);
    }
}
