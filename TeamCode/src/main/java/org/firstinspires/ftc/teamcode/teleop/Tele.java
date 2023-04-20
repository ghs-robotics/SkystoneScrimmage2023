package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.Robot;

@TeleOp
public class Tele extends LinearOpMode {
    Robot jerry;
    @Override
    public void runOpMode() throws InterruptedException {
        jerry = new Robot(hardwareMap, telemetry);
        telemetry.update();
        waitForStart();
        while (opModeIsActive()){
            jerry.drive.calculateAndSetDrivePowers(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
            telemetry.update();
        }

    }
}
