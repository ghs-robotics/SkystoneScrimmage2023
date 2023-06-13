package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Claw {

    private final double CLAW_UP = 1;
    private final double CLAW_DOWN = 0;
    Servo leftClaw;
    Servo rightClaw;

    public Claw (HardwareMap hardwareMap, Telemetry telemetry) {
        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        rightClaw = hardwareMap.get(Servo.class, "rightClaw");
    }

    public void moveClawUp () {
        leftClaw.setPosition(CLAW_UP);
        rightClaw.setPosition(CLAW_UP);
    }
    public void moveClawDown () {
        leftClaw.setPosition(CLAW_DOWN);
        rightClaw.setPosition(CLAW_DOWN);
    }
}