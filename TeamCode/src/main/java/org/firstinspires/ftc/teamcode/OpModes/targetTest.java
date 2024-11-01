package org.firstinspires.ftc.teamcode.OpModes;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drivetrain.MecanumDrive;
import org.firstinspires.ftc.teamcode.tuning.TuningOpModes;
//import org.firstinspires.ftc.teamcode.drivetrain.TankDrive;

@TeleOp(name="Target TEST", group="Linear OpMode")

public final class targetTest extends LinearOpMode {


    @Override

    public void runOpMode() throws InterruptedException {

            Pose2d beginPose = new Pose2d(0, -62.5, Math.toRadians(-90));
        if (TuningOpModes.DRIVE_CLASS.equals(MecanumDrive.class)) {
            MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

            waitForStart();
            Actions.runBlocking(
                    drive.actionBuilder(beginPose)
                            .strafeTo(new Vector2d( 0,-39))
                            .strafeToLinearHeading(new Vector2d(-30,-45.5),Math.toRadians(-45))
                            .waitSeconds(1)
                            .strafeToLinearHeading(new Vector2d(-53,-51),Math.toRadians(45))
                            .waitSeconds(1)
                            .turnTo(Math.toRadians(-75))
                            .waitSeconds(1)
                            .turnTo(Math.toRadians(45))
                            .waitSeconds(1)
                            .turnTo(Math.toRadians(-55))
                            .waitSeconds(1)
                            .turnTo(Math.toRadians(45))
                            .build());
        } else {
            throw new RuntimeException();

        }
    }

}
