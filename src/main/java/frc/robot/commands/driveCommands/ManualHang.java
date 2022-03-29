// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.driveCommands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class ManualHang extends CommandBase {

  private ClimberSubsystem sub;
  private final Supplier<Double> left, right;

  public ManualHang(ClimberSubsystem mew, Supplier<Double> left, Supplier<Double> right) {
    this.sub = mew;
    this.left = left;
    this.right = right;
    addRequirements(sub);
  }


  @Override
  public void initialize() {}

  @Override
  public void execute() {
 
  

    double leftClimbSpeed = right.get() - left.get();
    sub.moveClimbers(-leftClimbSpeed);
   //  sub.moveRightClimber(rightClimbSpeed);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
