package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

//In which I have no clue what I'm doing!!! But I'm trying my best!!!! ;-;
// AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHHHHHHHHHHH

public class Arm {
    public static final int lower = 20;

    private DcMotor liftMotor;

    private Servo leftGripper;
    private Servo rightGripper;

    Telemetry telemetry;

    private boolean grippingBlock;
    private int target;

    public Arm(HardwareMap hardwareMap, Telemetry telemetry) {
        liftMotor = hardwareMap.get(DcMotor.class, "lift");

        leftGripper = hardwareMap.get(Servo.class, "lgripper");
        rightGripper = hardwareMap.get(Servo.class, "rgripper");

        this.telemetry = telemetry;
        telemetry.update();
    }

    public void setLiftDrivePowers(double vp){
        liftMotor.setPower(vp);
    }

    public void runLift (double x) {
        liftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        double verticalPower = x;
        setLiftDrivePowers(verticalPower);
    }

    public void runLiftToPos(int targetPos){
        liftMotor.setTargetPosition(targetPos);
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void moveGripper(){
        if (grippingBlock){
            leftGripper.setPosition(0.5);
            rightGripper.setPosition(0);
        } else {
            leftGripper.setPosition(0);
            rightGripper.setPosition(0.5);
        }

        grippingBlock = !grippingBlock;
    }

    public void getTelemetry(){
        telemetry.addData("lift encoder", liftMotor.getCurrentPosition());
        telemetry.addData("next target", target);
    }

}


