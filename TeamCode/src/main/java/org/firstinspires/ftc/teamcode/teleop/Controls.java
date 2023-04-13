package org.firstinspires.ftc.teamcode.teleop;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.robot.Robot;

public class Controls {
    Robot robot;

    public Controls(Robot bot){
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
}