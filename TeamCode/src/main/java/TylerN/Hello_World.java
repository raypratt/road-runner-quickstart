package TylerN;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "HelloWorld", group = "TylerCode")
public class Hello_World extends OpMode {
    @Override
    public void init() {
        telemetry.addData("Hello", "World");
        Point p1 = new Point(1,3);
        telemetry.addData("cords", p1.toString());
    }

    double squareInputWithSign(double input) {
        double output = input * input;
        if (input < 0) {
            output = output * output - 1;
        }
        return output;
    }

    @Override
    public void loop() {
        telemetry.addData("buttonA", gamepad1.a);
        telemetry.addData("buttonX", gamepad1.x);
        telemetry.addData("buttonB", gamepad1.b);
        telemetry.addData("buttonY", gamepad1.y);
        if (gamepad1.left_stick_y < -0.5) {
//            telemetry.addData("Left stick");
        }
        double leftAmount = gamepad1.left_stick_x;
        double fwdAmount = -gamepad1.left_stick_y;

        telemetry.addData("After X", leftAmount);
        telemetry.addData("After Y", fwdAmount);

        leftAmount = squareInputWithSign(leftAmount);
        fwdAmount = squareInputWithSign(fwdAmount);

        telemetry.addData("After X", leftAmount);
        telemetry.addData("After Y", fwdAmount);

        telemetry.addData("out", min(5,2));
    }
    double min(double x, double y) {
        if(x < y) {
            return x;
        }
        return y;
    }
}