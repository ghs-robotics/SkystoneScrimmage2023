package org.firstinspires.ftc.teamcode.actions;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.robot.Robot;

public class AutoActions extends Robot{
    fieldSide side;

    public AutoActions(HardwareMap hardwareMap, Telemetry telemetry, fieldSide side){
        super(hardwareMap, telemetry);
        this.side = side;
    }

    public void runSet(){

    }

    public void initRobot(){
        cam.initCamera();
        gyro.reset();
    }

    public void getTelemetry(){
        cam.camTelemetry();
        cam.pipeline.getTelemetry();
    }

    public void move(int targetX, int targetY){
        int currentX = drive.getPosition()[0];
        int currentY = drive.getPosition()[1];

        int xDiff = targetX - currentX;
        int yDiff = targetY - currentY;

        double xPower = 0;
        double yPower = 0;


        if (Math.abs(xDiff) > 20){
            xPower = Math.pow((xDiff / 100.0), 3);
        }

        if (Math.abs(yDiff) > 20){
            yPower = Math.pow((yDiff / 100.0), 3);
        }

        drive.calculateDrivePowers(xPower, yPower, 0);
    }

    public enum fieldSide{
        LEFT,
        RIGHT
    }
}

