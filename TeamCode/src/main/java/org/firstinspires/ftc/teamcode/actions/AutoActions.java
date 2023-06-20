package org.firstinspires.ftc.teamcode.actions;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.robot.Robot;

public class AutoActions {
    Robot robot;
    fieldSide side;

    public static final int INIT = 0;
    public static final int MOVE = 1;
    public static final int GRIP = 2;
    public static final int LIFT = 3;

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

