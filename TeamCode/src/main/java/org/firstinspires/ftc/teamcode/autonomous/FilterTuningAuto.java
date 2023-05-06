package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.Robot;


@Autonomous
public class FilterTuningAuto extends LinearOpMode {
    Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap, telemetry);
        robot.cam.initCamera();

        ElapsedTime timer = new ElapsedTime();

        telemetry.update();
        waitForStart();
        while (opModeIsActive()){
            // put an image in front of the bot
            // waits
            if (timer.seconds() > 5) {
                // call function that will change numbers until the bot detects the image
                // generates / overwrites a file called HSV constants that the robot camera can access
                // return the numbers in telemetry and end opMode
            }

            telemetry.update();
        }
        robot.cam.closeCamera();
    }
}
