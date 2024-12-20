package org.firstinspires.ftc.teamcode.drivetrain;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;


import org.firstinspires.ftc.robotcore.external.android.AndroidSoundPool;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


@Config
public class DriveTrain {
    private DcMotor rightFront, leftFront, rightBack, leftBack;
    IMU imu;
    public double gear = 0.6666;
    public double powerExponent = 1;
    RevBlinkinLedDriver led;
    AndroidSoundPool androidSoundPool;
    private PIDController controller;
    public static double p=0.002, i=0, d=0.0001, f=0.05;
    private final double ticks_in_degrees = 8192/360;

    public void init(HardwareMap hwMap) {
        //Motor inits
        rightFront = hwMap.get(DcMotor.class, "rightFront");
        leftFront = hwMap.get(DcMotor.class, "leftFront");
        rightBack = hwMap.get(DcMotor.class, "rightBack");
        leftBack = hwMap.get(DcMotor.class, "leftBack");

//        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.REVERSE);

        //IMU inits
        imu = hwMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.DOWN, RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        imu.initialize(parameters);

        //PIDF intis
        controller = new PIDController(p, i, d);

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
