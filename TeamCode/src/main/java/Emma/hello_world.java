package Emma;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "hello_world", group = "Emma")
public class hello_world extends OpMode {
    public int number = 2;
    public char letter = 'b';
    public double decimal = .5;
    public boolean below = true;
    public boolean buttion;

    @Override
    public void init() {
        telemetry.addData("Hello", "World");
        telemetry.addData("This is a number", number);

        Point p1 = new Point(1,3);
        telemetry.addData("coords", p1.toString());

    }
    double squarinputwithsing(double input){
        double output = input * input;
        if(input<0){
            output = output * -1;
        }
        return output;
    }

    @Override
    public void loop() {
        number = 6;
        telemetry.addData("This is a number", number);
        double joystick1 = gamepad1.left_stick_x;
        telemetry.addData("joystickvalue_left_x", joystick1);
        double joystick2 = gamepad1.left_stick_y;
        telemetry.addData("joystickvalue_left_y", joystick2);
        boolean buttona = gamepad1.a;
        double joystick3 = gamepad1.right_stick_x;
        telemetry.addData("joystickvalue_right_x", joystick3);
        double joystick4 = gamepad1.right_stick_y;
        telemetry.addData("joystickvalue_right_y", joystick4);
        telemetry.addData("buttona", buttona);
        boolean buttonb = gamepad1.b;
        telemetry.addData("buttonb", buttonb);
        boolean buttony = gamepad1.y;
        telemetry.addData("buttony", buttony);
        boolean buttonx = gamepad1.x;
        telemetry.addData("buttonx", buttonx);
        if (gamepad1.left_stick_y < 0) {
            telemetry.addData("left stick", " is negitive");
        }
        if (gamepad1.left_stick_y > 0) {
            telemetry.addData("left stick", " is positive");
        } else {
            telemetry.addData("right stick", " is negitive");

        }
        if (gamepad1.right_stick_y > 0) {
            telemetry.addData("right stick", " is positive");
        }
        if (gamepad1.right_stick_y == -1) {
            telemetry.addData("right stick", " full power");
        }
        if (gamepad1.left_stick_y < -0.5) {
            telemetry.addData("left stick", " is negitive and large");
        } else if (gamepad1.left_stick_y < 0) {
            telemetry.addData("left stick", " is negitive and small");
        } else if (gamepad1.left_stick_y < 0.5) {
            telemetry.addData("left stick", " is postive and small");
        } else {
            telemetry.addData("left stick", " is postive and large");
        }
        telemetry.addData("left stick y", gamepad1.left_stick_y);
        int angle = -270;
        while (angle < -180) {
            angle += 360;
        }
        for (int i = 0; i < 4; i++) {
            telemetry.addData("hello", ":)");
        }
        if (!gamepad1.a) {
            joystick3 *= 0.5;
            telemetry.addData("mode", "slow");

        } else {
            telemetry.addData("mode", "turbo");
        }
        double x_= gamepad1.left_stick_x;
        double y_= gamepad1.left_stick_y;

        if (gamepad1.b) {
            double copy_x = x_;
            x_ = y_;
            y_ = copy_x;

        }


        double leftAmount = gamepad1.left_stick_x;
        double fwdAmount = -gamepad1.left_stick_y;

        telemetry.addData("before x", leftAmount );
        telemetry.addData("before y", fwdAmount );

        leftAmount = squarinputwithsing(leftAmount);
        fwdAmount = squarinputwithsing(fwdAmount);

        telemetry.addData("after x",leftAmount);
        telemetry.addData("after y",fwdAmount);
        telemetry.addData("out",min(5,2));
    }
    double min(double x, double y){
        if(x<y){
            return x;
        }
        return y;

    }
}