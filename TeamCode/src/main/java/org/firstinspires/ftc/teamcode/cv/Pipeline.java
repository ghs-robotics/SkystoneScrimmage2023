package org.firstinspires.ftc.teamcode.cv;

import static org.firstinspires.ftc.teamcode.cv.ColorFilterConstants.BLOCK_DARK_H;
import static org.firstinspires.ftc.teamcode.cv.ColorFilterConstants.BLOCK_DARK_S;
import static org.firstinspires.ftc.teamcode.cv.ColorFilterConstants.BLOCK_DARK_V;
import static org.firstinspires.ftc.teamcode.cv.ColorFilterConstants.CANNY;
import static org.firstinspires.ftc.teamcode.cv.ColorFilterConstants.FILTER;
import static org.firstinspires.ftc.teamcode.cv.ColorFilterConstants.FILTER_TYPE;
import static org.firstinspires.ftc.teamcode.cv.ColorFilterConstants.GRAY_LOWER;
import static org.firstinspires.ftc.teamcode.cv.ColorFilterConstants.GRAY_UPPER;
import static org.firstinspires.ftc.teamcode.cv.ColorFilterConstants.BLOCK_LIGHT_H;
import static org.firstinspires.ftc.teamcode.cv.ColorFilterConstants.BLOCK_LIGHT_S;
import static org.firstinspires.ftc.teamcode.cv.ColorFilterConstants.BLOCK_LIGHT_V;
import static org.firstinspires.ftc.teamcode.cv.ColorFilterConstants.LOWER_B;
import static org.firstinspires.ftc.teamcode.cv.ColorFilterConstants.LOWER_G;
import static org.firstinspires.ftc.teamcode.cv.ColorFilterConstants.LOWER_R;
import static org.firstinspires.ftc.teamcode.cv.ColorFilterConstants.UPPER_B;
import static org.firstinspires.ftc.teamcode.cv.ColorFilterConstants.UPPER_G;
import static org.firstinspires.ftc.teamcode.cv.ColorFilterConstants.UPPER_R;

import android.provider.ContactsContract;

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
    Mat hsv = new Mat();
    Mat rgb = new Mat();
    Mat display = new Mat();

    public Pipeline (OpenCvCamera camera){
        cam = camera;
    }

    @Override
    public Mat processFrame(Mat input) {
        // converts input image to black and white
        rgb = input;
        Imgproc.cvtColor(input, grayscale, Imgproc.COLOR_BGR2GRAY, 3);
        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_BGR2HSV, 3);

        if (FILTER_TYPE == 0)
            display = processBGR(rgb);
        else if (FILTER_TYPE == 1)
            display = processGray(grayscale);
        else
            display = processHSV(hsv);


        if (CANNY)
            Imgproc.Canny(display, display, 100, 122, 3, false);


        return display;
//
//        return display2;
    }

//    private Mat findEdge(Mat input){
//        //TODO: figure out what the thresholds are for the Canny method
//        Imgproc.Canny(input, input, 101, 130);
//        return input;
//    }

    private Mat processBGR(Mat input){
        Scalar lower = new Scalar(LOWER_B, LOWER_G, LOWER_R);
        Scalar upper = new Scalar(UPPER_B, UPPER_G, UPPER_R);
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
        Scalar lightRange = new Scalar(BLOCK_LIGHT_H, BLOCK_LIGHT_S, BLOCK_LIGHT_V);
        Scalar darkRange = new Scalar(BLOCK_DARK_S, BLOCK_DARK_H, BLOCK_DARK_V);

        if (FILTER)
            Core.inRange(input, lightRange, darkRange, input);

        return input;
    }

    @Override
    public void onViewportTapped() {
        viewportPaused = !viewportPaused;

        if(viewportPaused)
            cam.pauseViewport();
        else
            cam.resumeViewport();

    }
//    public boolean tuneCamera(){
        // find function that will
        // idea - compare the camera input to a pre-existing mat
//        Mat input = hsv;
//        boolean endFun = false;
//
//        int hDiff = 0;
//        int sDiff = 0;
//        int vDiff = 0;
//        if (input != constantMat){
//            hDiff = input - constantMat;
//            sDiff = input - constantMat;
//            vDiff = input - constantMat;
//
//            telemetry.addData("h difference", hDiff);
//            telemetry.addData("s difference", sDiff);
//            telemetry.addData("v difference", vDiff);
//        }else
//            telemetry.addLine("no diff");
//
//        return !endFun;
//    }
}
