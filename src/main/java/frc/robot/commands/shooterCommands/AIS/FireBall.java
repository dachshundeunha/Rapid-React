// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooterCommands.AIS;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ShooterSubsystem;

public class FireBall extends CommandBase {

  private ShooterSubsystem sub;

  public FireBall(ShooterSubsystem mew) {
    this.sub = mew;
    addRequirements(sub);
  }

  @Override
  public void initialize() {
    System.out.println("Auto shooting engaged");
  }

  @Override
  public void execute() {
    sub.shoot(Constants.SHOOTER_SPEED);
    SmartDashboard.putBoolean("Can Shoot", testShootingAbility());


    
    if (testShootingAbility()) {
      sub.controlWheels(Constants.FEEDER_SPEED);
    } else {
      sub.controlWheels(0.0);                   
    }
    
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("Auto shooting disengaged");
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  private boolean testShootingAbility() {
    double nv = RobotContainer.imu.getNetVelocity();
    if (nv <= Constants.ALLOWED_VELOCITY
     && Math.abs(RobotContainer.myLimelight.getVerticalOffset() - Constants.OPTIMAL_DISTANCE) <= Constants.ALLOWED_DISTANCE_ERROR
     && RobotContainer.shooterReady) {
      return true;
    } else {
      return false;
    }
  }
}