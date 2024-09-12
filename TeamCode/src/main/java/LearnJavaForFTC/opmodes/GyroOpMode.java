package LearnJavaForFTC.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import LearnJavaForFTC.mechanisms.ProgrammingBoard8;

@TeleOp
@Disabled
public class GyroOpMode extends OpMode {
    ProgrammingBoard8 board = new ProgrammingBoard8();

    @Override
    public void init() {
        board.init(hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("Our Heading", board.getHeading(AngleUnit.DEGREES));

    }
}
