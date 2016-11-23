package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * This is a demo teleop program that shows how to use the MasqMRColorSensor.
 * Created by Varun Singh, Lead Programmer of Team 4997 Masquerade.
 */

@TeleOp(name = "MasqMRColorSensorDemo", group = "Demo")
public class MasqMRColorSensorDemo extends LinearOpMode {

    public void runOpMode() throws InterruptedException {
        // in this demo, the color sensor is named "colorSensor" in the robot configuration file
        // the I2C address is set to 60 (0x3C), which is the default I2C address for MR Color Sensors
        MasqMRColorSensor colorSensor = new MasqMRColorSensor("colorSensor", 60, hardwareMap);
        colorSensor.setActiveMode(); // the LED will be on

        // wait to see this on the Driver Station before pressing play, to make sure the color sensor has been initialized
        while (!isStarted()) {
            telemetry.addData("Status", "Initialization Complete");
            telemetry.update();
        }
        
        waitForStart();
        telemetry.clear();

        while (opModeIsActive()) {
            // the next few lines show how to retrieve data from the color sensor and use them
            int colorNumber = colorSensor.colorNumber();
            
            double red = colorSensor.red();
            double green = colorSensor.green();
            double blue = colorSensor.blue();
            double alpha = colorSensor.alpha();
            
            double hue = colorSensor.hue();
            double saturation = colorSensor.saturation();
            double value = colorSensor.value();
            
            colorSensor.setBlueThresholds(1, 4); // if the color number is between 1 and 4 inclusive, then the color sensor will say that it is detecting blue
            colorSensor.setRedThresholds(10, 12); // if the color number is between 10 and 12 inclusive, then the color sensor will say that it is detecting red
            colorSensor.setWhiteThresholds(14, 16); // if the color number is between 14 and 16 inclusive, then the color sensor will say that it is detecting white
            
            boolean isBlue = colorSensor.detectBlue();
            boolean isRed = colorSensor.detectRed();
            boolean isWhite = colorSensor.detectWhite();
            
            // this adds telemetry data using the telemetrize() method in the MasqMRColorSensor class
            telemetry.addData(colorSensor.getName(), colorSensor.telemetrize());
            telemetry.update();
        }
    }
    
}
