package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

//In which I have no clue what I'm doing!!! But I'm trying my best!!!! ;-;
// AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHHHHHHHHHHH

public class Lift {

    private DcMotor liftDrive;

    public Lift(HardwareMap hardwareMap, Telemetry telemetry) {

        liftDrive = hardwareMap.get(DcMotor.class, "lift");
        telemetry.update();
    }

    public void setLiftDrivePowers(double vp){
        liftDrive.setPower(vp);
    }

    public void calculateAndSetLiftPower (double x) {
        double verticalPower = x;


        setLiftDrivePowers(verticalPower);

    }

}


