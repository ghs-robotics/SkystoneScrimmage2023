package org.firstinspires.ftc.teamcode.robot;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class Gyro {

    IMU gyro;
    Orientation orientation;
    RevHubOrientationOnRobot revOrientation;
    YawPitchRollAngles angles;

    double heading;

    public Gyro(HardwareMap hardwareMap) {
        gyro = hardwareMap.get(IMU.class, "imu");
        orientation = new Orientation(AxesReference.INTRINSIC, AxesOrder.ZYX,
                DEGREES, 0, 0, 0, 0);
        revOrientation = new RevHubOrientationOnRobot(orientation);
        IMU.Parameters parameters = new IMU.Parameters(revOrientation);

        gyro.initialize(parameters);

    }

    // Rotation - main one to use
    public double getHeading(AngleUnit unit) {
        double yaw = getOrientation(unit)[0];
        return yaw;
    }

    // up and down x axis
    public double getPitch(AngleUnit unit){
        double pitch = getOrientation(unit)[1];
        return pitch;
    }

    // up and down y axis
    public double getRoll(AngleUnit unit){
        double roll = getOrientation(unit)[2];
        return roll;
    }

    public void reset() {
        gyro.resetYaw();
    }

    public double[] getOrientation(AngleUnit unit) {
        angles = gyro.getRobotYawPitchRollAngles();
        double yaw = angles.getYaw(unit);
        double pitch = angles.getPitch(unit);
        double roll = angles.getRoll(unit);

        double[] angle = {yaw, pitch, roll};

        return angle;
    }
}