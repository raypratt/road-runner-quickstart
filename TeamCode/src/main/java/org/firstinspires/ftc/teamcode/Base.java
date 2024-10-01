package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name = "Base", group="Alpha")

public class Base extends OpMode {
    public ElapsedTime elapsedTime;
    public ElapsedTime vibrateTime;
    public int wTime = 115;

    //Initialize
    Mechanisms mechs = new Mechanisms();

    @Override
     public void init(){

        mechs.init(hardwareMap);

    }

    @Override
    public void start(){
        elapsedTime.reset();
        vibrateTime.reset();
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
        //mechs.lights(gamepad1, gamepad2, elapsedTime, wTime, vibrateTime);

        telemetry.addData("Gear", mechs.getGear());
        telemetry.addData("RF Speed", mechs.getRFSpeed());
        telemetry.addData("LF Speed", mechs.getLFSpeed());
        telemetry.addData("RB Speed", mechs.getRBSpeed());
        telemetry.addData("LB Speed", mechs.getLBSpeed());
    }

}
