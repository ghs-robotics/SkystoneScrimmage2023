package org.firstinspires.ftc.teamcode.cv;

import com.acmerobotics.dashboard.config.Config;

import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvPipeline;

@Config
public class Pipeline extends OpenCvPipeline {
    boolean viewportPaused = false;
    OpenCvCamera cam;

    public static int HSV_DARK_VALUE = 20;
    public static int HSV_LIGHT_VALUE = 90;

    Mat grayscale = new Mat();
    Mat hsv = new Mat();

    public Pipeline (OpenCvCamera camera){
        cam = camera;
    }

    @Override
    public Mat processFrame(Mat input) {
        // converts input image to black and white
        Imgproc.cvtColor(input, grayscale, Imgproc.COLOR_BGR2GRAY);

        // converts input image to hsv
        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_BGR2HSV);

        // Shades of gray
        Scalar light = new Scalar(180, 0, HSV_LIGHT_VALUE);
        Scalar dark = new Scalar(180, 0, HSV_DARK_VALUE);


//        Core.inRange(hsv, light, dark, hsv);

        return grayscale;
    }

    @Override
    public void onViewportTapped()
    {

        viewportPaused = !viewportPaused;

        if(viewportPaused)
            cam.pauseViewport();
        else
            cam.resumeViewport();

    }
}
