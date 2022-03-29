// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooterCommands.AIS;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.TurretSubsystem;

public class TargetHoop extends CommandBase {

  private TurretSubsystem sub;
  private double x, rotationSpeed;
  private boolean inXRange;
  private boolean roamDirection; // true -> Clockwise, false -> Counterclockwise

  public TargetHoop(TurretSubsystem mew) {
    this.sub = mew;
    roamDirection = true;

    addRequirements(sub);
  }

  @Override
  public void initialize() {
    System.out.println("Auto targeting engaged");
  }

  @Override
  public void execute() {
    SmartDashboard.putBoolean("Has Target", RobotContainer.myLimelight.hasTarget());
    boolean temp = RobotContainer.myLimelight.hasTarget();

    if (temp) { 
      snap();
      System.out.println("Snapping");
    } else {       
      roam();
      System.out.println("Roaming");
    }  
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("Auto targeting disengaged");
  }

  @Override
  public boolean isFinished() {
    return false;
  }
  
  private void snap() {
    x = RobotContainer.myLimelight.getHorizontalOffset();
    SmartDashboard.putNumber("X", x);
    double net = x + getAdjustment();
  
    if (sub.getSensorPosition() >= Constants.LEFT_HANGER_LIMIT || sub.getSensorPosition() <= Constants.RIGHT_HANGER_LIMIT) {
      roam();
    } else {
      rotationSpeed = Constants.ROTATION_CONSTANT * net;
      if (Math.abs(net) <= Constants.ALLOWED_X_ERROR) {
        inXRange = true;
        RobotContainer.shooterReady = true;
      } else {
        inXRange = false;
        RobotContainer.shooterReady = false;
      }
  
      if (!inXRange) {
        sub.rotateTurret(rotationSpeed);
      } else {
        sub.rotateTurret(0.0);
      }
    }
  }

  private void roam() {
    RobotContainer.shooterReady = false;

    if (sub.getSensorPosition() >= Constants.LEFT_HANGER_LIMIT) {
      roamDirection = false;                                                
    } else if (sub.getSensorPosition() <= Constants.RIGHT_HANGER_LIMIT) {
      roamDirection = true;
    }
    
    if (roamDirection) {
      sub.rotateTurret(Constants.ROAM_VELOCITY);
    } else {
      sub.rotateTurret(-Constants.ROAM_VELOCITY);
    }
  }

  private double getAdjustment() {
    double turretAngle = sub.convertToRadians();
    double imuVelocity = RobotContainer.imu.getVelocityX();

    if (imuVelocity <= Constants.CRUISE_VELOCITY) {
      return 0.0;
    }

    double xComponentRobotVelocity = -Math.abs(Math.sin(turretAngle)) * imuVelocity;
    double adjustment = xComponentRobotVelocity * Constants.ADJUSTMENT_FACTOR;    //TODO: Find adjustment factor

    return adjustment;
  }
}