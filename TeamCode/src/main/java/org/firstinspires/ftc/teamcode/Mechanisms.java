package org.firstinspires.ftc.teamcode;


import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.hardware.digitalchickenlabs.OctoQuad;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.android.AndroidSoundPool;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Mechanisms {
    private DcMotor rightFront, leftFront, rightBack, leftBack;
    private AndroidSoundPool androidSoundPool;
    IMU imu;
    public double gear = 0.6666;
    public double powerExponent = 1;

    public void init(HardwareMap hwMap){
        //Motor inits
        rightFront = hwMap.get(DcMotor.class, "rightFront");
        leftFront = hwMap.get(DcMotor.class, "leftFront");
        rightBack = hwMap.get(DcMotor.class, "rightBack");
        leftBack = hwMap.get(DcMotor.class, "leftBack");
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.REVERSE);

        //Sound inits
        androidSoundPool.setVolume(1);
        androidSoundPool.initialize(SoundPlayer.getInstance());

        //IMU inits
        imu = hwMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.DOWN, RevHubOrientationOnRobot.UsbFacingDirection.LEFT));
        imu.initialize(parameters);

        //Octoquad inits
        OctoQuad octoquad;
        final int ODO_LEFT = 0;
        final int ODO_RIGHT = 1;
        final int ODO_PERP = 2;
        octoquad = hwMap.get(OctoQuad.class, "octoquad");
    }

    public void drive(double leftStickX, double leftStickY, double pivot){
        double y = -leftStickY;
        double x = leftStickX;
        double rx = pivot;

        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double rotX = x*Math.cos(-botHeading) - y*Math.sin(-botHeading);
        double rotY = x*Math.sin(-botHeading) + y*Math.cos(-botHeading);
        double max = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1.0);

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

    public void resetYaw(){
        imu.resetYaw();
    }
}
