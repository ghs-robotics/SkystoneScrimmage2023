package org.firstinspires.ftc.teamcode.cv;

import static org.firstinspires.ftc.teamcode.cv.dashboard.CVTestingSettings.CANNY;
import static org.firstinspires.ftc.teamcode.cv.dashboard.CVTestingSettings.FILTER;
import static org.firstinspires.ftc.teamcode.cv.dashboard.CVTestingSettings.FILTER_TYPE;
import static org.firstinspires.ftc.teamcode.cv.dashboard.ColorFilterConstants.BLOCK_DARK_H;
import static org.firstinspires.ftc.teamcode.cv.dashboard.ColorFilterConstants.BLOCK_DARK_S;
import static org.firstinspires.ftc.teamcode.cv.dashboard.ColorFilterConstants.BLOCK_DARK_V;
import static org.firstinspires.ftc.teamcode.cv.dashboard.ColorFilterConstants.GRAY_LOWER;
import static org.firstinspires.ftc.teamcode.cv.dashboard.ColorFilterConstants.GRAY_UPPER;
import static org.firstinspires.ftc.teamcode.cv.dashboard.ColorFilterConstants.BLOCK_LIGHT_H;
import static org.firstinspires.ftc.teamcode.cv.dashboard.ColorFilterConstants.BLOCK_LIGHT_S;
import static org.firstinspires.ftc.teamcode.cv.dashboard.ColorFilterConstants.BLOCK_LIGHT_V;
import static org.firstinspires.ftc.teamcode.cv.dashboard.ColorFilterConstants.LOWER_B;
import static org.firstinspires.ftc.teamcode.cv.dashboard.ColorFilterConstants.LOWER_G;
import static org.firstinspires.ftc.teamcode.cv.dashboard.ColorFilterConstants.LOWER_R;
import static org.firstinspires.ftc.teamcode.cv.dashboard.ColorFilterConstants.UPPER_B;
import static org.firstinspires.ftc.teamcode.cv.dashboard.ColorFilterConstants.UPPER_G;
import static org.firstinspires.ftc.teamcode.cv.dashboard.ColorFilterConstants.UPPER_R;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.features2d.SimpleBlobDetector;
import org.opencv.features2d.SimpleBlobDetector_Params;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvPipeline;

public class TestingPipeline extends OpenCvPipeline {
    boolean viewportPaused = false;

    SimpleBlobDetector blobDetector;
    SimpleBlobDetector_Params blobParams;

    OpenCvCamera cam;

    Telemetry telemetry;

    Mat grayscale = new Mat();
    Mat hsv = new Mat();
    Mat rgb = new Mat();
    Mat display = new Mat();

    boolean zone1;
    boolean zone2;

    public TestingPipeline (OpenCvCamera camera, Telemetry telemetry){
        cam = camera;
        this.telemetry = telemetry;
    }

    @Override
    public Mat processFrame(Mat input) {
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
    }

    public int getZone(){
        if (zone1)
            return 1;
        else if (zone2)
            return 2;
        else
            return 3;
    }

    private void setBlobParams(){

    }


    private void detectBlob(Mat in){

    }


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

    public void getTelemetry(){
        telemetry.addLine();
        telemetry.addLine("Pipeline telemetry");
        telemetry.addData("channels: ", display.channels());
        telemetry.addData("dump:     ", display.dump());
        telemetry.addLine();
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
