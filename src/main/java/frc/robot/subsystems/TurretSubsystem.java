// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TurretSubsystem extends SubsystemBase {
  private final TalonFX turret;

  public TurretSubsystem() {
    turret = new TalonFX(Constants.TURRET);
    turret.setNeutralMode(NeutralMode.Brake);
    turret.configForwardSoftLimitEnable(true);
    turret.configReverseSoftLimitEnable(true);
    turret.configForwardSoftLimitThreshold(Constants.UPPER_TURRET_LIMIT);
    turret.configReverseSoftLimitThreshold(Constants.LOWER_TURRET_LIMIT);
  }

  @Override
  public void periodic() {
  }

  public void rotateTurret(double rotation) {
    turret.set(ControlMode.PercentOutput, rotation);
  }

  public double convertToAngle() {
    return turret.getSelectedSensorPosition() * 0.00184 + 180.0; // Right -> 90, Back -> 180, Left -> 270
  }

  public double convertToRadians() {
    return convertToAngle() * Constants.TO_RADIANS;
  }

  public double getSensorPosition() {
    return turret.getSelectedSensorPosition();
  }

}
