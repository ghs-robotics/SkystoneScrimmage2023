package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class BallDrivebase {

    private DcMotor leftDrive;

    private DcMotor rightDrive;

    private DcMotor backDrive;


    public BallDrivebase(HardwareMap hardwareMap, Telemetry telemetry){

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

    public void setDrivePowers(double lp, double rp, double bp) {
        leftDrive.setPower(lp);
        rightDrive.setPower(rp);
        backDrive.setPower(bp);
    }

    public void calculateAndSetDrivePowers(double y, double x, double rot){

        double leftPower = y - rot;
        double rightPower = y + rot;
        double backPower = x ;

        setDrivePowers(leftPower,rightPower,backPower);
    }

}
