package Haddon;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp(name = "Intake_test", group = "Haddon")
public class Intake_test extends OpMode {
    private CRServo leftServo;
    private CRServo rightServo;
    @Override
    public void init () {
        leftServo = hardwareMap.get(CRServo.class, "servoLeft");
        rightServo = hardwareMap.get(CRServo.class, "servoCRRight");
    }
    public void moveservoLeft(double powerLeft) {
        leftServo.setPower(powerLeft);
    }
    public void moveservoRight(double powerRight) {
        rightServo.setPower(powerRight);
    }
    @Override
    public void loop() {
        if (gamepad1.a) {
            leftServo.setPower(1);
            rightServo.setPower(-1);
        }
        else if (gamepad1.y) {
            leftServo.setPower(-1);
            rightServo.setPower(1);
        }
        else {
            leftServo.setPower(0);
            rightServo.setPower(0);
        }
    }
}
