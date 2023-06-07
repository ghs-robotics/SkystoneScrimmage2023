package org.firstinspires.ftc.teamcode.robot.drivebase;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class BallDrive {
    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor backDrive;

    private double leftPower;
    private double rightPower;
    private double backPower;


    public BallDrive(HardwareMap hardwareMap, Telemetry telemetry){

        // Gets the motor from the hub, make sure the name matches the config on the Driver hub
        leftDrive = hardwareMap.get(DcMotor.class, "left");
        rightDrive = hardwareMap.get(DcMotor.class, "right");
        backDrive = hardwareMap.get(DcMotor.class, "bob");

        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.update();
    }


    public void calculateDrivePowers(double x, double y, double rot) {
        leftPower = y - rot;
        rightPower = y + rot;
        backPower = x ;

        setDrivePowers();
    }

    private void setDrivePowers() {
        leftDrive.setPower(leftPower);
        rightDrive.setPower(rightPower);
        backDrive.setPower(backPower);
    }

    public int getX() {
        return backDrive.getCurrentPosition();
    }

    public int getY() {
        return leftDrive.getCurrentPosition();
    }
}
