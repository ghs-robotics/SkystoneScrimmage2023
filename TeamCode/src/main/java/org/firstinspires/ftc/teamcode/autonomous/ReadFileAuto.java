package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.actions.AutoActions;
import org.firstinspires.ftc.teamcode.constants.ReadWrite;
import org.firstinspires.ftc.teamcode.robot.Robot;

public class ReadFileAuto extends LinearOpMode {
    private Robot robot;
    private AutoActions actions;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap, telemetry);

        actions.initRobot();

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()){
            telemetry.addData("auto", ReadWrite.getAutoInstructions());
            telemetry.addData("hsv", ReadWrite.getHSV());

            actions.getTelemetry();
            telemetry.update();
        }
        robot.cam.closeCamera();
    }
}
