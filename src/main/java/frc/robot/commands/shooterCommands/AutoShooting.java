// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooterCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoShooting extends CommandBase {

  private ShooterSubsystem sub;
  private boolean doesShoot, doesIntake;
  private double interval, power;
  private final Timer timer = new Timer();

  public AutoShooting(ShooterSubsystem mew, boolean doesShoot, boolean doesIntake, double interval, double power) {
    this.doesShoot = doesShoot;
    this.doesIntake = doesIntake;
    this.power = power;
    this.sub = mew;
    this.interval = interval;
    addRequirements(sub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (doesShoot) {
      sub.shoot(power);
    } else {
      sub.shoot(0);
    }

    if (doesIntake) {
      sub.controlWheels(0.85);
    } else {
      sub.controlWheels(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.get() >= interval;
  }
}
