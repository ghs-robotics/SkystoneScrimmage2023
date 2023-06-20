package org.firstinspires.ftc.teamcode.robot.drivebase;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;

public class BallDrive {

    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor backDrive;

    private ArrayList<DcMotor> motors = new ArrayList<DcMotor>();

    private int xPos;
    private int yPos;

    public BallDrive(HardwareMap hardwareMap, Telemetry telemetry){

        // Gets the motor from the hub, make sure the name matches the config on the Driver hub
        leftDrive = hardwareMap.get(DcMotor.class, "l");
        rightDrive = hardwareMap.get(DcMotor.class, "r");
        backDrive = hardwareMap.get(DcMotor.class, "b");

        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDrive.setDirection(DcMotorSimple.Direction.FORWARD);

        motors.add(leftDrive);
        motors.add(rightDrive);
        motors.add(backDrive);

        for (DcMotor m: motors){
            m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

        telemetry.update();
    }

    public int[] getPosition(){
        xPos  = backDrive.getCurrentPosition();
        yPos = leftDrive.getCurrentPosition();
        return new int[]{xPos, yPos};
    }

    public void resetPos(){
        for (DcMotor m: motors){
            m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
    }

    public void setDriveMode(){
        for (DcMotor m: motors){
            m.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
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
