package org.firstinspires.ftc.teamcode.actions;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.robot.Robot;

public class TeleControls extends Robot{

    public TeleControls(HardwareMap hardwareMap, Telemetry telemetry){
        super(hardwareMap, telemetry);
    }

    public void metaDrive (double leftStickX, double leftStickY, double rightStickX){
        double y = leftStickY;
        double x = leftStickX;
        double angle = gyro.getHeading(AngleUnit.RADIANS);

        double newY = y * Math.cos(angle) + x * Math.sin(angle);
        double newX = y * Math.sin(angle) - x * Math.cos(angle);

        drive.calculateDrivePowers(newY, newX, rightStickX);
    }

    public void regularDrive (double leftStickX, double leftStickY, double rightStickX){
        drive.calculateDrivePowers(leftStickX, leftStickY, rightStickX);
    }

    public void resetGyro (boolean bumper1, boolean bumper2){
        if (bumper1 && bumper2)
            gyro.reset();
    }

    /**
     *              Gamepad 2
     */

    public void moveGripper(boolean a){
        if (a)
            arm.moveGripper();
    }

    public void runLift(double leftStickY){
        arm.runLift(leftStickY);
    }

    public void getTelemetry(){
        arm.getTelemetry();
    }
}