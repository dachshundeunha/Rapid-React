// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.driveCommands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class ManualDrive extends CommandBase {

  private DrivetrainSubsystem sub;
  private final Supplier<Double> speedFunction, turnFunction;
  private final Supplier<Boolean> slowMode;


  public ManualDrive(DrivetrainSubsystem mew, Supplier<Double> speedFunction, Supplier<Double> turnFunction, Supplier<Boolean> slowMode) {
    this.sub = mew;
    this.speedFunction = speedFunction;
    this.turnFunction = turnFunction;
    this.slowMode = slowMode;
    addRequirements(sub);
  }

  @Override
  public void initialize() {
    System.out.println("Manual drive engaged");
  }

  @Override
  public void execute() {
    double realTimeSpeed = speedFunction.get();
    double realTimeTurn = turnFunction.get();
    boolean slow = slowMode.get();

    
    if (slow) {
      realTimeSpeed *= 0.5;
      realTimeTurn *= 0.5;
    } 
    

    sub.arcadeDrive(realTimeSpeed, realTimeTurn);
    // sub.curvatureDrive(realTimeTurn, realTimeSpeed, slow);
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("Manual drive disengaged");
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}