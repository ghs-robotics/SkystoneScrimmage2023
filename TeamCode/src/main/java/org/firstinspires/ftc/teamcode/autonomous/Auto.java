package org.firstinspires.ftc.teamcode.autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.actions.AutoActions;
import org.firstinspires.ftc.teamcode.robot.Robot;

@Autonomous
public class Auto extends LinearOpMode {
    private Robot robot;
    private AutoActions actions;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap, telemetry);
        actions = new AutoActions(robot);

        actions.initRobot();

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()){
            actions.getTelemetry();
            telemetry.update();
        }
        robot.cam.closeCamera();
    }
}
