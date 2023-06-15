package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.actions.TeleControls;
import org.firstinspires.ftc.teamcode.robot.Robot;

@TeleOp
public class Tele extends LinearOpMode {
    Robot robot;

    TeleControls controls;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap, telemetry);

        controls = new TeleControls(robot, telemetry);

        telemetry.update();

        waitForStart();

        while (opModeIsActive()){
            // gamepad1

            controls.regularDrive(gamepad1.right_stick_x, gamepad1.left_stick_x, gamepad1.left_stick_y);


            // gamepad2
            controls.driveLift(gamepad2.left_stick_y);
            controls.runArm(gamepad2.a);
            // drag tower base - controls.moveBase(gamepad2.x);

            telemetry.update();
        }

    }
}
