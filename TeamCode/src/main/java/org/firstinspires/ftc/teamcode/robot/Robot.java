package org.firstinspires.ftc.teamcode.robot;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.cv.Camera;
import org.firstinspires.ftc.teamcode.robot.drivebase.BallDrivebase;
import org.firstinspires.ftc.teamcode.robot.drivebase.MecanumDrivebase;

public class Robot {
    public MecanumDrivebase drive;
//    public BallDrivebase drive;
    public Gyro gyro;

    public Camera cam;

    private HardwareMap hardwareMap;
    private FtcDashboard dashboard;
    public Telemetry telemetry;

    public Robot(HardwareMap hMap, Telemetry t){
        hardwareMap = hMap;
        dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(t, dashboard.getTelemetry());

        drive = new MecanumDrivebase(hardwareMap, telemetry);
        gyro = new Gyro(hardwareMap);

        cam = new Camera(hardwareMap, telemetry);

        dashboard.startCameraStream(cam.cam, 0);
    }

    // estimate based on online info - the battery type is wrong
    public double getBatteryPercentage(){
        VoltageSensor voltageSensor = hardwareMap.voltageSensor.iterator().next();
        double v = voltageSensor.getVoltage();
        double p = -1.8048 * Math.pow(v, 4) + 83.538 * Math.pow(v, 3) - 1445.5 * Math.pow(v, 2) + 11086 * v - 31807;
        double percent = Math.round(p * 1000) / 100;

        return percent;
    }
}
