package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;

import org.firstinspires.ftc.robotcontroller.internal.FtcOpModeRegister;

import java.util.Locale;

/**
 * This is a Modern Robotics Color Sensor.
 * Provides the color number, as well as argb and hsv data.
 * Can detect red, white, and blue, and thresholds for those can be set.
 * Created by Varun Singh, Lead Programmer of Team 4997 Masquerade
 */

public class MasqMRColorSensor {

    private final I2cDevice colorSensor;
    private final I2cDeviceSynch colorSensorManager;
    private final String name;

    private float[] hsvValues = new float[3];

    private static final int
            COLOR_NUMBER_REGISTER = 0x04,
            RED_VALUE_REGISTER = 0x05,
            GREEN_VALUE_REGISTER = 0x06,
            BLUE_VALUE_REGISTER = 0x07,
            WHITE_VALUE_REGISTER = 0x08;

    private static final int
            READ_WINDOW_START = COLOR_NUMBER_REGISTER,
            READ_WINDOW_LENGTH = 5;

    private static final int
            COMMAND_REGISTER = 0x03,
            ACTIVE_MODE_COMMAND = 0x00,
            PASSIVE_MODE_COMMAND = 0x01;

    private int
            blueMinThreshold = 1, blueMaxThreshold = 4,
            redMinThreshold = 10, redMaxThreshold = 12,
            whiteMinThreshold = 14, whiteMaxThreshold = 16;


    public MasqMRColorSensor(String name, int i2cAddress) {
        this.name = name;

        colorSensor = FtcOpModeRegister.opModeManager.getHardwareMap().i2cDevice.get(name);
        colorSensor.resetDeviceConfigurationForOpMode();

        colorSensorManager = new I2cDeviceSynchImpl(colorSensor, I2cAddr.create8bit(i2cAddress), false);
        colorSensorManager.resetDeviceConfigurationForOpMode();
        colorSensorManager.engage();
        colorSensorManager.setReadWindow(new I2cDeviceSynch.ReadWindow(READ_WINDOW_START, READ_WINDOW_LENGTH, I2cDeviceSynch.ReadMode.REPEAT));
    }


    // active mode turns the LED on, passive mode turns it off
    // use active mode when detecting the color of something which does not emit its own light (such as the tape on the playing field)
    // use passive mode when detecting the color of something which emits its own light (such as the beacons)
    public void setActiveMode() {colorSensorManager.write8(COMMAND_REGISTER, ACTIVE_MODE_COMMAND);}
    public void setPassiveMode() {colorSensorManager.write8(COMMAND_REGISTER, PASSIVE_MODE_COMMAND);}

    // Color Number is a feature provided by the Modern Robotics Color Sensor
    // See http://modernroboticsinc.com/color-sensor for more details
    public int colorNumber() {return colorSensorManager.read8(COLOR_NUMBER_REGISTER);}

    // these return the argb values
    public int red() {return colorSensorManager.read8(RED_VALUE_REGISTER);}
    public int green() {return colorSensorManager.read8(GREEN_VALUE_REGISTER);}
    public int blue() {return colorSensorManager.read8(BLUE_VALUE_REGISTER);}
    public int alpha() {return colorSensorManager.read8(WHITE_VALUE_REGISTER);}

    private void rgb2hsv() {
        byte[] colorSensorCache = colorSensorManager.read(RED_VALUE_REGISTER, 3);
        Color.RGBToHSV(colorSensorCache[0], colorSensorCache[1], colorSensorCache[2], hsvValues);
    }

    // these return the hsv values
    public float hue() {rgb2hsv(); return hsvValues[0];}
    public float saturation() {rgb2hsv(); return hsvValues[1];}
    public float value() {rgb2hsv(); return hsvValues[2];}


    // this enables you to set the thresholds for the color number when detecting blue
    public void setBlueThresholds(int min, int max) {
        blueMinThreshold = min;
        blueMaxThreshold = max;
    }

    // this enables you to set the thresholds for the color number when detecting red
    public void setRedThresholds(int min, int max) {
        redMinThreshold = min;
        redMaxThreshold = max;
    }

    // this enables you to set the thresholds for the color number when detecting white
    public void setWhiteThresholds(int min, int max) {
        whiteMinThreshold = min;
        whiteMaxThreshold = max;
    }

    // this will return if the color sensor detects blue, based on the color number thresholds provided
    public boolean detectBlue() {
        int colorNumber = colorNumber();
        return colorNumber >= blueMinThreshold && colorNumber <= blueMaxThreshold;
    }

    // this will return if the color sensor detects red, based on the color number thresholds provided
    public boolean detectRed() {
        int colorNumber = colorNumber();
        return colorNumber >= redMinThreshold && colorNumber <= redMaxThreshold;
    }

    // this will return if the color sensor detects white, based on the color number thresholds provided
    public boolean detectWhite() {
        int colorNumber = colorNumber();
        return colorNumber >= whiteMinThreshold && colorNumber <= whiteMaxThreshold;
    }


    public String getName() {return name;}

    // this returns a string that can be used to output telemetry data easily in other classes
    public String telemetrize() {
        return String.format(Locale.US, "Color #: %d   ARGB:[%d,%d,%d,%d]  HSV:[%.3f,%.3f,%.3f]",
                colorNumber(), alpha(), red(), green(), blue(), hue(), saturation(), value());
    }


}
