package org.firstinspires.ftc.teamcode.OpModes;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantFunction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drivetrain.MecanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.Mechanisms;


@Autonomous (name="Auton", group="Alpha")
public class Auton extends LinearOpMode {

    Mechanisms mechs = new Mechanisms();
    String color;


    @Override
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(0, -62.5, Math.toRadians(-90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        mechs.init(hardwareMap);

        // vision here that outputs position
        int visionOutputPosition = 1;

        TrajectoryActionBuilder driveToBars = drive.actionBuilder(initialPose)
                .strafeTo(new Vector2d( 0,-39));
        TrajectoryActionBuilder tab2 = drive.actionBuilder(initialPose)
                .lineToY(37)
                .setTangent(Math.toRadians(0))
                .lineToX(18)
                .waitSeconds(3)
                .setTangent(Math.toRadians(0))
                .lineToXSplineHeading(46, Math.toRadians(180))
                .waitSeconds(3);
        TrajectoryActionBuilder tab3 = drive.actionBuilder(initialPose)
                .lineToYSplineHeading(33, Math.toRadians(180))
                .waitSeconds(2)
                .strafeTo(new Vector2d(46, 30))
                .waitSeconds(3);

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
        MoveTelescope telescopeAction = new MoveTelescope(1);
        MoveArm armAction = new MoveArm(45);
        Actions.runBlocking(
                new ParallelAction(
                    telescopeAction,
                    armAction,
                    new SequentialAction(
                            driveToBars.build(),
                            new UpdateArm(armAction,70),
                            new UpdateTelescope(telescopeAction, 10)


                    )
                )
        );
    }

    public class IntakeOut implements InstantFunction {
        // checks if the lift motor has been powered on
        private boolean initialized = false;

        @Override
        public void run() {
            mechs.intake_out();
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
            return false;
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
            return false;
        }
    }
    public class UpdateTelescope implements Action{
        private double position;
        MoveTelescope telescopeAction;
        public UpdateTelescope(MoveTelescope telescopeAction, double position){
            this.position = position;
            this.telescopeAction = telescopeAction;
        }


        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            telescopeAction.updatePos(position);
            return true;
        }
    }

}