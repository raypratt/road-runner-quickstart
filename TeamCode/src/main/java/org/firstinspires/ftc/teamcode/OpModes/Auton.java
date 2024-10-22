package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.mechanisms.Mechanisms;


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
