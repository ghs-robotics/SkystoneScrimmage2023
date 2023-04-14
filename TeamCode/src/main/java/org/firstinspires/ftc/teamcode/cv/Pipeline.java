package org.firstinspires.ftc.teamcode.cv;

import static org.firstinspires.ftc.teamcode.cv.CVConstants.DARK_H;
import static org.firstinspires.ftc.teamcode.cv.CVConstants.DARK_S;
import static org.firstinspires.ftc.teamcode.cv.CVConstants.DARK_V;
import static org.firstinspires.ftc.teamcode.cv.CVConstants.LIGHT_H;
import static org.firstinspires.ftc.teamcode.cv.CVConstants.LIGHT_S;
import static org.firstinspires.ftc.teamcode.cv.CVConstants.LIGHT_V;
import static org.firstinspires.ftc.teamcode.cv.CVConstants.LOWER_B;
import static org.firstinspires.ftc.teamcode.cv.CVConstants.LOWER_G;
import static org.firstinspires.ftc.teamcode.cv.CVConstants.LOWER_R;
import static org.firstinspires.ftc.teamcode.cv.CVConstants.UPPER_B;
import static org.firstinspires.ftc.teamcode.cv.CVConstants.UPPER_G;
import static org.firstinspires.ftc.teamcode.cv.CVConstants.UPPER_R;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvPipeline;

public class Pipeline extends OpenCvPipeline {
    boolean viewportPaused = false;
    OpenCvCamera cam;


    Mat grayscale = new Mat();
    Mat output = new Mat();

    public Pipeline (OpenCvCamera camera){
        cam = camera;
    }

    @Override
    public Mat processFrame(Mat input) {
        // converts input image to black and white
        Imgproc.cvtColor(input, grayscale, Imgproc.COLOR_BGR2GRAY);

        Mat hsv = processHSV(input);
        Mat rgb = processRGB(input);

        return rgb;
    }

    private Mat processRGB(Mat input){
        Scalar lower = new Scalar(LOWER_R, LOWER_G, LOWER_B);
        Scalar upper = new Scalar(UPPER_R, UPPER_G, UPPER_B);

        Core.inRange(input, lower, upper, input);

        return input;
    }

    private Mat processHSV(Mat input){
        Mat hsv = new Mat();

        // converts input image to hsv
        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_BGR2HSV);

        // Shades of gray
//        Scalar lightRange = new Scalar(LIGHT_H, LIGHT_S, LIGHT_V);
//        Scalar darkRange = new Scalar(DARK_S, DARK_H, DARK_V);
        Scalar lightRange = new Scalar(0, 0, 90);
        Scalar darkRange = new Scalar(0, 0, 60);

        Core.inRange(hsv, lightRange, darkRange, hsv);

        return hsv;
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
