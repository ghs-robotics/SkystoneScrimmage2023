package org.firstinspires.ftc.teamcode.opmodes

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.util.ElapsedTime
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction
import com.qualcomm.robotcore.util.Range
import org.firstinspires.ftc.teamcode.input.Controller

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
@Disabled
class Tele1 : OpMode() {
    // Declare OpMode members.
    private val runtime = ElapsedTime()
    private var lfDrive: DcMotor = hardwareMap.get(DcMotor::class.java, "leftFrontDrive")
    private var rfDrive: DcMotor = hardwareMap.get(DcMotor::class.java, "rightFrontDrive")
    private var lrDrive: DcMotor = hardwareMap.get(DcMotor::class.java, "leftRearDrive")
    private var rrDrive: DcMotor = hardwareMap.get(DcMotor::class.java, "rightRearDrive")
    private var controller1: Controller = Controller(gamepad1)
    private var controller2: Controller = Controller(gamepad2)

    /*
     * Code to run ONCE when the driver hits INIT
     */
    override fun init() {
        telemetry.addData("Status", "Initialized")


        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        lfDrive.setDirection(Direction.FORWARD)
        lrDrive.setDirection(Direction.FORWARD)
        rfDrive.setDirection(Direction.REVERSE)
        rrDrive.setDirection(Direction.REVERSE)

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
        controller1.update();
        controller2.update()
        
        driveXYR(controller1.left_stick_x, controller1.left_stick_y, controller1.right_stick_x)

        // Show the elapsed game time and wheel power.
//        telemetry.addData("Status", "Run Time: $runtime")
//        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower)
    }

    // Calculates powers for mecanum wheel drive
    fun driveXYR(x: Double, y: Double, r: Double) {
        val r = -r // invert rotation
        // set motor powers, assumed that positive power = forwards motion for wheel, there's often a motor.reverse() function to help with this
        rfDrive.power = (y - x + r)
        lfDrive.power = (y + x - r)
        lrDrive.power = -1 * (y - x - r)
        rrDrive.power = -1 * (y + x + r)
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    override fun stop() {}
}