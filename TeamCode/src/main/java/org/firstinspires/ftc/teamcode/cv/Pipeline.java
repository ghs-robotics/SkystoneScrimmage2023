package org.firstinspires.ftc.teamcode.cv;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvPipeline;

@Config
public class Pipeline extends OpenCvPipeline {
    boolean viewportPaused = false;
    OpenCvCamera cam;

    Mat mat = new Mat();
    Mat grayScale;
    Mat hsv;

    public Pipeline (OpenCvCamera camera){
        this.cam = camera;
    }

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_BGR2GRAY);

        // Shades of gray
        Scalar light = new Scalar(180, 0, 50);
        Scalar dark = new Scalar(180, 0, 20);

        Imgproc.rectangle(
                input,
                new Point(
                        input.cols()/4,
                        input.rows()/4),
                new Point(
                        input.cols()*(3f/4f),
                        input.rows()*(3f/4f)),
                new Scalar(0, 255, 0), 4);

        Core.inRange(mat, light, dark, mat);

        return input;
    }

    @Override
    public void onViewportTapped()
    {
        /*
         * The viewport (if one was specified in the constructor) can also be dynamically "paused"
         * and "resumed". The primary use case of this is to reduce CPU, memory, and power load
         * when you need your vision pipeline running, but do not require a live preview on the
         * robot controller screen. For instance, this could be useful if you wish to see the live
         * camera preview as you are initializing your robot, but you no longer require the live
         * preview after you have finished your initialization process; pausing the viewport does
         * not stop running your pipeline.
         *
         * Here we demonstrate dynamically pausing/resuming the viewport when the user taps it
         */

        viewportPaused = !viewportPaused;

        if(viewportPaused)
        {
            cam.pauseViewport();
        }
        else
        {
            cam.resumeViewport();
        }
    }
}
