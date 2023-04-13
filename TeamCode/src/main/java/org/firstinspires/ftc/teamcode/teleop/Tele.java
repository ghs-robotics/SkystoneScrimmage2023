package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.robot.Robot;

@Config
@TeleOp
public class Tele extends LinearOpMode {
    Robot robot;
    Controls controls;

    Telemetry telemetry;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        robot = new Robot(hardwareMap, telemetry);
        controls = new Controls(robot);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        robot.gyro.reset();
        waitForStart();

        while (opModeIsActive()){

            controls.metaDrive(gamepad1.left_stick_x, gamepad1.right_stick_y, gamepad1.right_stick_x);

            controls.resetGyro(gamepad1.left_bumper, gamepad1.right_bumper);

            telemetry.addData("1", robot.gyro.getHeading(AngleUnit.DEGREES));
            telemetry.addData("2", robot.gyro.getSecond(AngleUnit.DEGREES));
            telemetry.addData("3", robot.gyro.getThird(AngleUnit.DEGREES));
            telemetry.update();
        }
    }
}