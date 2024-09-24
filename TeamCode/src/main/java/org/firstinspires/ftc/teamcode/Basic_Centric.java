package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@TeleOp(name = "BasicCentric (Blocks to Java)")
public class Basic_Centric extends LinearOpMode {

    private BNO055IMU imu;
    private DcMotor rightFront;
    private DcMotor rightBack;
    private DcMotor leftFront;
    private DcMotor leftBack;
    private CRServo left_servo;
    private CRServo right_servo;

    double max;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        double LF;
        double RF;
        double LR;
        double RR;
        Orientation angles;
        BNO055IMU.Parameters imuParameters;
        double stickAngle;
        double magnitude;
        int reverse;
        double Gear;
        float xStick;
        float yStick;
        float IMUheading;
        double driveAngle;
        double vertical;
        double horizontal;
        float pivot;
        String gearText;

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");

        // Put initialization blocks here.
        // Create new IMU Parameters object.
        imuParameters = new BNO055IMU.Parameters();
        // Use degrees as angle unit.
        imuParameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        // Express acceleration as m/s^2.
        imuParameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        // Disable logging.
        imuParameters.loggingEnabled = false;
        // Initialize IMU.
        imu.initialize(imuParameters);
        // Prompt user to press start buton.
        telemetry.addData("IMU Example", "Press start to continue...");
        telemetry.update();
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        reverse = 1;
        Gear = 0.7;
        right_servo = hardwareMap.get(CRServo.class,"servoRight");
        left_servo = hardwareMap.get(CRServo.class,"servoLeft");
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                // Put loop blocks here.
                // Get Z rotation
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                xStick = gamepad1.left_stick_x;
                yStick = -gamepad1.left_stick_y;
                stickAngle = Math.atan2(yStick, xStick) / Math.PI * 180;
                magnitude = Math.sqrt(Math.pow(gamepad1.left_stick_x, 2) + Math.pow(gamepad1.left_stick_y, 2));
                IMUheading = angles.firstAngle - 90;
                driveAngle = IMUheading - stickAngle;
                if (gamepad1.back) {
                    imu.initialize(imuParameters);
                }
                if (gamepad2.a) {
                    left_servo.setPower(1);
                    right_servo.setPower(-1);
                } else if (gamepad2.y) {
                    left_servo.setPower(-1);
                    right_servo.setPower(1);
                }
                else {
                    left_servo.setPower(0);
                    right_servo.setPower(0);
                }
                // Display orientation info.
                if (gamepad1.y || gamepad1.y) {
                    Gear = 0.7;
                }
                if (gamepad1.x || gamepad1.x) {
                    Gear = 0.55;
                }
                if (gamepad1.a || gamepad1.a) {
                    Gear = 0.3;
                }
                vertical = magnitude * Math.cos(driveAngle / 180 * Math.PI);
                horizontal = magnitude * Math.sin(driveAngle / 180 * Math.PI);
                pivot = gamepad1.right_stick_x;
                RF = reverse * (-pivot + (vertical - horizontal));
                RR = reverse * (-pivot + reverse * (vertical + horizontal));
                LF = reverse * (pivot + reverse * (vertical + horizontal));
                LR = reverse * (pivot + reverse * (vertical - horizontal));
                max = maxNum(LF, RF, LR, RR);
                rightFront.setPower(Gear * (RF / max));
                rightBack.setPower(Gear * (RR / max));
                leftFront.setPower(Gear * (LF / max));
                leftBack.setPower(Gear * (LR / max));
                if (Gear >= 0.7) {
                    gearText = "High";
                } else if (Gear == 0.55) {
                    gearText = "Medium";
                } else {
                    gearText = "Low";
                }
                telemetry.addData("Gear", gearText);
                telemetry.update();
            }
        }
    }

    /**
     * Describe this function...
     */
    private double maxNum(double LF, double RF, double LR, double RR) {
        if (Math.abs(LF) > Math.abs(LR)) {
            max = Math.abs(LF);
        } else {
            max = Math.abs(LR);
        }
        if (Math.abs(max) < Math.abs(RF)) {
            max = Math.abs(RF);
        }
        if (Math.abs(max) < Math.abs(RR)) {
            max = Math.abs(RR);
        }
        return max;
    }
}