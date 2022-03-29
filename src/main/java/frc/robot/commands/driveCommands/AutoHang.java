// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.driveCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ClimberSubsystem;

public class AutoHang extends CommandBase {

  private ClimberSubsystem sub;
  private PIDController pidControllerLeft, pidControllerRight;
  private double point;

  public AutoHang(ClimberSubsystem mew, double setpoint) {
    this.sub = mew;
    this.point = setpoint;
    pidControllerLeft = new PIDController(Constants.hkP, Constants.hkI, Constants.hkD);
    pidControllerRight = new PIDController(Constants.hkP, Constants.hkI, Constants.hkD);
    pidControllerLeft.setSetpoint(setpoint);
    pidControllerRight.setSetpoint(setpoint);
    addRequirements(sub);
  }

  @Override
  public void initialize() {
    System.out.print("Auto hang engaged");
    pidControllerLeft.reset();
    pidControllerRight.reset();
  }

  @Override
  public void execute() {
    double leftSpeed = pidControllerLeft.calculate(sub.getLeftEncoder());
    double rightSpeed = pidControllerRight.calculate(sub.getRightEncoder());
    sub.moveLeftClimber(leftSpeed);
    sub.moveRightClimber(rightSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("Auto hang disengaged");
  }

  @Override
  public boolean isFinished() {
    return (Math.abs(sub.getLeftEncoder() - point) <= Constants.ALLOWED_HANGER_DIFF)
      && (Math.abs(sub.getRightEncoder() - point) <= Constants.ALLOWED_HANGER_DIFF);
  }
}