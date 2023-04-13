package org.firstinspires.ftc.teamcode.robot.drivebase;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MecanumDrivebase {

    public DcMotor leftFrontDrive;
    public DcMotor leftBackDrive;
    public DcMotor rightFrontDrive;
    public DcMotor rightBackDrive;

    public MecanumDrivebase(HardwareMap hardwareMap, Telemetry telemetry){

        // Gets the motor from the hub, make sure the name matches the config on the Driver hub
//        leftFrontDrive = hardwareMap.get(DcMotor.class, "");
//        leftBackDrive = hardwareMap.get(DcMotor.class, "");
//        rightFrontDrive = hardwareMap.get(DcMotor.class, "");
//        rightBackDrive = hardwareMap.get(DcMotor.class, "");

        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.update();
    }

    public void calculateDrivePowers(double y, double x, double rot){

        double leftFrontPower = y + x + rot;
        double leftBackPower = -y + x + rot;
        double rightFrontPower = y - x - rot;
        double rightBackPower = -y - x - rot;

        setDrivePowers(leftFrontPower, leftBackPower, rightFrontPower, rightBackPower);
    }

    public void setDrivePowers(double lf, double lb, double rf, double rb){
        leftFrontDrive.setPower(lf);
        leftBackDrive.setPower(lb);
        rightFrontDrive.setPower(rf);
        rightBackDrive.setPower(rb);
    }

}
