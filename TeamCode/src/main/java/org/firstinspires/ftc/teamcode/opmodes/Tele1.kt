package org.firstinspires.ftc.teamcode.opmodes

import com.qualcomm.hardware.bosch.BNO055IMU
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.util.ElapsedTime
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction
import com.qualcomm.robotcore.util.Range
import org.firstinspires.ftc.robotcore.external.navigation.*
import org.firstinspires.ftc.teamcode.input.Controller
import org.firstinspires.ftc.teamcode.misc.ADXL345

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */
@TeleOp(name = "Tele1", group = "Iterative Opmode")
class Tele1 : OpMode() {
    // Declare OpMode members.
    private val runtime = ElapsedTime()
    private var lfDrive: DcMotor? = null
    private var rfDrive: DcMotor? = null
    private var lrDrive: DcMotor? = null
    private var rrDrive: DcMotor? = null
    private var controller1: Controller? = null
    private var controller2: Controller? = null

    private var adxl345: ADXL345? = null

    var imu: BNO055IMU? = null

    private var posx = 0.0
    private var posy = 0.0
//    private var posz = 0.0
    private var velx = 0.0
    private var vely = 0.0
//    private var velz = 0.0
    private var lastAccel = Acceleration()
    private var lastTime = runtime.seconds()

    /*
     * Code to run ONCE when the driver hits INIT
     */
    override fun init() {
        telemetry.addData("Status", "Initialized")

        lfDrive= hardwareMap.get(DcMotor::class.java, "leftFrontDrive")
        rfDrive = hardwareMap.get(DcMotor::class.java, "rightFrontDrive")
        lrDrive = hardwareMap.get(DcMotor::class.java, "leftRearDrive")
        rrDrive = hardwareMap.get(DcMotor::class.java, "rightRearDrive")

        controller1 = Controller(gamepad1)
        controller2 = Controller(gamepad2)

        adxl345 = hardwareMap.get(ADXL345::class.java, "acc")

        adxl345!!.setDataFormatLessRaw(false, true, false, ADXL345.GRange.TWO)

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        lfDrive!!.setDirection(Direction.REVERSE)
        lfDrive!!.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        lrDrive!!.setDirection(Direction.FORWARD)
        lrDrive!!.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        rfDrive!!.setDirection(Direction.FORWARD)
        rfDrive!!.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        rrDrive!!.setDirection(Direction.REVERSE)
        rrDrive!!.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE

        // mumbo jumbo boilerplate from https://stemrobotics.cs.pdx.edu/node/7265
        val parameters = BNO055IMU.Parameters()
        parameters.mode                = BNO055IMU.SensorMode.IMU
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC
        parameters.loggingEnabled      = false
        parameters.mode                = BNO055IMU.SensorMode.IMU
        imu = hardwareMap.get(BNO055IMU::class.java, "imu")
        imu!!.initialize(parameters)
        lastAccel = imu!!.acceleration

        velx = 0.0
        vely = 0.0
        posx = 0.0
        posy = 0.0

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized")
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    override fun init_loop() {}

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    override fun start() {
        runtime.reset()
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    override fun loop() {
        val newTime = runtime.seconds()
//        val delta: Double = newTime - lastTime
        lastTime = newTime


        telemetry.addData("accelerometer device id", adxl345!!.deviceIDRaw)

        telemetry.addData("accelerometer X", adxl345!!.xRaw)
        telemetry.addData("accelerometer Y", adxl345!!.yRaw)
        telemetry.addData("accelerometer Z", adxl345!!.zRaw)

        telemetry.addData("accelerometer mode", Integer.toBinaryString(adxl345!!.dataFormatRaw.toInt()))

        controller1!!.update();
        controller2!!.update()
        
        driveXYR(controller1!!.left_stick_x, controller1!!.left_stick_y, controller1!!.right_stick_x)



        val newaccel = imu!!.linearAcceleration

        // get time difference in seconds (float)
        val delta = (newaccel.acquisitionTime - lastAccel.acquisitionTime).toDouble() / 1000000000.0

        telemetry.addData("delta", "%f", delta)
        telemetry.addData("accel", "x (%.2f) y (%.2f) z (%.2f)", newaccel.xAccel, newaccel.yAccel, newaccel.zAccel)

        // riemann sum
        val velChngX = (newaccel.xAccel + lastAccel.xAccel) * delta / 2.0 // trapezoid area
        val velChngY = (newaccel.yAccel + lastAccel.yAccel) * delta / 2.0
//        val velChngZ = newaccel.zAccel * delta

        if (Math.abs(velChngX) > 0.05) {
        velx += velChngX
        }
        if (Math.abs(velChngY) > 0.05) {
            vely += velChngY
        }

//        velz += velChngZ

        telemetry.addData("vel", "x (%.2f) y (%.2f)", velx, vely)

        // same again to get pos
        posx += velx * delta
        posy += vely * delta
//        posz += velz * delta

        telemetry.addData("pos", "x (%.2f) y (%.2f)", posx, posy)

        val or = imu!!.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES)

        telemetry.addData("orientation", "(%.2f)", or.firstAngle)

        telemetry.addData("orientation2", "%s", imu!!.angularOrientation.toString())

        // Show the elapsed game time and wheel power.
//        telemetry.addData("Status", "Run Time: $runtime")
//        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower)
        telemetry.update()
        lastAccel = newaccel
    }

    // Calculates powers for mecanum wheel drive
    fun driveXYR(x: Double, y: Double, r: Double) {
        val r = -r // invert rotation
        // set motor powers, assumed that positive power = forwards motion for wheel, there's often a motor.reverse() function to help with this
        rfDrive!!.power = (y - x + r)
        lfDrive!!.power = (y + x - r)
        lrDrive!!.power = -1 * (y - x - r)
        rrDrive!!.power = -1 * (y + x + r)
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    override fun stop() {}
}