package org.firstinspires.ftc.teamcode.tuning;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.MecanumDrive;
//import org.firstinspires.ftc.teamcode.TankDrive;

@TeleOp(name="Target TEST", group="Linear OpMode")

public final class targetTest extends LinearOpMode {


    @Override

    public void runOpMode() throws InterruptedException {


        Pose2d beginPose = new Pose2d(-38.625, -62.5, Math.toDegrees(90));
        if (TuningOpModes.DRIVE_CLASS.equals(MecanumDrive.class)) {
            MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

            waitForStart();
            Actions.runBlocking(
                    drive.actionBuilder(beginPose)
                            .strafeTo(new Vector2d(-45.0, 7.0625))
                         //   .strafeTo(new Vector2d(-55, 0))
                          //  .turn(Math.toRadians(90))
                           // .strafeTo(new Vector2d(-58, -55.5))
                            .build());
        } else {
            throw new RuntimeException();

        }
    }
}
