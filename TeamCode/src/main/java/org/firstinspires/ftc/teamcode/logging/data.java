package org.firstinspires.ftc.teamcode.logging;

import android.provider.ContactsContract;

public class data {
    public static class Datalog
    {
        // The underlying datalogger object - it cares only about an array of loggable fields
        private final Datalogger datalogger;

        // These are all of the fields that we want in the datalog.
        // Note that order here is NOT important. The order is important in the setFields() call below
        public Datalogger.GenericField opModeStatus = new Datalogger.GenericField("OpModeStatus");
        public Datalogger.GenericField loopCounter  = new Datalogger.GenericField("Loop Counter");
        public Datalogger.GenericField yaw          = new Datalogger.GenericField("Yaw");
        public Datalogger.GenericField battery      = new Datalogger.GenericField("Battery");
        public Datalogger.GenericField armAngle     = new Datalogger.GenericField("Arm Angle");
        public Datalogger.GenericField telescope    = new Datalogger.GenericField("Telescope");
        public Datalogger.GenericField wristPos     = new Datalogger.GenericField("Wrist Position");
        public Datalogger.GenericField leftFront    = new Datalogger.GenericField("Left Front Motor");
        public Datalogger.GenericField rightFront   = new Datalogger.GenericField("Right Front Motor");
        public Datalogger.GenericField leftBack     = new Datalogger.GenericField("Left Back Motor");
        public Datalogger.GenericField rightBack    = new Datalogger.GenericField("Right Back Motor");
        public Datalogger.GenericField intake       = new Datalogger.GenericField("Intake");
        public Datalogger.GenericField gear         = new Datalogger.GenericField("Gear");
        public Datalogger.GenericField gp1Lx        = new Datalogger.GenericField("GamePad 1 Left Stick X");
        public Datalogger.GenericField gp1Ly        = new Datalogger.GenericField("GamePad 1 Left Stick y");
        public Datalogger.GenericField gp1Rx        = new Datalogger.GenericField("GamePad 1 Right Stick x");
        public Datalogger.GenericField gp2Lx        = new Datalogger.GenericField("GamePad 2 Left Stick X");
        public Datalogger.GenericField gp2Ly        = new Datalogger.GenericField("GamePad 2 Left Stick y");
        public Datalogger.GenericField gp2Rx        = new Datalogger.GenericField("GamePad 2 Right Stick x");
        public Datalogger.GenericField gp2Ry        = new Datalogger.GenericField("GamePad 2 Left Stick y");
        public Datalogger.GenericField gp2DU        = new Datalogger.GenericField("GamePad 2 D-Pad Up");
        public Datalogger.GenericField gp2DD        = new Datalogger.GenericField("GamePad 2 D-Pad Down");
        public Datalogger.GenericField gp2DR        = new Datalogger.GenericField("GamePad 2 D-Pad Right");
        public Datalogger.GenericField gp2DL        = new Datalogger.GenericField("GamePad 2 D-Pad Left");
        public Datalogger.GenericField gp2Cross     = new Datalogger.GenericField("GamePad 2 Cross");
        public Datalogger.GenericField gp2Square    = new Datalogger.GenericField("GamePad 2 Square");
        public Datalogger.GenericField gp2Circle    = new Datalogger.GenericField("GamePad 2 Circle");
        public Datalogger.GenericField gp2Triangle  = new Datalogger.GenericField("GamePad 2 Triangle");
        public Datalogger.GenericField gp2LT        = new Datalogger.GenericField("GamePad 2 Left Trigger");
        public Datalogger.GenericField gp2RT        = new Datalogger.GenericField("GamePad 2 Right Trigger");
        public Datalogger.GenericField gp2LB        = new Datalogger.GenericField("GamePad 2 Left Button");
        public Datalogger.GenericField gp2RB        = new Datalogger.GenericField("GamePad 2 Right Button");

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
                            battery,
                            armAngle,
                            telescope,
                            wristPos,
                            leftFront,
                            rightFront,
                            leftBack,
                            rightBack,
                            intake,
                            gear,
                            gp1Lx,
                            gp1Ly,
                            gp1Rx,
                            gp2Lx,
                            gp2Ly,
                            gp2Rx,
                            gp2Ry,
                            gp2DU,
                            gp2DD,
                            gp2DR,
                            gp2DL,
                            gp2Cross,
                            gp2Square,
                            gp2Circle,
                            gp2Triangle,
                            gp2LT,
                            gp2RT,
                            gp2LB,
                            gp2RB
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
