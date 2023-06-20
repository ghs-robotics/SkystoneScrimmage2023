package org.firstinspires.ftc.teamcode.robot.drivebase;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class BallDrive {

    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor backDrive;

    private int xPos;
    private int yPos;

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

    public int[] getPosition(){
        xPos = leftDrive.getCurrentPosition();
        yPos  = backDrive.getCurrentPosition();
        return new int[]{xPos, yPos};
    }


    public void calculateDrivePowers(double x, double y, double rot) {

        double leftPower = y - rot;
        double rightPower = y + rot;
        double backPower = x ;

        setDrivePowers(leftPower,rightPower,backPower);
    }


    private void setDrivePowers(double lp, double rp, double bp) {
        leftDrive.setPower(lp);
        rightDrive.setPower(rp);
        backDrive.setPower(bp);
    }
}
