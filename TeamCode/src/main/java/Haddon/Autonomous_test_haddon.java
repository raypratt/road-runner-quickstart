package Haddon;

import com.qualcomm.hardware.ams.AMSColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.onbotjava.handlers.objbuild.WaitForBuild;

@Autonomous (name="Haddon_Auton",group = "Haddon")
public class Autonomous_test_haddon extends OpMode {
    private DcMotor rightFront, leftFront, rightBack, leftBack;
    @Override
    public void init() {
        rightFront=hardwareMap.get(DcMotor.class, "rightFront");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void start() {
    }

    @Override
    public void loop() {
        rightFront.setPower(1);
        leftFront.setPower(1);
        rightBack.setPower(1);
        leftBack.setPower(1);
    }
}
