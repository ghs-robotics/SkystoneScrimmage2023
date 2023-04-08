package org.firstinspires.ftc.teamcode.cv;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Config
public class Camera {
    private HardwareMap hardwareMap;
    private Telemetry telemetry;
    private Pipeline pipeline;

    public static final int PIXEL_HEIGHT = 488;
    public static final int PIXEL_WIDTH = 800;

    OpenCvCamera cam;

    public Camera(HardwareMap hardwareMap, Telemetry telemetry){
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;

        this.pipeline = new Pipeline(telemetry);
        this.cam = OpenCvCameraFactory.getInstance()
                .createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"));
    }

    public void initCamera(){
        cam.setPipeline(pipeline);

        cam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                cam.startStreaming(PIXEL_WIDTH, PIXEL_HEIGHT, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {
                telemetry.addLine("Error: Camera could not open");
                telemetry.update();
            }
        });

        telemetry.setMsTransmissionInterval(50);

    }

    public void closeCamera(){
        cam.closeCameraDevice();
    }
}
