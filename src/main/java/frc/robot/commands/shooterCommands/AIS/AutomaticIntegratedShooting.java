// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooterCommands.AIS;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class AutomaticIntegratedShooting extends ParallelCommandGroup {
  public AutomaticIntegratedShooting(TurretSubsystem turret, ShooterSubsystem shoot, IntakeSubsystem intake) {
    addCommands(
      new TargetHoop(turret),
      new FireBall(shoot),
      new FeederSpeeder(intake)
    );
  }
}