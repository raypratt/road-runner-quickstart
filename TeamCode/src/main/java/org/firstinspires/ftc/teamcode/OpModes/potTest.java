package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class potTest extends OpMode {
    private AnalogInput telePot;

    @Override
    public void init() {
        telePot = hardwareMap.get(AnalogInput.class, "telePot");
    }

    @Override
    public void loop() {
        telemetry.addData("TelePot Angle", getPotAngle());
    }

    public double getPotAngle(){
        return Range.scale(telePot.getVoltage(), 0, telePot.getMaxVoltage(), 0, 3600);
    }
}
