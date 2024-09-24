package TylerN;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class intake extends OpMode {
    private CRServo left_servo;
    private CRServo right_servo;


    @Override
    public void init() {
        right_servo = hardwareMap.get(CRServo.class,"servoRight");
        left_servo = hardwareMap.get(CRServo.class,"servoLeft");
    }

    public void moveLeft(double power) {
        left_servo.setPower(power);
    }
    public void moveRight(double power) {
        right_servo.setPower(power);
    }


    @Override
    public void loop() {
        telemetry.addData("buttonA", gamepad1.a);
        telemetry.addData("buttonY", gamepad1.y);
        if (gamepad1.a) {
            left_servo.setPower(1);
            right_servo.setPower(-1);
        }
        else {
            left_servo.setPower(0);
            right_servo.setPower(0);
        }
        if (gamepad1.y) {
            left_servo.setPower(-1);
            right_servo.setPower(1);
        }
        else {
            left_servo.setPower(0);
            right_servo.setPower(0);
        }
    }
}
