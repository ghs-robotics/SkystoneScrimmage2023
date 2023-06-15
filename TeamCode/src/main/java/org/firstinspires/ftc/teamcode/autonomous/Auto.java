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
        actions = new AutoActions(robot, AutoActions.fieldSide.LEFT);

        actions.initRobot();

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()){
            actions.getTelemetry();
            telemetry.update();

        }
//        robot.cam.closeCamera();
    }
    private void moveTo(int wantX, int wantY) {
        int rightNowX = robot.drive.getX();
        int rightNowY = robot.drive.getY();
        double xPower = 0;
        double yPower = 0;
        int xDistanceFrom = wantX - rightNowX;
        int yDistanceFrom = wantY - rightNowY;

        if (Math.abs(xDistanceFrom) > 10) {
            xPower = Math.pow(xDistanceFrom / 1000, 5);
        }

        if (Math.abs(yDistanceFrom) > 10) {
            yPower = Math.pow(yDistanceFrom / 1000, 5);

        }
        robot.drive.calculateDrivePowers(xPower, yPower, 0);
    }
}
