package org.firstinspires.ftc.teamcode.autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.Robot;

@Autonomous
public class Auto extends LinearOpMode {
    private Robot robot;


    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap, telemetry);

        robot.cam.initCamera();

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()){
            telemetry.addData("bat percent", robot.getBatteryPercentage() + "%");
            telemetry.update();
        }
        robot.cam.closeCamera();
    }
}
