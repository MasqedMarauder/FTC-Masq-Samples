# FTC-Masq-Samples
Hello! Welcome to the FTC Samples Repository created by Varun Singh, Lead Programmer of Team 4997 Masquerade.

This contains two files: MasqAdafruitIMU and MasqAdafruitIMUDemo. You can just simply copy-and-paste these two files
into your teamcode package. If you have your code structured differently, make sure to modify the package declaration
at the top of each file.

In your robot configuration file, make sure to have the IMU named "IMU" (at least for the demo) and declared of type
BoschBNO055 in the I2C Ports section of the Device Interface Module.

MasqAdafruitIMU is the "driver" for the Adafruit BoschBNO055 Inertial Measurement Unit. It allows for easy and accurate
angle measurement retrieval using quaternion data, as opposed to Euler angles.

MasqAdafruitIMUDemo is a demonstration teleop program that just outputs telemetry data of the yaw, pitch, and roll
measured by the IMU. It also shows how to easily retrieve the yaw, pitch, and roll measurements.

I hope you find these samples useful! Good luck teams!
