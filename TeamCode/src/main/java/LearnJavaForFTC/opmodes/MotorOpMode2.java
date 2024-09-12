package LearnJavaForFTC.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import LearnJavaForFTC.mechanisms.ProgrammingBoard4;

@TeleOp
@Disabled
public class MotorOpMode2 extends OpMode{
    ProgrammingBoard4 board = new ProgrammingBoard4();
    @Override
    public void init(){
        board.init(hardwareMap);
    }

    @Override
    public void loop(){
        board.setMotorSpeed(0.5);
        telemetry.addData("Motor Rotations", board.getMotorRotations());
        telemetry.addData("Encoder Position", board.getCurrentPosition());
        telemetry.addData("Ticks Per Rotation", board.getTicksPerRotation());
        telemetry.addData("Gearing", board.getGearing());
    }
}
