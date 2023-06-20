package org.firstinspires.ftc.teamcode.actions;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.robot.Robot;

public class AutoActions extends Robot{
    int side;

    public static final int LEFT = 1;
    public static final int RIGHT = -1;

    int progression = 0;
    int moveCount = 0;

    public AutoActions(HardwareMap hardwareMap, Telemetry telemetry, int side){
        super(hardwareMap, telemetry);
        this.side = side;
    }

    public int getProgression(){
        return progression;
    }

    public int getMoveCount(){
        return moveCount;
    }

    public void initRobot(){
        cam.initCamera();
        gyro.reset();
        drive.resetPos();
    }

    public void prepRobot(){
        drive.setDriveMode();
    }

    public void getTelemetry(){
        cam.camTelemetry();
        cam.pipeline.getTelemetry();
        telemetry.addData("x ", drive.getPosition()[0]);
        telemetry.addData("y ", drive.getPosition()[1]);
    }

    public void runGripper(){
        arm.moveGripper();
    }

    public void move(int targetX, int targetY) {
        int currentX = drive.getPosition()[0] * side;
        int currentY = drive.getPosition()[1];

        int xDiff = targetX - currentX;
        int yDiff = targetY - currentY;

        if (targetX == 0)
            xDiff = 0;

        if (targetY == 0)
            yDiff = 0;

        int error = 25;
        double divider = 100.0;

        double xPower = 0;
        double yPower = 0;


        if (Math.abs(xDiff) > error) {
            xPower = (xDiff / divider);
        }

        if (Math.abs(yDiff) > error) {
            yPower = (yDiff / divider);
        }

        drive.calculateDrivePowers(xPower, yPower, 0);

        if (xPower + yPower == 0){
            progression++;
            moveCount++;
        }
    }

}

