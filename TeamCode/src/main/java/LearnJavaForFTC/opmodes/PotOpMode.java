package LearnJavaForFTC.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import LearnJavaForFTC.mechanisms.ProgrammingBoard6;

@TeleOp
public class PotOpMode extends OpMode {
    ProgrammingBoard6 board = new ProgrammingBoard6();

    @Override
    public void init() {
        board.init(hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("Pot Angle", board.getPotAngle());
    }
}
