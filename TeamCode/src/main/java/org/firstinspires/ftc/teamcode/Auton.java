package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;


@Autonomous (name="Auton", group="Alpha")
public class Auton extends OpMode{

    Mechanisms mechs = new Mechanisms();
    String color;


    @Override
    public void init(){
        mechs.init(hardwareMap);
    }

    @Override
    public void init_loop(){
        telemetry.addLine("Press X for Blue, 0 for Red");
        if (gamepad2.cross){
            telemetry.addLine("Blue Selected");
            color = "blue";
        }
        if (gamepad2.circle){
            telemetry.addLine("Red Selected");
            color = "red";
        }
    }
    @Override
    public void loop(){

    }
}
