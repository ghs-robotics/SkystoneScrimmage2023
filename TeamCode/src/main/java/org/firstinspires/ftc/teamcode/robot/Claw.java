package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Claw {
    private Servo leftClaw;
    private Servo rightClaw;

    private double clawPos;

    private boolean clawsDown;

    public Claw(HardwareMap hardwareMap, Telemetry telemetry){
        leftClaw = hardwareMap.get(Servo.class, "lclaw");
        rightClaw = hardwareMap.get(Servo.class, "rclaw");

        telemetry.update();

    }

    public void runClaws(){
        if (!clawsDown)
            clawPos = .5;
        else
            clawPos = 0;

        triggerClaws();
        clawsDown = !clawsDown;
    }

    public double getClawPos(){
        return clawPos;
    }


    private void triggerClaws(){
        leftClaw.setPosition(clawPos);
        rightClaw.setPosition(clawPos);
    }

}
