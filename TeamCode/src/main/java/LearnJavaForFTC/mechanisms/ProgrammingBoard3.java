package LearnJavaForFTC.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ProgrammingBoard3 {
    private TouchSensor touchSensor;
    private DcMotor motor;

    public void init(HardwareMap hwMap){
        touchSensor = hwMap.get(TouchSensor.class, "touch_sensor");
        motor = hwMap.get(DcMotor.class, "motor");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public boolean isTouchSensorPressed() {
        return !touchSensor.isPressed();
    }

    public void setMotorSpeed(double speed){
        motor.setPower(speed);
    }
}
