package org.firstinspires.ftc.teamcode.autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.actions.AutoActions;

@Autonomous
public class TestingAuto extends LinearOpMode {
    private AutoActions actions;

    @Override
    public void runOpMode() throws InterruptedException {
        actions = new AutoActions(hardwareMap, telemetry, AutoActions.LEFT);


        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        actions.initRobot();

        while (opModeIsActive()){
            telemetry.addData("zone", actions.cam.getZone());



            actions.getTelemetry();
            telemetry.update();
        }
        actions.cam.closeCamera();
    }
}
