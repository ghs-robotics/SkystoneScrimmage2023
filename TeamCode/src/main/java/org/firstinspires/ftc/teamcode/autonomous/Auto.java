package org.firstinspires.ftc.teamcode.autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.actions.AutoActions;

@Autonomous
public class Auto extends LinearOpMode {
    private AutoActions actions;

    private int progression;
    private int moveCount;

    private int[] block = {-600, -400, -200};

    private int[] xCoords = {-200, 2300, 1600};
    private int[] yCoords = {1500, 0, 1300};

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
            moveCount = actions.getMoveCount();
            progression = actions.getProgression();



//            switch (progression){
//                case 0:
//                    move();
//                    break;
//                case 1:
//                    move();
//                    break;
//                case 2:
//                    move();
//                    break;
//            }


            actions.getTelemetry();
            telemetry.update();
        }
        actions.cam.closeCamera();
    }

    private void move(){
        actions.move(xCoords[moveCount], yCoords[moveCount]);
    }
}
