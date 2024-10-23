package Haddon;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorColor;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp (name="do_stuff-haddon", group = "haddon")
public class do_stuff extends OpMode {
    public TouchSensor testbutton;
    public DcMotor testmotor;
    public Servo testservo;
    public IMU imu;
    public ColorSensor colorsensor;
    @Override
    public void init() {
        testbutton=hardwareMap.touchSensor.get("touch_sensor");
        testmotor=hardwareMap.dcMotor.get("motor");
        testservo=hardwareMap.servo.get("servo");
        imu=hardwareMap.get(IMU.class, "imu");
        colorsensor=hardwareMap.colorSensor.get("sensor_color_distance");
    }

    @Override
    public void loop() {
        testmotor.setPower(-gamepad1.left_stick_y);
        testservo.setPosition(gamepad1.right_stick_x);
        telemetry.addData("Angle",imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        telemetry.addData("Color&Distance?", colorsensor.red());
        if (colorsensor.red()<14){
            telemetry.addData("Color","red");
        } else {
            telemetry.addData("Color","not red idk");
        }
        }
}
