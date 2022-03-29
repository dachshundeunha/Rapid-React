// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// TODO: Finish
package frc.robot.commands.driveCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DrivetrainSubsystem;

public class TurnAngle extends CommandBase {

  private DrivetrainSubsystem sub;
  private PIDController turnController;
  private double startingAngle, finishingAngle;

  public TurnAngle(DrivetrainSubsystem mew, double angle) {
    this.sub = mew;
    this.finishingAngle = angle;
    addRequirements(sub);

    turnController = new PIDController(Constants.dkP, Constants.dkI, Constants.dkD);
    turnController.setTolerance(Constants.dkToleranceDegrees);
    turnController.enableContinuousInput(-180.0f, 180.0f);
  }

  @Override
  public void initialize() {
  
  }

  @Override
  public void execute() {
    turnController.setSetpoint(finishingAngle);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
