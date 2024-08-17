package LearnJavaForFTC.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ProgrammingBoard4 {
    private TouchSensor touchSensor;
    private DcMotor motor;
    private double ticksPerRotation;

    private double gearing;
    private String motorType;

    public void init(HardwareMap hwMap){
        touchSensor = hwMap.get(TouchSensor.class, "touch_sensor");
        motor = hwMap.get(DcMotor.class, "motor");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ticksPerRotation = motor.getMotorType().getTicksPerRev()/20;
        motorType = String.valueOf(motor.getMotorType());
        motor.getMotorType().setGearing(1);
        gearing = motor.getMotorType().getGearing();
    }

    public boolean isTouchSensorPressed(){
        return touchSensor.isPressed();
    }

    public void setMotorSpeed(double speed){
        motor.setPower(speed);
    }

    public double getMotorRotations(){
        return motor.getCurrentPosition() / ticksPerRotation;
    }

    public double getCurrentPosition(){
        return motor.getCurrentPosition();
    }

    public double getTicksPerRotation(){
        return ticksPerRotation;
    }

    public double getGearing(){
        return motor.getMotorType().getGearing();
    }

    public String getMotortype() {return String.valueOf(motor.getMotorType());}

}


