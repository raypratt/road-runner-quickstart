package LearnJavaForFTC.opmodes;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import LearnJavaForFTC.mechanisms.ProgrammingBoard5;

@TeleOp
@Disabled
public class ServoGamepadOpMode extends OpMode {
    ProgrammingBoard5 board = new ProgrammingBoard5();

    @Override
    public void init() {
        board.init(hardwareMap);
    }

    @Override
    public void loop(){
    /*    if (gamepad1.a) {
            board.setServoPosition(1.0);
        }
        else if (gamepad1.b){
            board.setServoPosition(0);
        }
        else {
            board.setServoPosition(0.5);
        } */
        board.setServoPosition(gamepad1.left_trigger);
    }
}
