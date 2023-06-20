package org.firstinspires.ftc.teamcode.autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.actions.AutoActions;
import org.firstinspires.ftc.teamcode.robot.Robot;

@Autonomous
public class Auto extends LinearOpMode {
    private AutoActions actions;

    @Override
    public void runOpMode() throws InterruptedException {
        actions = new AutoActions(hardwareMap, telemetry, AutoActions.fieldSide.LEFT);


        telemetry.addData("Status", "Initialized");
        actions.initRobot();
        telemetry.update();
        waitForStart();

        actions.prepRobot();
        telemetry.update();
        while (opModeIsActive()){


            actions.getTelemetry();
            telemetry.update();
        }
        actions.cam.closeCamera();
    }
}
