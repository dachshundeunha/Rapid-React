// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.music.Orchestra;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ShooterSubsystem extends SubsystemBase {
  
  private final TalonFX topShooter;
  private final TalonFX bottomShooter;

  private WPI_TalonSRX blorangeWheel = new WPI_TalonSRX(Constants.HOLD_WHEEL_1);
  private WPI_TalonSRX blueWheel = new WPI_TalonSRX(Constants.HOLD_WHEEL_2);

  private String song = "song10.chrp";
  private Orchestra orchestra;

  public ShooterSubsystem() {
    topShooter = new TalonFX(Constants.TOP_SHOOTER);
    bottomShooter = new TalonFX(Constants.BOTTOM_SHOOTER);
    topShooter.setInverted(false);

    bottomShooter.set(ControlMode.PercentOutput, 0);
    bottomShooter.setInverted(true);

    blorangeWheel.setInverted(true);
    blueWheel.setInverted(false);  

    topShooter.setNeutralMode(NeutralMode.Brake);
    bottomShooter.setNeutralMode(NeutralMode.Brake);
  }

  public void shoot(double shooterSpeed) {
    bottomShooter.set(ControlMode.PercentOutput, 0.5 * shooterSpeed);
    topShooter.set(ControlMode.PercentOutput, shooterSpeed);
  } 

  @Override
  public void periodic() {
    double distanceToGoal = RobotContainer.myLimelight.distanceFromLimelightToGoalInches();
    SmartDashboard.putNumber("Distance to Goal", distanceToGoal);

  }

  public void controlWheel1(double speed) {
    blorangeWheel.set(speed);
  }

  public void controlWheel2(double speed) {
    blueWheel.set(speed);
  }

  public void controlWheels(double speed) {
    blorangeWheel.set(speed);
    blueWheel.set(speed);
  }


  /*
  public void playMusic() {
    ArrayList<TalonFX> instruments = new ArrayList<TalonFX>();
    instruments.add(topShooter);
    instruments.add(bottomShooter);
    orchestra = new Orchestra(instruments);
    orchestra.loadMusic(song);
    System.out.println("Music playing");
    orchestra.play();
  }
  */
}

