package org.firstinspires.ftc.teamcode.OpModes;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.logging.data;

import org.firstinspires.ftc.teamcode.logging.Datalogger;
import org.firstinspires.ftc.teamcode.logging.log;
import org.firstinspires.ftc.teamcode.mechanisms.Mechanisms;

@Config
@TeleOp(name = "Base", group="Alpha")

public class Base extends OpMode {
    public ElapsedTime elapsedTime;
    public ElapsedTime vibrateTime;
    public int wTime = 115;
    public static int target_angle = 5;
    public static int target_telescope = 0;
    data.Datalog datalog;
    public int i;
    //Initialize
    Mechanisms mechs = new Mechanisms();
    String color;
    @Override
     public void init(){
        elapsedTime = new ElapsedTime();
        vibrateTime = new ElapsedTime();

        elapsedTime.startTime();
        vibrateTime.startTime();

        mechs.init(hardwareMap,0.774, 2.755);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        datalog = new data.Datalog("JPEC_datalog");
        datalog.opModeStatus.set("INIT");
        datalog.battery.set(mechs.get_battery());
        datalog.writeLine();
        i = 0;
        mechs.setWinch_servo(0.7);

    }


    @Override
    public void init_loop() {
        telemetry.addLine("Press Cross or A for Gold, Circle or B for Maroon");
        if (gamepad2.cross){
            telemetry.addLine("Gold Selected");
            color = "gold";
            mechs.startVoltage = 0.44;
            mechs.endVoltage = 2.233;
        }
        if (gamepad2.circle){
            telemetry.addLine("Maroon Selected");
            color = "maroon";
        }
    }
    //public void start(){
    //    elapsedTime.reset();
    //    vibrateTime.reset();
    //}

    @Override
    public void loop(){
        i++;
        driverControls();
        manualControls();
        buttonSetpoints();

        //Safety settings for arm and telescope
        if (target_angle > 89) target_angle = 89;
        else if (target_angle < 0) target_angle = 0;
        mechs.set_arm(target_angle);

        if (target_telescope > 4200) target_telescope = 4200;
        else if (target_telescope < 0) target_telescope = 0;

        mechs.set_telescope(target_telescope);

        mechs.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
        //mechs.lights(gamepad1, gamepad2, elapsedTime, wTime, vibrateTime);

        telemetryData();
        loggingTeleOp();

    }
    private void driverControls() {
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
    }
    private void manualControls() {

        //Intake On/Off
        if (gamepad2.right_bumper){
            mechs.intake_out();
        }
        else if(gamepad2.left_bumper){
            mechs.intake_in();
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

    }
    private void buttonSetpoints() {
        //Stow
        if (gamepad2.x){
            target_angle = 0;
            target_telescope = 0;
            mechs.wrist_score();
        }
        //Long Reach
        else if (gamepad2.b){
            target_angle = 0;
            target_telescope = 2800;
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
            target_angle = 77;
            target_telescope = 4200;
            mechs.wrist_basket();
        }
        //High Chamber
        else if (gamepad2.dpad_up){
            target_angle = 35;
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
    }
    private void telemetryData(){
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
        telemetry.addData("Wrist Position", mechs.get_wrist_position());
        telemetry.addData("getPotentiometer",mechs.getPotentiometer());
        telemetry.addData("Get Telescope Power", mechs.getTelescopePower());
    }
    private void loggingTeleOp(){
        datalog.loopCounter.set(i);
        datalog.battery.set(mechs.get_battery());
        datalog.opModeStatus.set("Running");
        datalog.yaw.set(mechs.getBotHeading());
        datalog.armAngle.set(mechs.get_arm_pos_degrees());
        datalog.telescope.set(mechs.getTelescopeTicks());
        datalog.wristPos.set(mechs.get_wrist_position());
        datalog.leftFront.set(mechs.getLFSpeed());
        datalog.rightFront.set(mechs.getRFSpeed());
        datalog.leftBack.set(mechs.getLBSpeed());
        datalog.rightBack.set(mechs.getRBSpeed());
        datalog.intake.set(mechs.get_intake_status());
        datalog.gear.set(mechs.getGear());
        datalog.gp1Lx.set(gamepad1.left_stick_x);
        datalog.gp1Ly.set(gamepad1.left_stick_y);
        datalog.gp1Rx.set(gamepad1.right_stick_x);
        datalog.gp2Lx.set(gamepad2.left_stick_x);
        datalog.gp2Ly.set(gamepad2.left_stick_y);
        datalog.gp2Rx.set(gamepad2.right_stick_x);
        datalog.gp2Ry.set(gamepad2.right_stick_y);
        datalog.gp2DU.set(gamepad2.dpad_up);
        datalog.gp2DD.set(gamepad2.dpad_down);
        datalog.gp2DR.set(gamepad2.dpad_right);
        datalog.gp2DL.set(gamepad2.dpad_left);
        datalog.gp2Cross.set(gamepad2.cross);
        datalog.gp2Square.set(gamepad2.square);
        datalog.gp2Circle.set(gamepad2.circle);
        datalog.gp2Triangle.set(gamepad2.triangle);
        datalog.gp2LT.set(gamepad2.left_trigger);
        datalog.gp2RT.set(gamepad2.right_trigger);
        datalog.gp2LB.set(gamepad2.left_bumper);
        datalog.gp2RB.set(gamepad2.right_bumper);

        datalog.writeLine();
    }

    @Override
    public void stop () {
        mechs.setWinch_servo(1);
    }


}