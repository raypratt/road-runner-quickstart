package LearnJavaForFTC.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import LearnJavaForFTC.mechanisms.ProgrammingBoard4;

@TeleOp
public class MotorSensorOpMode extends OpMode {
    ProgrammingBoard4 board = new ProgrammingBoard4();
    @Override
    public void init() {
        board.init(hardwareMap);
    }

    @Override
    public void loop(){
        if (board.isTouchSensorPressed()) {
            board.setMotorSpeed(0.5);
        }
        else {
            board.setMotorSpeed(0.0);
        }
        telemetry.addData("Ticks Per Rotation", board.getTicksPerRotation());
        telemetry.addData("Gearing", board.getGearing());
        telemetry.addData("Motor rotations", board.getMotorRotations());
    }
}

