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

    /* What (I think)
    IMU is: Inertial Measurement Unit, provides the orientation of the robot and "angular velocity",
    it's speed/what direction in whatever angular (rotational?) direction. Built in to the hub, a sensor that
    measures movement on multiple axes.

    RevHubOrientationOnRobot: The orientation of the control hub, where it is on the robot.

    YawPitchRollAngles:
         Yaw: side to side lateral rotation, how far turned around on the Z axis (vertically towards the ceiling). Also called heading
         Pitch: front to back rotation, how far turned on X axis.
         Roll: side to side tilt, how far turned on Y axis.
     */

    IMU gyro;
    Orientation orientation;
    RevHubOrientationOnRobot revOrientation;
    YawPitchRollAngles angles;

    double heading;

    public Gyro(HardwareMap hardwareMap) {
        //hardware mapping the IMU, literally finding the device(I think that's what hardware mapping does?)
        gyro = hardwareMap.get(IMU.class, "imu");

        /* Intrinsic: The coordinate system moves along with the rotational axis (the gyro?),
        so the placement of things may change but their coordinates may not? We have intrinsic rotations
         Angle unit is in degrees*/
        //Orientation: Saying what side is facing where and the angle of the bottom if the hub is upright
        orientation = new Orientation(AxesReference.INTRINSIC, AxesOrder.ZYX, DEGREES, 0, 0, 0, 0);

        // "Constructs a RevHubOrientationOnRobot for a REV Hub that is mounted at any arbitrary angle on a robot using an Orientation object."
        revOrientation = new RevHubOrientationOnRobot(orientation);

        //Passing on orientation info to IMU
        IMU.Parameters parameters = new IMU.Parameters(revOrientation);

        //initializing the gyro, passing on the info using parameters
        gyro.initialize(parameters);

    }

// getting the heading/yaw, method
    public double getHeading(AngleUnit unit) {
        double yaw = getOrientation(unit)[0];
        return yaw;
    }

//resetting yaw method, orientation is relative to this
    public void reset() {
        gyro.resetYaw();
    }
//method to get orientation, returns yaw, pitch, and roll as angle
    public double[] getOrientation(AngleUnit unit) {
        angles = gyro.getRobotYawPitchRollAngles();
        double yaw = angles.getYaw(unit);
        double pitch = angles.getPitch(unit);
        double roll = angles.getRoll(unit);

        double[] angle = {yaw, pitch, roll};

        return angle;
    }
}