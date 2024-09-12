package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Base", group="Alpha")
public class Base extends OpMode {
    Mechanisms mechs = new Mechanisms();

    @Override
     public void init(){
        mechs.init(hardwareMap);
    }

    @Override
    public void loop(){
        if (gamepad1.a) {
            mechs.setGear(0.33);
        }
        else if (gamepad1.x) {
            mechs.setGear(0.66);
        }
        else if (gamepad1.y) {
            mechs.setGear(1.0);
        }
        if (gamepad1.options){
            mechs.resetYaw();
        }

        mechs.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
        telemetry.addData("Gear", mechs.getGear());
        telemetry.addData("RFSpeed", mechs.getRFSpeed());
    }
}
