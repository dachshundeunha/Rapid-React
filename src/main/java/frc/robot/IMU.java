// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort.Port;

public class IMU {
    private AHRS imu;
    
    public IMU() {
      try {
        imu = new AHRS(Port.kUSB1);
      } catch(RuntimeException ex) {
        DriverStation.reportError("Error instantiating navX-MXP1: " + ex.getMessage(), true);
      }

      imu.reset();   
    }

    public double getAngle() {
      return imu.getAngle();
    }

    public double getDisplacementX() {
      return imu.getDisplacementX();
    }

    public double getVelocityX() {
      return imu.getVelocityX();
    }

    public double getDisplacementY() {
      return imu.getDisplacementY();
    }
  
    public double getVelocityY() {
      return imu.getVelocityY();
    }
  
    public double getTurnRate() {
      return -imu.getRate();
    }
    
  public double getNetVelocity() {
    double xVelocity = imu.getVelocityX();
    double yVelocity = imu.getVelocityY();
    double netVelocity = Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(yVelocity, 2));
    return netVelocity;
  }

  public void reset() {
    imu.reset();
    imu.resetDisplacement();
  }
}
