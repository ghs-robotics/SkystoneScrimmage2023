package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

//In which I have no clue what I'm doing!!! But I'm trying my best!!!! ;-;
// AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHHHHHHHHHHH

public class LiftArm {
    private DcMotor liftMotor;

    private Servo gripper;

    Telemetry telemetry;

    private double liftPosition;
    private double gripperPosition;

    private boolean grippingBlock;
    private int target;
//finding the motor/servo
    public LiftArm(HardwareMap hardwareMap, Telemetry telemetry) {
        liftMotor = hardwareMap.get(DcMotor.class, "lift");

        gripper = hardwareMap.get(Servo.class, "gripper");

        this.telemetry = telemetry;
        telemetry.update();
    }
//setting lift drive powers to vertical power
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
/*if else statement saying that if grippingBlock is true, the gripper is set to 0.5 (half) of it's available range. else,
    it's set to 0 (none) of it's available range. At the end, grippingBlock is flipped (so if it's true it switches to false).
    !grippingBlock flips it, = makes it so grippingBlock is flipped.*/
    public void moveGripper(){
        if (grippingBlock){
            gripper.setPosition(0.5);
        } else {
            gripper.setPosition(0);
        }

        grippingBlock = !grippingBlock;
    }


}


