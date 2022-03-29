// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooterCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class ManualIntake extends CommandBase {

  private ShooterSubsystem dub;

  public ManualIntake(ShooterSubsystem lew) {
    this.dub = lew;
    addRequirements(dub);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {

 
      dub.controlWheels(0.7);

  }

  @Override
  public void end(boolean interrupted) {
    dub.controlWheels(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}