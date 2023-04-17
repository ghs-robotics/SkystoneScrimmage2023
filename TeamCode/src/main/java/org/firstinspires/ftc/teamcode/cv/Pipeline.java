package org.firstinspires.ftc.teamcode.cv;

import static org.firstinspires.ftc.teamcode.cv.CVConstants.DARK_H;
import static org.firstinspires.ftc.teamcode.cv.CVConstants.DARK_S;
import static org.firstinspires.ftc.teamcode.cv.CVConstants.DARK_V;
import static org.firstinspires.ftc.teamcode.cv.CVConstants.FILTER;
import static org.firstinspires.ftc.teamcode.cv.CVConstants.FILTER_TYPE;
import static org.firstinspires.ftc.teamcode.cv.CVConstants.GRAY_LOWER;
import static org.firstinspires.ftc.teamcode.cv.CVConstants.GRAY_UPPER;
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

import javax.microedition.khronos.opengles.GL;

public class Pipeline extends OpenCvPipeline {
    boolean viewportPaused = false;
    OpenCvCamera cam;


    Mat grayscale = new Mat();
    Mat hsv = new Mat();
    Mat rgb = new Mat();
    Mat display;

    public Pipeline (OpenCvCamera camera){
        cam = camera;
    }

    @Override
    public Mat processFrame(Mat input) {
        // converts input image to black and white
        rgb = input;
        Imgproc.cvtColor(input, grayscale, Imgproc.COLOR_BGR2GRAY);
        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_BGR2HSV);


        if (FILTER_TYPE == 0)
            display = processRGB(rgb);
        else if (FILTER_TYPE == 1)
            display = processGray(grayscale);
        else
            display = processHSV(hsv);

        return display;
    }

    private Mat processRGB(Mat input){
        Scalar lower = new Scalar(LOWER_R, LOWER_G, LOWER_B);
        Scalar upper = new Scalar(UPPER_R, UPPER_G, UPPER_B);
        if (FILTER)
            Core.inRange(input, lower, upper, input);

        return input;
    }

    private Mat processGray(Mat input){
        Scalar lower = new Scalar(GRAY_LOWER);
        Scalar upper = new Scalar(GRAY_UPPER);

        if (FILTER)
            Core.inRange(input, lower, upper, input);

        return input;

    }

    private Mat processHSV(Mat input){
        Scalar lightRange = new Scalar(LIGHT_H, LIGHT_S, LIGHT_V);
        Scalar darkRange = new Scalar(DARK_S, DARK_H, DARK_V);

        if (FILTER)
            Core.inRange(input, lightRange, darkRange, input);

        return input;
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
