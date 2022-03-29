// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {

  private TalonFX leftHanger, rightHanger;
  private PIDController pidController;

  public ClimberSubsystem() {
    leftHanger = new TalonFX(Constants.LEFT_HANGER);
    rightHanger = new TalonFX(Constants.RIGHT_HANGER);

    leftHanger.setNeutralMode(NeutralMode.Brake);
    rightHanger.setNeutralMode(NeutralMode.Brake);

    pidController = new PIDController(Constants.hkP, Constants.hkI, Constants.hkD);
  }

  @Override
  public void periodic() {
  }

  public void moveClimbers(double speed) {
    rightHanger.follow(leftHanger);
    leftHanger.set(ControlMode.PercentOutput, speed);
  }

  public void moveLeftClimber(double speed) {
    leftHanger.set(ControlMode.PercentOutput, speed);
  }

  public void moveRightClimber(double speed) {
    rightHanger.set(ControlMode.PercentOutput, speed);
  }

  

  public void enableVirtualLock() { // TODO: Finish
    while (Math.abs(getRightEncoder() - getLeftEncoder()) > Constants.ALLOWED_HANGER_DIFF) {
      fixRightHanger();
    }
  } 

  public void disableVirtualLock() {

  }

  public void fixRightHanger() {
    double leftPos = getLeftEncoder();
    pidController.setSetpoint(leftPos);
    double speed = pidController.calculate(getLeftEncoder());
    leftHanger.set(ControlMode.PercentOutput, speed);
  }

  public void fixLeftHanger() {
    double rightPos = getRightEncoder();
    pidController.setSetpoint(rightPos);
    double speed = pidController.calculate(getRightEncoder());
    rightHanger.set(ControlMode.PercentOutput, speed);
  }

  public void resetHangerEncoders() {
    leftHanger.setSelectedSensorPosition(0);
    rightHanger.setSelectedSensorPosition(0);
  }

  public double getLeftEncoder() {
    return leftHanger.getSelectedSensorPosition();
  }

  public double getRightEncoder() {
    return rightHanger.getSelectedSensorPosition();
  }
}
