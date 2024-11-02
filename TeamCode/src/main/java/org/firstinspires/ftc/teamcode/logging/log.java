package org.firstinspires.ftc.teamcode.logging;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.internal.files.DataLogger;
import org.firstinspires.ftc.teamcode.mechanisms.Mechanisms;

@TeleOp
public class log extends OpMode {

    VoltageSensor battery;
    Datalog datalog;
    IMU imu;
    int i = 0;

    @Override
    public void init() {
        Mechanisms mechs = new Mechanisms();
        battery = hardwareMap.voltageSensor.get("Control Hub");
        // Initialize the datalog
        datalog = new Datalog("JPEC_datalog_01");

        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.DOWN, RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        imu.initialize(parameters);

        // You do not need to fill every field of the datalog
        // every time you call writeLine(); those fields will simply
        // contain the last value.
        datalog.opModeStatus.set("INIT");
        datalog.battery.set(battery.getVoltage());
        datalog.writeLine();
    }

    public void loop(){
        datalog.opModeStatus.set("RUNNING");

        {
            // Note that the order in which we set datalog fields
            // does *not* matter! The order is configured inside
            // the Datalog class constructor.
            i++;
            datalog.loopCounter.set(i);
            datalog.battery.set(battery.getVoltage());



            datalog.yaw.set(imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
            datalog.pitch.set(imu.getRobotYawPitchRollAngles().getPitch(AngleUnit.DEGREES));
            datalog.roll.set(imu.getRobotYawPitchRollAngles().getRoll(AngleUnit.DEGREES));

            // The logged timestamp is taken when writeLine() is called.
            datalog.writeLine();

            // Datalog fields are stored as text only; do not format here.
            telemetry.addData("Yaw", datalog.yaw);
            telemetry.addData("Pitch", datalog.pitch);
            telemetry.addData("Roll", datalog.roll);
            telemetry.addLine();
            telemetry.addData("OpMode Status", datalog.opModeStatus);
            telemetry.addData("Loop Counter", datalog.loopCounter);
            telemetry.addData("Battery", datalog.battery);

            telemetry.update();
        }

    }


    public static class Datalog
    {
        // The underlying datalogger object - it cares only about an array of loggable fields
        private final Datalogger datalogger;

        // These are all of the fields that we want in the datalog.
        // Note that order here is NOT important. The order is important in the setFields() call below
        public Datalogger.GenericField opModeStatus = new Datalogger.GenericField("OpModeStatus");
        public Datalogger.GenericField loopCounter  = new Datalogger.GenericField("Loop Counter");
        public Datalogger.GenericField yaw          = new Datalogger.GenericField("Yaw");
        public Datalogger.GenericField pitch        = new Datalogger.GenericField("Pitch");
        public Datalogger.GenericField roll         = new Datalogger.GenericField("Roll");
        public Datalogger.GenericField battery      = new Datalogger.GenericField("Battery");

        public Datalog(String name)
        {
            // Build the underlying datalog object
            datalogger = new Datalogger.Builder()

                    // Pass through the filename
                    .setFilename(name)

                    // Request an automatic timestamp field
                    .setAutoTimestamp(Datalogger.AutoTimestamp.DECIMAL_SECONDS)

                    // Tell it about the fields we care to log.
                    // Note that order *IS* important here! The order in which we list
                    // the fields is the order in which they will appear in the log.
                    .setFields(
                            opModeStatus,
                            loopCounter,
                            yaw,
                            pitch,
                            roll,
                            battery
                    )
                    .build();
        }

        // Tell the datalogger to gather the values of the fields
        // and write a new line in the log.
        public void writeLine()
        {
            datalogger.writeLine();
        }
    }
}
