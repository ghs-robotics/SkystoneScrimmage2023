package org.firstinspires.ftc.teamcode.cv;

import static org.firstinspires.ftc.teamcode.cv.dashboard.CVTestingSettings.CANNY;
import static org.firstinspires.ftc.teamcode.cv.dashboard.CVTestingSettings.FILTER;
import static org.firstinspires.ftc.teamcode.cv.dashboard.CVTestingSettings.FILTER_TYPE;
import static org.firstinspires.ftc.teamcode.cv.dashboard.ColorFilterConstants.BLOCK_DARK_H;
import static org.firstinspires.ftc.teamcode.cv.dashboard.ColorFilterConstants.BLOCK_DARK_S;
import static org.firstinspires.ftc.teamcode.cv.dashboard.ColorFilterConstants.BLOCK_DARK_V;
import static org.firstinspires.ftc.teamcode.cv.dashboard.ColorFilterConstants.BLOCK_LIGHT_H;
import static org.firstinspires.ftc.teamcode.cv.dashboard.ColorFilterConstants.BLOCK_LIGHT_S;
import static org.firstinspires.ftc.teamcode.cv.dashboard.ColorFilterConstants.BLOCK_LIGHT_V;
import static org.opencv.core.CvType.CV_8UC1;
import static org.opencv.core.CvType.CV_8UC3;
//import static org.firstinspires.ftc.teamcode.cv.dashboard.ColorFilterConstants.GRAY_LOWER;
//import static org.firstinspires.ftc.teamcode.cv.dashboard.ColorFilterConstants.GRAY_UPPER;
//import static org.firstinspires.ftc.teamcode.cv.dashboard.ColorFilterConstants.LOWER_B;
//import static org.firstinspires.ftc.teamcode.cv.dashboard.ColorFilterConstants.LOWER_G;
//import static org.firstinspires.ftc.teamcode.cv.dashboard.ColorFilterConstants.LOWER_R;
//import static org.firstinspires.ftc.teamcode.cv.dashboard.ColorFilterConstants.UPPER_B;
//import static org.firstinspires.ftc.teamcode.cv.dashboard.ColorFilterConstants.UPPER_G;
//import static org.firstinspires.ftc.teamcode.cv.dashboard.ColorFilterConstants.UPPER_R;

import android.graphics.Color;

import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.features2d.SimpleBlobDetector;
import org.opencv.features2d.SimpleBlobDetector_Params;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvPipeline;
import org.tensorflow.lite.support.image.ImageProcessor;

import java.util.ArrayList;

public class TestingPipeline extends OpenCvPipeline {
    boolean viewportPaused = false;

    SimpleBlobDetector blobDetector;
    SimpleBlobDetector_Params blobParams;

    OpenCvCamera cam;

    Telemetry telemetry;

    Mat hsv = new Mat();
    Mat rgb = new Mat();
    Mat display = new Mat();

    Mat mask = new Mat();

    int zone;

    public TestingPipeline (OpenCvCamera camera, Telemetry telemetry){
        cam = camera;
        this.telemetry = telemetry;
    }

    @Override
    public Mat processFrame(Mat input) {
        this.rgb = input;

        //processHSV(hsv);

        display = seeBlock();

        checkZone();

        return display;
    }

    public void checkZone(){
    }

    public Mat seeBlock(){
        Scalar lightRange = new Scalar(BLOCK_LIGHT_H, BLOCK_LIGHT_S, BLOCK_LIGHT_V);
        Scalar darkRange = new Scalar(BLOCK_DARK_S, BLOCK_DARK_H, BLOCK_DARK_V);

        Core.inRange(rgb, lightRange, darkRange, mask);

//        Mat contour = new Mat(mask.rows(), mask.cols(), mask.type(), new Scalar(0));
        Mat contour = new Mat();
        ArrayList<MatOfPoint> contours = new ArrayList<MatOfPoint>();

        Rect largestRect = new Rect(0,0,1,1);

        Imgproc.findContours(mask, contours, contour, 3,2);


        for (int i = 0; i < contours.size() - 1; i++) {
            Rect rect = Imgproc.boundingRect(contours.get(i));
            if (largestRect.area() < rect.area() && isReasonable(rect.width, rect.height)) {
                largestRect = rect;
            }
        }

        Imgproc.rectangle(rgb, largestRect, new Scalar(0, 0, 255), 2);

        return contour;

    }


    @Override
    public void onViewportTapped() {
        viewportPaused = !viewportPaused;

        if(viewportPaused)
            cam.pauseViewport();
        else
            cam.resumeViewport();

    }

    public int getZone(){
        return zone;
    }

    boolean isReasonable(int w, int h) {
        return w > 10 && h > 10;
    }

    public void getTelemetry(){
        telemetry.addLine();
        telemetry.addLine("Pipeline telemetry");
        telemetry.addData("channels: ", display.channels());
        telemetry.addData("dump:     ", display.dump());
        telemetry.addLine();
    }
}
