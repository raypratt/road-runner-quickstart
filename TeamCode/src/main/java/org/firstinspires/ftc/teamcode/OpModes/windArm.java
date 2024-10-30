package org.firstinspires.ftc.teamcode.OpModes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.mechanisms.Mechanisms;

@TeleOp (name="Wind Arm", group = "Alpha")
public class windArm extends OpMode {

  Mechanisms mechs = new Mechanisms();

  @Override
  public void init() {
    mechs.init(hardwareMap);
  }

  public void loop() {
    if (gamepad1.left_stick_y != 0.0) {
      mechs.set_telescope_power(-gamepad1.left_stick_y);
    } else {
      mechs.set_telescope_power(0);
    }
  }
}
