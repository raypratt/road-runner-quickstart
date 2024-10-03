package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;


@Autonomous (name="Auton", group="Alpha")
public class Auton extends OpMode{

    Mechanisms mechs = new Mechanisms();

    @Override
    public void init(){
        mechs.init(hardwareMap);
    }


    @Override
    public void loop(){

    }
}
