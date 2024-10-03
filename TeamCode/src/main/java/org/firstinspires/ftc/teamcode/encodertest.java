package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "Encoder test")
public class encodertest extends OpMode {
    DcMotor motor;
    @Override
    public void init() {
     motor = hardwareMap.get(DcMotor.class,"motor");
    }

    @Override
    public void loop() {
        motor.setPower(1);
        telemetry.addData("motorposition", motor.getCurrentPosition());

    }
}
