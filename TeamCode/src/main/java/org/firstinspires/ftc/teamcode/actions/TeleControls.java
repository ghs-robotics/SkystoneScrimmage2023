package org.firstinspires.ftc.teamcode.actions;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.robot.Robot;

public class TeleControls {
    Robot robot;

    public TeleControls(Robot bot){
        robot = bot;
    }

    public void metaDrive (double leftStickX, double leftStickY, double rightStickX){
        double y = leftStickY;
        double x = leftStickX;
        double angle = robot.gyro.getHeading(AngleUnit.RADIANS);

        double newY = y * Math.cos(angle) + x * Math.sin(angle);
        double newX = y * Math.sin(angle) - x * Math.cos(angle);

        robot.drive.calculateDrivePowers(newY, newX, rightStickX);
    }

    public void regularDrive (double leftStickX, double leftStickY, double rightStickX){
        double y = leftStickY;
        double x = leftStickX;
        double r = rightStickX;

        robot.drive.calculateDrivePowers(y, x, r);
    }

    public void resetGyro (boolean bumper1, boolean bumper2){
        if (bumper1 && bumper2)
            robot.gyro.reset();
    }

    public void driveLift(double leftStickY){
        if (leftStickY != 0)
            robot.arm.runLift(leftStickY);
    }

    public void runArm(boolean button){
        if (button)
            robot.arm.moveGripper();
    }

    public void driveLiftToPos(){

    }
}