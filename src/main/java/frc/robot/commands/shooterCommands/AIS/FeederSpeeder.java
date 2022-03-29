// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooterCommands.AIS;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.IntakeSubsystem;

public class FeederSpeeder extends CommandBase {

  private IntakeSubsystem sub;
  private boolean hasBall;

  public FeederSpeeder(IntakeSubsystem mew) {
    this.sub = mew;
    addRequirements(sub);
  }

  @Override
  public void initialize() {
    System.out.println("Auto intake engaged");
    
    if (!sub.getLeftIntakeBarValue().equals(Value.kForward)) {
      sub.deployIntakeBar();
    }
    
    RobotContainer.myColorSensor.updateColorSensor();
    hasBall = RobotContainer.myColorSensor.getBallPresence();
  }

  @Override
  public void execute() {
    RobotContainer.myColorSensor.updateColorSensor();
    hasBall = RobotContainer.myColorSensor.getBallPresence();

    if (hasBall) {
      if (RobotContainer.myColorSensor.getEnemyPresence()) {
        sub.runIntake(-Constants.INTAKE_SPEED);
        Timer.delay(Constants.TIME_NEEDED_TO_SPIT);
      } else {
        sub.runIntake(0.0);
      }
    } else {
      sub.runIntake(-Constants.INTAKE_SPEED);
    }
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("Auto intake disengaged");
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}