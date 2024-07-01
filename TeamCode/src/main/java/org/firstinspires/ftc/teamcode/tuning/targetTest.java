package org.firstinspires.ftc.teamcode.tuning;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.TankDrive;

@TeleOp(name="Target TEST", group="Linear OpMode")

public final class targetTest extends LinearOpMode {


    @Override

    public void runOpMode() throws InterruptedException {


        Pose2d beginPose = new Pose2d(-60, 60, 0);
        if (TuningOpModes.DRIVE_CLASS.equals(MecanumDrive.class)) {
            MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

            waitForStart();
            Actions.runBlocking(
                    drive.actionBuilder(beginPose)
                            .splineToLinearHeading(new Pose2d(33, 60, 0.0), 0.0)
                            .splineToLinearHeading(new Pose2d(-46, -35, -2.35), -2.35)
                            .splineToLinearHeading(new Pose2d(45, -55, 0.0), 0.0)
                            .splineToLinearHeading(new Pose2d(0, 0, 0.0), -2.35)
                            .build());
        } else {
            throw new RuntimeException();
        }
    }
}
