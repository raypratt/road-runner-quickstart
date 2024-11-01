package org.firstinspires.ftc.teamcode.logging;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import org.firstinspires.ftc.teamcode.logging.Datalogger;


import org.firstinspires.ftc.robotcore.internal.files.DataLogger;
import org.firstinspires.ftc.teamcode.mechanisms.Mechanisms;

@TeleOp
public class log extends OpMode {

    VoltageSensor battery;
    Datalog datalog;

    @Override
    public void init() {
        Mechanisms mechs = new Mechanisms();
        mechs.init(hardwareMap);

    }

    public void loop(){

    }
    public static class Datalog{

    }
}
