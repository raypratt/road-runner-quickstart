package org.firstinspires.ftc.teamcode.mechanisms;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.android.AndroidSoundPool;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


@Config
public class Mechanisms {
    private DcMotor rightFront, leftFront, rightBack, leftBack, telescope, arm;
    IMU imu;
    public double gear = 0.6666;
    public double powerExponent = 1;
    RevBlinkinLedDriver led;
    AndroidSoundPool androidSoundPool;
    private CRServo left_servo,right_servo;
    private Servo wrist_servo;
    private PIDController controller;
    public double wrist_pos;


//    public static double ARM_P = 0.001, ARM_I =0, ARM_D =0.0000, ARM_F =0.05; // Gold Bot
    public static double ARM_P = 0.003, ARM_I =0, ARM_D =0.0000, ARM_F =0.05;
//    public static double TELESCOPE_P = 0.03, TELESCOPE_I = 0.01, TELESCOPE_D = 0; // Gold Bot
    public static double TELESCOPE_P = 0.01, TELESCOPE_I = 0.01, TELESCOPE_D = 0;
    private final double ticks_in_degrees = 8192/360;
    private static double ARM_OFFSET = 0;
    private final double ticks_to_inches = 1; // set = to 1 if using just ticks.
    public void init(HardwareMap hwMap) {
        //Motor inits
        rightFront = hwMap.get(DcMotor.class, "rightFront");
        leftFront = hwMap.get(DcMotor.class, "leftFront");
        rightBack = hwMap.get(DcMotor.class, "rightBack");
        leftBack = hwMap.get(DcMotor.class, "leftBack");
        telescope = hwMap.get(DcMotor.class, "telescope");
        arm = hwMap.get(DcMotor.class, "arm");

        arm.setDirection(DcMotorSimple.Direction.REVERSE);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        telescope.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


//        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        telescope.setDirection(DcMotorSimple.Direction.REVERSE);

        left_servo = hwMap.get (CRServo.class,"left_servo");
        right_servo = hwMap.get (CRServo.class,"right_servo");
        wrist_servo = hwMap.get(Servo.class,"wrist_servo");

        //IMU inits
        imu = hwMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.DOWN, RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        imu.initialize(parameters);

        //PIDF intis
        controller = new PIDController(ARM_P, ARM_I, ARM_D);
        int arm_start_ticks = arm.getCurrentPosition();

        //LED init
        //led = hwMap.get(RevBlinkinLedDriver.class, "led");

        //Sound inits
        //androidSoundPool = new AndroidSoundPool();
        //androidSoundPool.setVolume(1);
    }

    public void drive(double leftStickX, double leftStickY, double pivot){
        double y = -leftStickY;
        double x = leftStickX;
        double rx = pivot;
        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double rotX = x*Math.cos(-botHeading) - y*Math.sin(-botHeading);
        double rotY = x*Math.sin(-botHeading) + y*Math.cos(-botHeading);
        double max = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1.0);

        rotX = rotX*1.1;

        rightFront.setPower(gear*Math.pow(((rotY - rotX - rx) / max), powerExponent));
        rightBack.setPower(gear*Math.pow(((rotY + rotX - rx) / max), powerExponent));
        leftFront.setPower(gear*Math.pow(((rotY + rotX + rx) / max), powerExponent));
        leftBack.setPower(gear*Math.pow(((rotY - rotX + rx) / max), powerExponent));

    }
    public void setGear(double newGear){
        gear = newGear;
    }

    public double getGear() {
        return gear;
    }
    public double getPowerExponent(){
        return powerExponent;
    }

    public double getRFSpeed(){
        return rightFront.getPower();
    }

    public double getLFSpeed(){
        return leftFront.getPower();
    }

    public double getRBSpeed(){
        return rightBack.getPower();
    }

    public double getLBSpeed(){
        return leftBack.getPower();
    }

    public void resetYaw(){
        imu.resetYaw();
    }

    public double getBotHeading(){
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
    }

    public void intake_in(){
        left_servo.setPower(1);
        right_servo.setPower(-1);
    }

    public void intake_off(){
        left_servo.setPower(0);
        right_servo.setPower(0);
    }

    public void intake_out(){
        left_servo.setPower(-1);
        right_servo.setPower(1);
    }

    public void wrist_intake(){
        wrist_servo.setPosition(0);
    }

    public void wrist_basket(){
        wrist_pos= 0.5;
        wrist_servo.setPosition(0.5);
    }

    public void wrist_score() {
        wrist_pos = 1.0;
        wrist_servo.setPosition(1.0);
    }

    public void wrist(double inc) {
        wrist_pos += inc;
        wrist_servo.setPosition(wrist_pos);
    }

    public void  wrist_chamber() {
        wrist_pos = 0.9;
        wrist_servo.setPosition(0.9);
    }

    public void arm_out(){
        telescope.setPower(1);
    }

    public void arm_in(){
        telescope.setPower(-1);
    }

    public void arm_off(){
        telescope.setPower(0);
    }
    public void set_telescope(double target){
        controller.setPID(TELESCOPE_P, TELESCOPE_I, TELESCOPE_D);
        int telescopePos = telescope.getCurrentPosition();

        double pid = controller.calculate(telescopePos*ticks_to_inches, target);
        double power = pid;
        telescope.setPower(power);
    }

    public int getTelescopeTicks() {
        return telescope.getCurrentPosition();
    }
    
    public void arm_move(double power){
        arm.setPower(power);
    }

    public void set_arm(double target){
        controller.setPID(ARM_P, ARM_I, ARM_D);
        int armPos = arm.getCurrentPosition();
        double pid = controller.calculate(armPos, target*ticks_in_degrees);
        double ff = Math.cos(Math.toRadians(target/ticks_in_degrees))* ARM_F;
        double power = pid + ff;
        arm.setPower(power);
    }

    public double get_arm_power(int target){
        controller.setPID(ARM_P, ARM_I, ARM_D);
        int armPos = arm.getCurrentPosition();
        double pid = controller.calculate(armPos, target*ticks_in_degrees);
        double ff = Math.cos(Math.toRadians(target/ticks_in_degrees))* ARM_F;
        double power = pid + ff;
        return power;
    }

    public double get_arm_pos_degrees(){
        return arm.getCurrentPosition()/ticks_in_degrees;
    }

    public double get_arm_pos_ticks(){
        return arm.getCurrentPosition();
    }

    public void set_telescope_ticks(int ticks){
        telescope.setTargetPosition(ticks);
        telescope.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        telescope.setPower(1);
    }


    /*
    public void lights(Gamepad gamepad1, Gamepad gamepad2, ElapsedTime eTime, int wTime, ElapsedTime vTime) {
        if ((eTime.seconds() - wTime <= wTime) && vTime.seconds() > 2) {
            led.setPattern(RevBlinkinLedDriver.BlinkinPattern.BREATH_GRAY);
            gamepad1.rumbleBlips(2);
            gamepad2.rumbleBlips(2);
            androidSoundPool.play("RawRes:ss_siren");
            int J = 0;
            if (J == 0) {
                gamepad1.setLedColor(1, 0.85, 0, 10000);
                gamepad2.setLedColor(0.5, 0, 0, 10000);
                J += 1;
            } else if (J == 1) {
                gamepad2.setLedColor(1, 0.85, 0, 10000);
                gamepad1.setLedColor(0.5, 0, 0, 10000);
                J += 1;
            } else {
                J = 0;
            }
            vTime.reset();
        }
    } */
}
