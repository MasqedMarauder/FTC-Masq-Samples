package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * This is a demo teleop program that shows how to use the MasqAdafruitIMU.
 * Created by Varun Singh, Lead Programmer of Team 4997 Masquerade.
 */

@TeleOp(name = "MasqAdafruitIMUDemo", group = "Demo")
public class MasqAdafruitIMUDemo extends LinearOpMode {

    public void runOpMode() throws InterruptedException {
        // in this demo, the IMU is named "IMU" in the robot configuration file
        MasqAdafruitIMU imu = new MasqAdafruitIMU("IMU", hardwareMap);

        // wait to see this on the Driver Station before pressing play, to make sure the IMU has been initialized
        while (!isStarted()) {
            telemetry.addData("Status", "Initialization Complete");
            telemetry.update();
        }
        
        waitForStart();
        telemetry.clear();

        while (opModeIsActive()) {
            // the next 4 lines show how to retrieve angles from the imu and use them
            double[] angles = imu.getAngles();
            double yaw = angles[0];
            double pitch = angles[1];
            double roll = angles[2];
            
            // this adds telemetry data using the telemetrize() method in the MasqAdafruitIMU class
            telemetry.addData(imu.getName(), imu.telemetrize());
            telemetry.update();
        }
    }
    
}
