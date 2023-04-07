package org.firstinspires.ftc.teamcode.cv;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class Pipeline extends OpenCvPipeline {
    Telemetry telemetry;

    Mat mat = new Mat();

    public Pipeline (Telemetry getTelemetry){
        telemetry = getTelemetry;
    }

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);

        // Shades of gray
        Scalar light = new Scalar(180, 0, 50);
        Scalar medium = new Scalar(180, 0, 30);
        Scalar dark = new Scalar(180, 0, 20);

        Core.inRange(mat, light, dark, mat);
    }
}
