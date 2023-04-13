package org.firstinspires.ftc.teamcode.robot;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.cv.Camera;
import org.firstinspires.ftc.teamcode.robot.drivebase.BallDrivebase;
import org.firstinspires.ftc.teamcode.robot.drivebase.MecanumDrivebase;

public class Robot {
    public MecanumDrivebase drive;
//    public BallDrivebase drive;
    public Gyro gyro;

    public Camera cam;

    private FtcDashboard dashboard;
    public Telemetry telemetry;

    public Robot(HardwareMap hardwareMap, Telemetry t){
        dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(t, dashboard.getTelemetry());

//        drive = new MecanumDrivebase(hardwareMap, telemetry);
//        gyro = new Gyro(hardwareMap);

        cam = new Camera(hardwareMap, telemetry);

        dashboard.startCameraStream(cam.cam, 0);
    }
}
