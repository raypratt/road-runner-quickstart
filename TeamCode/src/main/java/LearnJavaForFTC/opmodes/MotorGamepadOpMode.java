package LearnJavaForFTC.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import LearnJavaForFTC.mechanisms.ProgrammingBoard4;

@TeleOp
public class MotorGamepadOpMode extends OpMode {
    ProgrammingBoard4 board = new ProgrammingBoard4();
    @Override
    public void init() {
        board.init(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            board.setMotorSpeed(0.5);
        }
        else {
            board.setMotorSpeed(0.5);
        }
        telemetry.addData("Motor rotations", board.getMotorRotations());
    }
}
