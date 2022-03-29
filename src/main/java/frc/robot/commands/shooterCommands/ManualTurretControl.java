// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooterCommands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;

public class ManualTurretControl extends CommandBase {

  private TurretSubsystem sub;
  private Supplier<Double> turretSpeed;

  public ManualTurretControl(TurretSubsystem mew, Supplier<Double> turretSpeed) {
    this.sub = mew;
    this.turretSpeed = turretSpeed;
    addRequirements(sub);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double rot = 0.5* turretSpeed.get();
    sub.rotateTurret(rot);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
