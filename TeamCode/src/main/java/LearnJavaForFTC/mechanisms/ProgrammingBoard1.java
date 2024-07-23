package LearnJavaForFTC.mechanisms;

import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class ProgrammingBoard1 {
    private TouchSensor touchSensor;

    public void init(HardwareMap hwMap){
        touchSensor = hwMap.get(TouchSensor.class, "touch_sensor");
    }

    public String getTouchSensoreState() {
        if (touchSensor.isPressed()){
            return "Pressed";
        }
        else{
            return "Not Pressed";
        }
    }

    public boolean isTouchSensorReleased() {
        return !touchSensor.isPressed();
    }
}

