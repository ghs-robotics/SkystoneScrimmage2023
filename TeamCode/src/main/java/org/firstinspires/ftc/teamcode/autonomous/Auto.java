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
        telemetry.update();
        waitForStart();

        actions.initRobot();

        while (opModeIsActive()){
            actions.move(500, 500);


            actions.getTelemetry();
            telemetry.update();
        }
        actions.cam.closeCamera();
    }
}
