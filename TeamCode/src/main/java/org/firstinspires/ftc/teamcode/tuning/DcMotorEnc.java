package org.firstinspires.ftc.teamcode.tuning;

import com.qualcomm.hardware.digitalchickenlabs.OctoQuad;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

public class DcMotorEnc implements DcMotorEx {
    private OctoQuad octoquad;
    private int encIndex;
    DcMotorController controller;
    private Direction dir;

    public DcMotorEnc(OctoQuad octquad, int encIndex) {
        this.octoquad = octquad;
        this.encIndex = encIndex;
    }

    @Override public void setVelocity(double angularRate) {}
    @Override public void setVelocity(double angularRate, AngleUnit unit) {}
    @Override public double getVelocity(AngleUnit unit) { return 0; }

    @Override public int getCurrentPosition() { return octoquad.readSinglePosition(this.encIndex); }
    @Override public double getVelocity() { return octoquad.readSingleVelocity(this.encIndex); }

    @Override public void setDirection(Direction direction) { this.dir = direction; }
    @Override public Direction getDirection() { return this.dir; }

    // Unused Methods Below
    @Override public void setMotorEnable() {}
    @Override public void setMotorDisable() {}
    @Override public boolean isMotorEnabled() { return false; }
    @Override public void setPIDCoefficients(RunMode mode, PIDCoefficients pidCoefficients) {}
    @Override public void setPIDFCoefficients(RunMode mode, PIDFCoefficients pidfCoefficients) throws UnsupportedOperationException {}
    @Override public void setVelocityPIDFCoefficients(double p, double i, double d, double f) {}
    @Override public void setPositionPIDFCoefficients(double p) {}
    @Override public PIDCoefficients getPIDCoefficients(RunMode mode) { return null; }
    @Override public PIDFCoefficients getPIDFCoefficients(RunMode mode) { return null; }
    @Override public void setTargetPositionTolerance(int tolerance) {}
    @Override public int getTargetPositionTolerance() { return 0; }
    @Override public double getCurrent(CurrentUnit unit) { return 0; }
    @Override public double getCurrentAlert(CurrentUnit unit) { return 0; }
    @Override public void setCurrentAlert(double current, CurrentUnit unit) {}
    @Override public boolean isOverCurrent() { return false; }
    @Override public MotorConfigurationType getMotorType() { return null; }
    @Override public void setMotorType(MotorConfigurationType motorType) {}
    @Override public DcMotorController getController() { return null; }
    @Override public int getPortNumber() { return 0; }
    @Override public void setZeroPowerBehavior(ZeroPowerBehavior zeroPowerBehavior) {}
    @Override public ZeroPowerBehavior getZeroPowerBehavior() { return null; }
    @Override public void setPowerFloat() {}
    @Override public boolean getPowerFloat() { return false; }
    @Override public void setTargetPosition(int position) {}
    @Override public int getTargetPosition() { return 0; }
    @Override public boolean isBusy() { return false; }
    @Override public void setMode(RunMode mode) {}
    @Override public RunMode getMode() { return null; }
    @Override public void setPower(double power) {}
    @Override public double getPower() { return 0; }
    @Override public Manufacturer getManufacturer() { return null; }
    @Override public String getDeviceName() { return ""; }
    @Override public String getConnectionInfo() { return ""; }
    @Override public int getVersion() { return 0; }
    @Override public void resetDeviceConfigurationForOpMode() {}
    @Override public void close() {}
}
