package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.mechanisms.Mechanisms;

@TeleOp
public class potTest extends OpMode {
    private AnalogInput telePot;
    Mechanisms mechs = new Mechanisms();
    @Override
    public void init() {
        mechs.init(hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("TelePot Angle", mechs.getPotentiometer());
        telemetry.addData("TelePot voltage", mechs.getVoltagePotentiometer());
    }

    public double getPotodemtery(){
        return Range.scale(telePot.getVoltage(), 0, telePot.getMaxVoltage(), 0, 3600);
    }
}
