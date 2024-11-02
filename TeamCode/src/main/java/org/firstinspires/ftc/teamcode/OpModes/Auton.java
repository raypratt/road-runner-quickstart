package org.firstinspires.ftc.teamcode.OpModes;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantFunction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.drivetrain.MecanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.Mechanisms;


@Autonomous (name="Auton", group="Alpha")
public class Auton extends LinearOpMode {

    Mechanisms mechs = new Mechanisms();
    String color;


    @Override
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(0, -62.5, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        mechs.init(hardwareMap);

        // vision here that outputs position
        int visionOutputPosition = 1;

        TrajectoryActionBuilder driveToBars = drive.actionBuilder(initialPose)
                .strafeTo(new Vector2d( 0,-48));
        TrajectoryActionBuilder firstSample = drive.actionBuilder(new Pose2d( 0,-48, Math.toRadians(90)))
                .strafeToLinearHeading(new Vector2d(-50,-48),Math.toRadians(90));
        TrajectoryActionBuilder scoringPosition = drive.actionBuilder(new Pose2d( -50,-48, Math.toRadians(90)))
                .strafeToLinearHeading(new Vector2d(-60,-60),Math.toRadians(-135));
        TrajectoryActionBuilder park = drive.actionBuilder(new Pose2d(-53,-51,Math.toRadians(45)))
                .strafeTo(new Vector2d( 60,-60));


        while (!isStopRequested() && !opModeIsActive()) {
            telemetry.addLine("Press X for Blue, 0 for Red");
            if (gamepad2.cross){
                telemetry.addLine("Blue Selected");
                color = "blue";
            }
            if (gamepad2.circle){
                telemetry.addLine("Red Selected");
                color = "red";
            }
            int position = visionOutputPosition;
            telemetry.addData("Position during Init", position);
            telemetry.update();
            mechs.set_arm(39);
            mechs.set_telescope(0);
        }

//        int startPosition = visionOutputPosition;
//        telemetry.addData("Starting Position", startPosition);
//        telemetry.update();
        waitForStart();
//
//        if (isStopRequested()) return;
//
//        Action trajectoryActionChosen;
//        if (startPosition == 1) {
//            trajectoryActionChosen = tab1.build();
//        } else if (startPosition == 2) {
//            trajectoryActionChosen = tab2.build();
//        } else {
//            trajectoryActionChosen = tab3.build();
//        }

        MoveTelescope telescopeAction = new MoveTelescope(0);
        MoveArm armAction = new MoveArm(45);
        Actions.runBlocking(
                new ParallelAction(
                    telescopeAction,
                    armAction,
                    new SequentialAction(
                            new WristScore(),
                            driveToBars.build(),
                            new UpdateArm(armAction,45),
                            new SleepAction(1),
                            new UpdateTelescope(telescopeAction, 3500),
                            new SleepAction(1),
                            new UpdateArm(armAction,25),
                            new SleepAction(1),
                            new IntakeOut(),
                            new SleepAction(1),
                            new IntakeOff(),
                            new UpdateTelescope(telescopeAction, 300),
                            new SleepAction(1),
                            new WristIntake(),
                            firstSample.build(),
                            new UpdateArm(armAction,0),
                            new IntakeOut(),
                            new SleepAction(1),
                            new UpdateTelescope(telescopeAction, 4200),
                            new SleepAction(0.9),
                            new IntakeOff(),
                            new UpdateTelescope(telescopeAction, 300),
                            new SleepAction(2),
                            new UpdateArm(armAction,77),
                            new SleepAction(2),
                            new WristBasket(),
                            new SleepAction(1),
                            new UpdateTelescope(telescopeAction,4200),
                            scoringPosition.build(),
                            new SleepAction(2),
                            new IntakeOut()
                    )
                )
        );
    }
    public class WristBasket implements Action {
        // checks if the lift motor has been powered on

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            mechs.wrist_basket();
            return false;
        }
    }
    public class WristScore implements Action {
        // checks if the lift motor has been powered on

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            mechs.wrist_score();
            return false;
        }
    }
    public class WristIntake implements Action {
        // checks if the lift motor has been powered on

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            mechs.wrist_intake();
            return false;
        }
    }
    public class IntakeOut implements Action {
        // checks if the lift motor has been powered on

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            mechs.intake_out();
            return false;
        }
    }
    public class IntakeIn implements Action {
        // checks if the lift motor has been powered on

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            mechs.intake_in();
            return false;
        }
    }

    public class IntakeOff implements Action {
        // checks if the lift motor has been powered on

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            mechs.intake_off();
            return false;
        }
    }

    public class MoveArm implements Action {
        private double angle;
        public MoveArm(double angle) {
            this.angle = angle;
        }
        public void updateAngle(double angle){
            this.angle = angle;


        }
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            mechs.set_arm(angle);
            telemetry.addData("Target Angle", angle);
            return true;
        }
    }

    public class UpdateArm implements Action{
        private MoveArm armAction;
        private double angle;
        public UpdateArm(MoveArm armAction, double angle) {
            this.armAction = armAction;
            this.angle = angle;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            armAction.updateAngle(angle);
            return false;
        }
    }

    public class MoveTelescope implements Action{
        public double position;
        public MoveTelescope(double position) {
            this.position = position;

        }
        public void updatePos(double position) {
            this.position = position;
        }
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            mechs.set_telescope(position);
            telemetry.addData("Target Position", position);
            return true;
        }
    }
    public class UpdateTelescope implements Action {
        private double position;
        MoveTelescope telescopeAction;
        public UpdateTelescope(MoveTelescope telescopeAction, double position){
            this.position = position;
            this.telescopeAction = telescopeAction;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            telescopeAction.updatePos(position);
            return false;
        }
    }

}