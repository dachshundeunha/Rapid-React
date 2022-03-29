// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// TODO: Finish
package frc.robot.commands.driveCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.IMU;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DriveDistance extends CommandBase {

  private DrivetrainSubsystem sub;
  private IMU imu;

  public DriveDistance(DrivetrainSubsystem mew, IMU mew2, double distance) {
    this.sub = mew;
    this.imu = mew2;
    addRequirements(sub);
  }

  @Override
  public void initialize() {
    imu.reset();
    sub.arcadeDrive(1.0, 0.0);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    sub.arcadeDrive(0,0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}