package LearnJavaForFTC.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import LearnJavaForFTC.mechanisms.ProgrammingBoard7;

@TeleOp
@Disabled
public class DistanceColorOpMode extends OpMode {
    ProgrammingBoard7 board = new ProgrammingBoard7();

    @Override
    public void init() {
        board.init(hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("Amount Red", board.getAmountRed());
        telemetry.addData("Distance (cm)", board.getDistance(DistanceUnit.CM));
        telemetry.addData("Distance (in)", board.getDistance(DistanceUnit.INCH));
    }
}
