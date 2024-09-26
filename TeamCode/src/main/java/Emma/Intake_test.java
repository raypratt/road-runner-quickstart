
package Emma;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "intake test", group = "Emma")
public class        Intake_test extends OpMode {
    private CRServo left_servo;
    private CRServo right_servo;
    @Override
    public void init() {
        left_servo = hardwareMap.get(CRServo.class,"servoLeft");
        right_servo = hardwareMap.get(CRServo.class,"servoRight");

    }
    public void moveleft(double power){
        left_servo.setPower(power);
    }
    public void moveright(double power){
        right_servo.setPower(power);
    }

    public void loop() {
        if (gamepad1.y) {
            moveleft(1);
            moveright(-1);

        } else if (gamepad1.a){
            moveleft(-1);
            moveright(1);
        }
       else{
           moveleft(0);
           moveright(0);
       }

    }


}
