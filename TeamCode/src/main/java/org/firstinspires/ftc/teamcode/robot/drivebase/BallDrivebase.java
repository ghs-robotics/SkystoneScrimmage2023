package org.firstinspires.ftc.teamcode.robot.drivebase;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class BallDrivebase {
    public BallDrivebase(HardwareMap hardwareMap, Telemetry telemetry) {
    }

    // For Lillian's Ego
    public String compliment(int repeats){
        String comp = "";
        for (int i = 0; i < repeats; i++)
            comp += "Lillian's balldrive is so pretty\n";

        return comp;
    }
}
