// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class IntakeSubsystem extends SubsystemBase {

  private TalonFX intakeMotor = new TalonFX(Constants.INTAKE_MOTOR);
  private DoubleSolenoid intakeBar = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.INTAKE_FORWARD_CHANNEL, Constants.INTAKE_REVERSE_CHANNEL);

  public IntakeSubsystem() {
    intakeMotor.setInverted(true);
  }

  @Override
  public void periodic() {
    RobotContainer.myColorSensor.updateColorSensor();
  }

  public void deployIntakeBar() {
    intakeBar.set(Value.kForward);
  }

  public void retractIntakeBar() {
    intakeBar.set(Value.kReverse);
  }


  public Value getLeftIntakeBarValue() {
    return intakeBar.get();
  }
  

  public void stopIntakeBar() {
    intakeBar.set(Value.kOff);
  }

  public void runIntake(double speed) {
    intakeMotor.set(ControlMode.PercentOutput, speed);
  }
}