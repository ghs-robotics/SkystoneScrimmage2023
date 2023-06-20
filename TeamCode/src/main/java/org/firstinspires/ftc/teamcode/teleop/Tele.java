package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.actions.TeleControls;
import org.firstinspires.ftc.teamcode.robot.Robot;

@TeleOp
public class Tele extends LinearOpMode {
    TeleControls controls;

    @Override
    public void runOpMode() throws InterruptedException {

        controls = new TeleControls(hardwareMap, telemetry);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()){

            controls.regularDrive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

            controls.resetGyro(gamepad1.left_bumper, gamepad1.right_bumper);


            //---------------------------------------------------------------------------------------------
            //                                     GAMEPAD 2
            //---------------------------------------------------------------------------------------------

            controls.moveGripper(gamepad2.left_bumper);

            controls.runLift(gamepad2.left_stick_y);


            controls.getTelemetry();
            telemetry.update();
        }
    }
}