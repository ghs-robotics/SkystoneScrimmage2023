package org.firstinspires.ftc.teamcode.autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.actions.AutoActions;

@Autonomous
public class Auto extends LinearOpMode {
    private AutoActions actions;

    private int progression;

    private int[] xCoords = {1000, 0};
    private int[] yCoords = {0, 2000};

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

            progression = actions.getProgression();

            switch (progression){
                case 0:
                    actions.move(0, 500);
                    break;
                case 1:
                    actions.move(500, 0);
            }


            actions.getTelemetry();
            telemetry.update();
        }
        actions.cam.closeCamera();
    }
}
