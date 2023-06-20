package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Claws {
    private Servo leftClaw;
    private Servo rightClaw;

    Telemetry telemetry;

    public Claws(HardwareMap hardwareMap, Telemetry telemetry) {
        leftClaw = hardwareMap.get(Servo.class, "lclaw");
        rightClaw = hardwareMap.get(Servo.class, "rclaw");

        leftClaw.setDirection(Servo.Direction.FORWARD);
        rightClaw.setDirection(Servo.Direction.REVERSE);

        this.telemetry = telemetry;
        telemetry.update();
    }

    public void clawsUp(){
        leftClaw.setPosition(-0.1);
        rightClaw.setPosition(-0.1);
    }

    public void clawsDown(){
        leftClaw.setPosition(0.5);
        rightClaw.setPosition(0.5);
    }
}
