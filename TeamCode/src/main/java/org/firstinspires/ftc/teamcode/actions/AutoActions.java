package org.firstinspires.ftc.teamcode.actions;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.robot.Robot;

public class AutoActions {
    Robot robot;
    fieldSide side;

    public AutoActions(Robot bot, fieldSide side){
        robot = bot;
        this.side = side;
    }

    public void runSet(){

    }

    public void initRobot(){
        robot.cam.initCamera();
        robot.gyro.reset();
    }

    public void getTelemetry(){
        robot.cam.camTelemetry();
        robot.cam.pipeline.getTelemetry();
    }

    public enum fieldSide{
        LEFT,
        RIGHT
    }
}

