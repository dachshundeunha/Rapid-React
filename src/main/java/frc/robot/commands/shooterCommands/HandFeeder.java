// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooterCommands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class HandFeeder extends CommandBase {

  private IntakeSubsystem sub;
  private final Supplier<Boolean> speedFunction, direction;
  private int dira;

  public HandFeeder(IntakeSubsystem mew, Supplier<Boolean> speedFunction, Supplier<Boolean> direction) {
    this.sub = mew;
    this.direction = direction;
    this.speedFunction = speedFunction;
    addRequirements(sub);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    boolean spe = speedFunction.get();
    boolean dir = direction.get();

    if (dir) {
      dira = 1;
    } else {
      dira = -1;
    }

    double speed = -dira;

    if (spe) {
      sub.runIntake(speed);
    } else {
      sub.runIntake(0);
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
