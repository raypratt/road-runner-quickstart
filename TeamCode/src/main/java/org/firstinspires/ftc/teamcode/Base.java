package org.firstinspires.ftc.teamcode;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.internal.camera.delegating.DelegatingCaptureSequence;

import java.util.Optional;

@Config
@TeleOp(name = "Base", group="Alpha")

public class Base extends OpMode {
    public ElapsedTime elapsedTime;
    public ElapsedTime vibrateTime;
    public int wTime = 115;
    public int target = 0;

    //Initialize
    Mechanisms mechs = new Mechanisms();

    @Override
     public void init(){
        elapsedTime = new ElapsedTime();
        vibrateTime = new ElapsedTime();

        elapsedTime.startTime();
        vibrateTime.startTime();

        mechs.init(hardwareMap);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

    }

    //@Override
    //public void start(){
    //    elapsedTime.reset();
    //    vibrateTime.reset();
    //}
    @Override
    public void loop(){
        if (gamepad1.a) {
            mechs.setGear(0.33);
        }
        else if (gamepad1.x) {
            mechs.setGear(0.66);
        }
        else if (gamepad1.y) {
            mechs.setGear(1.0);
        }
        if (gamepad1.start){
            mechs.resetYaw();
        }
        if (gamepad1.right_trigger>0){
            mechs.intake_on();
        }
        else if(gamepad1.left_trigger>0){
            mechs.intake_out();
        }
        else{
            mechs.intake_off();
        }
        if (gamepad1.dpad_down){
            mechs.wrist_intake();
        }
        else if (gamepad1.dpad_right){
            mechs.wrist_stow();
        }
        else if (gamepad1.dpad_up){
            mechs.wrist_score();
        }
        if (gamepad1.share) {
            mechs.resetYaw();
        }

        if (gamepad2.right_trigger>0){
            mechs.arm_out();}
        else if (gamepad2.left_trigger>0){
            mechs.arm_in();}
        else{
            mechs.arm_off();
        }

        if (gamepad2.x){
            target = 0;
            //telescope = 0;
            mechs.wrist_stow();
        }
        else if (gamepad2.b){
            target = 0;
            //telescope = 100;
            mechs.wrist_intake();
        }
        else if (gamepad2.a){
            target = 0;
            //telescope = 0;
            mechs.wrist_intake();
        }
        else if (gamepad2.y){
            target = 92;
            //telescope = 100;
            mechs.wrist_score();
        }



        //mechs.arm_move(gamepad2.left_stick_y);

        mechs.set_arm(target);

        mechs.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
        //mechs.lights(gamepad1, gamepad2, elapsedTime, wTime, vibrateTime);

        telemetry.addData("Gear", mechs.getGear());
        telemetry.addData("RF Speed", mechs.getRFSpeed());
        telemetry.addData("LF Speed", mechs.getLFSpeed());
        telemetry.addData("RB Speed", mechs.getRBSpeed());
        telemetry.addData("LB Speed", mechs.getLBSpeed());
        telemetry.addData("leftstick x", gamepad1.left_stick_x);
        telemetry.addData("leftstick y", gamepad1.left_stick_y);
        telemetry.addData("rightstickx", gamepad1.right_stick_x);
        telemetry.addData("botHeading", mechs.getBotHeading());
        telemetry.addData("arm angle degrees", mechs.get_arm_pos_degrees());
        telemetry.addData("arm angle ticks", mechs.get_arm_pos_ticks());
        telemetry.addData("arm target", target);


    }

}
