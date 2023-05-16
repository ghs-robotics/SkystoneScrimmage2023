package org.firstinspires.ftc.teamcode.actions;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.robot.Robot;

public class AutoActions {
    Robot robot;

    public AutoActions(Robot bot){
        robot = bot;
    }

    public void initRobot(){
        robot.cam.initCamera();
        robot.gyro.reset();
    }

    public void getTelemetry(){
        robot.cam.camTelemetry();
    }
}
