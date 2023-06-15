package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LiftArm {
    private DcMotor liftMotor;
    private Servo gripperServo;

    public LiftArm (HardwareMap hardwareMap, Telemetry telemetry) {
        liftMotor = hardwareMap.get(DcMotor.class, "liftMotor");
        gripperServo = hardwareMap.get(Servo.class, "gripperServo");
        telemetry.update();
    }
    public void moveLift (double lift) {
        liftMotor.setPower(lift);
//        liftMotor.setTargetPosition(lift);
//        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void moveGripper (boolean grip) {
        if (grip)
            gripperServo.setPosition(1);
        else
            gripperServo.setPosition(0);
    }
}


