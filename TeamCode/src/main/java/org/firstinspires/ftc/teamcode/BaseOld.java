package org.firstinspires.ftc.teamcode;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.android.AndroidSoundPool;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@TeleOp(name = "Base2023")
public class  BaseOld extends OpMode {

    private BNO055IMU imu;
    private DcMotor rightFrontAsDcMotor;
    private DcMotor rightRearAsDcMotor;
    private DcMotor leftFrontAsDcMotor;
    private DcMotor leftRearAsDcMotor;
    private AndroidSoundPool androidSoundPool;

    double gear;
    double Time;
    double max;
    int I;
    double VTime;
    int J;
    ElapsedTime Controller_Timer;
    ElapsedTime VibrateTime;
    BNO055IMU.Parameters imuParameters;
    int powerExponent;

    @Override
    public void init(){

    }
    @Override
    public void loop(){

}
    /**
     * Describe this function...
     */
    private void Drive() {
        double LF;
        double RF;
        double LR;
        double RR;
        Orientation angles;
        float xStick;
        float yStick;
        double stickAngle;
        double magnitude;
        float IMUheading;
        double driveAngle;
        double vertical;
        double horizontal;
        float pivot;

        if (gamepad1.a) {
            gear = 0.4;
        }
        if (gamepad1.x) {
            gear = 0.7;
        }
        if (gamepad1.y) {
            gear = 1;
        }
        // Get Z rotation
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        xStick = gamepad1.left_stick_x;
        yStick = -gamepad1.left_stick_y;
        stickAngle = Math.atan2(yStick, xStick) / Math.PI * 180;
        IMUheading = angles.firstAngle - 90;
        driveAngle = IMUheading - stickAngle;
        if (gamepad1.back) {
            imu.initialize(imuParameters);
        }
    }

    /**
     * Describe this function...
     */
    private void init2() {
        gear = 1;
        I = 0;
        J = 0;
        androidSoundPool.setVolume(1);
        androidSoundPool.initialize(SoundPlayer.getInstance());
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
        powerExponent = 3;
        rightFrontAsDcMotor.setDirection(DcMotor.Direction.REVERSE);
        rightRearAsDcMotor.setDirection(DcMotor.Direction.REVERSE);
        Controller_Timer = new ElapsedTime(System.nanoTime());
        VibrateTime = new ElapsedTime(System.nanoTime());
    }

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
  /*  @Override
        init() {
            imu = hardwareMap.get(BNO055IMU.class, "imu");
            androidSoundPool = new AndroidSoundPool();

            init2();
        }
        loop(){
            Controls();
            Drive();
            telemetry2();
            }
        }

        androidSoundPool.close();
    }

    /**
     * Describe this function...
     */
   /* private void telemetry2() {
        telemetry.addData("Time", Time);
        telemetry.addData("VibrateTime", VTime);
        telemetry.update();
    }

    /**
     * Describe this function...
     */
    private void Controls() {
    }

    /**
     * Describe this function...
     */
   /* private int sgn(double Stick) {
        int sgn2;

        if (Math.abs(Stick) == Stick) {
            sgn2 = 1;
        } else {
            sgn2 = -1;
        }
        return sgn2;
    }

    /**
     * Describe this function...
     */
   /* private double maxNum(double LF, double RF, double LR, double RR) {
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
    }*/
}
