// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooterCommands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class ControlIntakeBar extends CommandBase {

  private IntakeSubsystem sub;
  private boolean hasChanged = false;

  public ControlIntakeBar(IntakeSubsystem mew) {
    this.sub = mew;
    addRequirements(sub);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if (!sub.getLeftIntakeBarValue().equals(Value.kForward)) {
      sub.deployIntakeBar();
      hasChanged = true;
    } else {
      sub.retractIntakeBar();
      hasChanged = true;
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    if (hasChanged) {
      return true;
    }
    return false;
  }
}
