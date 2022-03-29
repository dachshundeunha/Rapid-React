// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.driveCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DrivetrainSubsystem;

public class TimedTurn extends CommandBase {

  private DrivetrainSubsystem sub;
  private double interval;
  private int direction;
  private final Timer timer = new Timer();

  public TimedTurn(DrivetrainSubsystem mew, double interval, int direction) {
    this.sub = mew;
    this.interval = interval;
    this.direction = direction;
    addRequirements(sub);
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    sub.arcadeDrive(0.0, direction * Constants.AUTO_DRIVE_ROTATION);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    sub.arcadeDrive(0.0, 0.0);
  }

  @Override
  public boolean isFinished() {
    return timer.get() >= interval;
  }
}
