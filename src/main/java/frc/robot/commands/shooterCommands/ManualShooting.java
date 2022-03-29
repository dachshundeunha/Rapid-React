// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooterCommands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class ManualShooting extends CommandBase {

  private ShooterSubsystem sub;
  private Supplier<Boolean> shootingSpeed, intakeSpeed, direction;
  private int dira;

  public ManualShooting(ShooterSubsystem mew, Supplier<Boolean> shootingSpeed, Supplier<Boolean> intakeSpeed, Supplier<Boolean> direction) {
    this.sub = mew;
    this.intakeSpeed = intakeSpeed;
    this.shootingSpeed = shootingSpeed;
    this.direction = direction;
    addRequirements(sub);
  }

  
  @Override
  public void initialize() {
    System.out.println("Manual shooting engaged");
  }

  @Override
  public void execute() {
    boolean shoot = shootingSpeed.get();
    boolean feed = intakeSpeed.get();
    boolean dir = direction.get();

    if (dir) {
      dira = 1;
    } else {
      dira = -1;
    }

    double shootingSpeed = 0.9;
    double feederSpeed = -0.85 * dira;

    if (shoot) {
      sub.shoot(shootingSpeed);
    } 
     else {
      sub.shoot(0.0);
    }

    if (feed) {
      sub.controlWheels(feederSpeed);
    } 
    else {
      sub.controlWheels(0.0);
    }

  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("Manual shooting disengaged");
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
