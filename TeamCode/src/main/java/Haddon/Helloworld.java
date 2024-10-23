package Haddon;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp(name = "HelloWorld", group = "Haddon")
public class Helloworld extends OpMode {
    public int five = 5;
    public boolean button = true;
    TouchSensor testButton;
    @Override
    public void init() {
        testButton = hardwareMap.touchSensor.get("touch_sensor");
    }

    @Override
    public void loop() {
        telemetry.addData("testButton", testButton.getValue());
        double joysticklx = gamepad1.left_stick_x;
        telemetry.addData("Left stick x", joysticklx);
        double joystickly = gamepad1.left_stick_y;
        telemetry.addData("Left stick y", joystickly);
        double joystickrx = gamepad1.right_stick_x;
        telemetry.addData("Right stick x", joystickrx);
        double joystickry = gamepad1.right_stick_y;
        telemetry.addData("Right stick y", joystickry);
        boolean button1 = gamepad1.x;
        telemetry.addData("X button", button1);
        boolean button2 = gamepad1.y;
        telemetry.addData("Y button", button2);
        boolean button3 = gamepad1.a;
        telemetry.addData("A button", button3);
        boolean button4 = gamepad1.b;
        telemetry.addData("B button", button4);
        boolean lbumper = gamepad1.left_bumper;
        telemetry.addData("Left bumper", lbumper);
        boolean rbumper = gamepad1.right_bumper;
        telemetry.addData("Right bumper", rbumper);
        double ltrigger = gamepad1.left_trigger;
        telemetry.addData("Left trigger", ltrigger);
        double rtrigger = gamepad1.right_trigger;
        telemetry.addData("Right trigger", rtrigger);

        if (gamepad1.left_stick_y < -0.5) {
            telemetry.addData("Left stick", "is negative and large");
        } else if (gamepad1.left_stick_y < 0) {
            telemetry.addData("Left stick", "is negative and small");
        } else if (gamepad1.left_stick_y < 0.5) {
            telemetry.addData("Left stick", "is positive and small");
        } else {
            telemetry.addData("Left stick", "is positive and large");
        }

        int angle = -270;
        while (angle < -180) {
            angle += 360;
        }
        for (int i = 0; i < 10; i++) {
            telemetry.addLine("hi :)");
        }
        if (!gamepad1.a) {
            joysticklx *= 0.5;
            telemetry.addData("slo", "mo");
        } else {
            joysticklx *= 2;
            telemetry.addData("zoomie", "mode");
        }
        double x = gamepad1.left_stick_y;
        double y = gamepad1.left_stick_x;
        if (gamepad1.b) {
            double copy_of_x = x;
            x = y;
            y = copy_of_x;
        }
        if (gamepad1.y) {
            print("Y is pressed?", "Yes");
        } else {
            print("Y is pressed", "No");
        }
    }
    public void print(String str, String str2) {
        telemetry.addData(str, str2);
    }
}
