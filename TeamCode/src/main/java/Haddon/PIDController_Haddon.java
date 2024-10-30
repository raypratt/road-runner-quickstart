package Haddon;

//I didn't make any of this. It was AI generated on Android Studio
public class PIDController_Haddon {
    private double Kp, Ki, Kd;
    private double setpoint;
    private double integral, previousError;

    public PIDController_Haddon(double Kp, double Ki, double Kd) {
        this.Kp = Kp;
        this.Ki = Ki;
        this.Kd = Kd;
        this.integral = 0;
        this.previousError = 0;
    }

    public void setSetpoint(double setpoint) {
        this.setpoint = setpoint;
    }

    public double calculate(double measuredValue) {
        double error = setpoint - measuredValue;
        integral += error;
        double derivative = error - previousError;
        previousError = error;
        return Kp * error + Ki * integral + Kd * derivative;
    }
}