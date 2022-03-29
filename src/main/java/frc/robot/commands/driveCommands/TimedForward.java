// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.driveCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class TimedForward extends CommandBase {

  private DrivetrainSubsystem sub;
  private double interval, speed;
  private final Timer timer = new Timer();

  public TimedForward(DrivetrainSubsystem mew, double interval, double speed) {
    this.sub = mew;
    this.interval = interval;
    this.speed = speed;
    addRequirements(sub);
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  @Override
  public void execute() {
    sub.arcadeDrive(0.0, speed);
  }

  @Override
  public void end(boolean interrupted) {
    sub.arcadeDrive(0.0, 0.0);
  }

  @Override
  public boolean isFinished() {
    return timer.get() >= interval;
  }
}
