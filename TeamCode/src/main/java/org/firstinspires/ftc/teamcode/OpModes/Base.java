package org.firstinspires.ftc.teamcode.OpModes;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.mechanisms.Mechanisms;

@Config
@TeleOp(name = "Base", group="Alpha")

public class Base extends OpMode {
    public ElapsedTime elapsedTime;
    public ElapsedTime vibrateTime;
    public int wTime = 115;
    public static int target_angle = 5;
    public static int target_telescope = 0;

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
        //Drive Gear
        if (gamepad1.a) {
            mechs.setGear(0.33);
        }
        else if (gamepad1.x) {
            mechs.setGear(0.66);
        }
        else if (gamepad1.y) {
            mechs.setGear(1.0);
        }

        //Reset Yaw
        if (gamepad1.start){
            mechs.resetYaw();
        }
        if (gamepad1.share) {
            mechs.resetYaw();
        }

        //Intake On/Off
        if (gamepad2.right_bumper){
            mechs.intake_on();
        }
        else if(gamepad2.left_bumper){
            mechs.intake_out();
        }
        else{
            mechs.intake_off();
        }

        //Wrist Position
        if (gamepad2.right_stick_y<0){
            mechs.wrist(0.05);
        }
        if (gamepad2.right_stick_y>0){
            mechs.wrist(-0.05);
        }

        //Manual Telescope Control
        if (gamepad2.right_trigger>0 && gamepad1.circle){
            target_telescope = target_telescope + 50;}
        else if (gamepad2.left_trigger>0 && gamepad1.circle){
           target_telescope = target_telescope - 50;}

        //Manual Angle Control
        if (gamepad2.left_stick_y<0 /*&& gamepad1.circle*/){
            target_angle = target_angle + 1;}
        else if (gamepad2.left_stick_y >0 /*&& gamepad1.circle*/){
            target_angle = target_angle - 1;}

//        if (gamepad2.right_bumper) mechs.intake_out();
//        else if (gamepad2.left_bumper)mechs.intake_on();
//        else mechs.intake_off();
        //Stow
        if (gamepad2.x){
            target_angle = 0;
            target_telescope = 0;
            mechs.wrist_score();
        }
        //Long Reach
        else if (gamepad2.b){
            target_angle = 0;
            target_telescope = 4494;
            mechs.wrist_intake();
        }
        //Short Reach
        else if (gamepad2.a){
            target_angle = 0;
            target_telescope = 0;
            mechs.wrist_intake();
        }
        //High Basket
        else if (gamepad2.y){
            target_angle = 89;
            target_telescope = 4200;
            mechs.wrist_score();
        }
        //High Chamber
        else if (gamepad2.dpad_up){
            target_angle = 45;
            target_telescope = 3500;
            mechs.wrist_chamber();
        }
        //Low Chamber
        else if (gamepad2.dpad_down){
            target_angle = 30;
            target_telescope = 1000;
            mechs.wrist_score();
        }
        else if (gamepad2.dpad_right){
            target_angle = 70;
            target_telescope = 1200;
            mechs.wrist_score();
        }

        //mechs.arm_move(gamepad2.left_stick_y);
        if (target_angle > 89) target_angle = 89;
        else if (target_angle < 0) target_angle = 0;
        mechs.set_arm(target_angle);

        if (target_telescope > 4200) target_telescope = 4200;
        else if (target_telescope < 0) target_telescope = 0;

        mechs.set_telescope(target_telescope);

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
        telemetry.addData("arm target", target_angle);
        telemetry.addData("arm power", mechs.get_arm_power(target_angle));
        telemetry.addData("Telescope Target", target_telescope);
        telemetry.addData("Telescope in Ticks:", mechs.getTelescopeTicks());


    }

}
