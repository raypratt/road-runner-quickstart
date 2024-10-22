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

            Pose2d beginPose = new Pose2d(-38.625, -62.5, Math.toRadians(-90));
        if (TuningOpModes.DRIVE_CLASS.equals(MecanumDrive.class)) {
            MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

            waitForStart();
            Actions.runBlocking(
                    drive.actionBuilder(beginPose)
                            .strafeTo(new Vector2d( -49.5,-38))
                            .turnTo(Math.toRadians(45))
                            .strafeTo(new Vector2d(-49,-53))
                            .turnTo(Math.toRadians(-90))
                            .strafeTo(new Vector2d(-59, -38))
                            .turnTo(Math.toRadians(45))
                            .strafeTo(new Vector2d(-49,-53))
                            .build());
        } else {
            throw new RuntimeException();

        }
    }
}
