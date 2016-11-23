# MR Color Sensor
Hello! Welcome to the FTC Samples Repository created by Varun Singh, Lead Programmer of Team 4997 Masquerade.

This contains two files: MasqMRColorSensor and MasqMRColorSensorDemo. You can just simply copy-and-paste these two files
into your teamcode package. If you have your code structured differently, make sure to modify the package declaration
at the top of each file.

In your robot configuration file, make sure to have the color sensor named "colorSensor" (at least for the demo) and 
declared of type I2cDevice (NOT as a ColorSensor) in the I2C Ports section of the Device Interface Module.

If you have changed the I2C address of your color sensor using the MR Core Device Discovery application, remember to 
set the new address in the code (the appropriate place is shown in the demo program).

MasqMRColorSensorIMU is the "driver" for the Modern Robotics Color Sensor. It allows for easy and accurate 
retrieval of ARGB and HSV data. In addition, it provides the Color Number outputted by the MR Color Sensor, which is a 
special feature provided by Modern Robotics. Find out more here: http://modernroboticsinc.com/color-sensor

MasqMRColorSensorDemo is a demonstration teleop program that just outputs telemetry data and shows how to use the driver.

I hope you find these samples useful! Good luck teams!
