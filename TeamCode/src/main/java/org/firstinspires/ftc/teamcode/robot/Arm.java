package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

//In which I have no clue what I'm doing!!! But I'm trying my best!!!! ;-;
// AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHHHHHHHHHHH

public class Arm {
    public static final int lower = 20;

    private DcMotor liftMotor;

    private Servo gripper;

    Telemetry telemetry;

    private boolean grippingBlock;
    private int target;

    public Arm(HardwareMap hardwareMap, Telemetry telemetry) {
        liftMotor = hardwareMap.get(DcMotor.class, "lift");
        liftMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        gripper = hardwareMap.get(Servo.class, "gripper");
        gripper.setDirection(Servo.Direction.FORWARD);

        this.telemetry = telemetry;
        telemetry.update();
    }

    public void setLiftDrivePowers(double vp){
        liftMotor.setPower(vp);
    }

    public void runLift (double x) {
        liftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        double verticalPower = -x;
        if ((liftMotor.getCurrentPosition() <= 0 && verticalPower < 0) ||
                (liftMotor.getCurrentPosition() >= 1000 && verticalPower > 0))
            verticalPower = 0;

        setLiftDrivePowers(verticalPower);
    }

    public void runLiftToPos(int targetPos){
        liftMotor.setTargetPosition(targetPos);
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void moveGripper(){
        if (grippingBlock){
            gripper.setPosition(0.5);
        } else {
            gripper.setPosition(-1);
        }

        grippingBlock = !grippingBlock;
    }

    public void getTelemetry(){
        telemetry.addData("lift encoder", liftMotor.getCurrentPosition());
        telemetry.addData("next target", target);
    }

}


