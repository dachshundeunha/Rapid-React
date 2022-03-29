// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooterCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class AutoFeeder extends CommandBase {

  private IntakeSubsystem sub;
  private boolean doesIntake;
  private double interval;
  private final Timer timer = new Timer();
  
  public AutoFeeder(IntakeSubsystem mew, boolean doesIntake, double interval) {
    this.sub = mew;
    this.doesIntake = doesIntake;
    this.interval = interval;
    addRequirements(sub);
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  @Override
  public void execute() {
    if (doesIntake) {
      sub.runIntake(1);
    } else {
      sub.runIntake(0);
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return timer.get() > interval;
  }
}