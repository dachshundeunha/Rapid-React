// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

public class ColorSensor {

    private final I2C.Port i2c = I2C.Port.kOnboard;
    private final ColorSensorV3 colorSensor = new ColorSensorV3(i2c);
    private Color detectedColor;
    private int proximity;

    public ColorSensor() {
        detectedColor = colorSensor.getColor();
        proximity = colorSensor.getProximity();
    }

    public void updateColorSensor() {
        detectedColor = colorSensor.getColor();
        proximity = colorSensor.getProximity();

        SmartDashboard.putNumber("ColoSens Proximity", proximity);
    }

    public String getColor() {
        double mRed = detectedColor.red;
        double mBlue = detectedColor.blue;

        if (getBallPresence()) {
            if (mRed > mBlue) {
                return "Red";
            } else if (mBlue > mRed) {
                return "Blue";
            }
        }
        return null;
    }

    public boolean getEnemyPresence() {
        if (Constants.ALLIANCE) {
            if (getColor().equals("Red")) {
                return true;
            } else {
                return false;
            }
        } else {
            if (getColor().equals("Blue")) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean getBallPresence() {
        int distance = proximity;

        if (distance >= Constants.MINIMUM_PRESENCE_DISTANCE
        && distance <= Constants.MAXIMUM_PRESENCE_DISTANCE) {
            return true;
        }
        return false;
    }

    public double proximityToCmConverter() {
        double numerator = proximity / 1000;
        double cm = Math.log(numerator) / Math.log(0.64889);
        return cm;
    }

    public double getDistance() {
        double cmdistance = proximityToCmConverter();
        return cmdistance;
    }

}

