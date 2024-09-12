package LearnJavaForFTC;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
@Disabled
public class ClassMemberOpMode extends OpMode{
    boolean initDone;

    @Override
    public void init() {
        telemetry.addData("init Done", initDone);
        initDone = true;
    }

    double squareInputWithSign(double input){
        double output = input * input;
        if (input < 0){
            output = output * -1;
        }
        return output;
    }

    @Override
    public void loop() {
        telemetry.addData("init Done", initDone);
    }
}
