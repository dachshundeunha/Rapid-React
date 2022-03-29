// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DrivetrainSubsystem extends SubsystemBase {

  private WPI_TalonSRX leftFrontMotor, leftRearMotor, rightFrontMotor, rightRearMotor;
  private MotorControllerGroup leftSide, rightSide;
  private DifferentialDrive myDrive;
  


  public DrivetrainSubsystem() {
    // Drivetrain
    leftFrontMotor = new WPI_TalonSRX(Constants.LEFT_FRONT_MOTOR);
    leftRearMotor = new WPI_TalonSRX(Constants.LEFT_REAR_MOTOR);
    rightFrontMotor = new WPI_TalonSRX(Constants.RIGHT_FRONT_MOTOR);
    rightRearMotor = new WPI_TalonSRX(Constants.RIGHT_REAR_MOTOR);

    leftFrontMotor.setNeutralMode(NeutralMode.Brake);
    leftRearMotor.setNeutralMode(NeutralMode.Brake);
    rightFrontMotor.setNeutralMode(NeutralMode.Brake);
    rightRearMotor.setNeutralMode(NeutralMode.Brake);

    leftSide = new MotorControllerGroup(leftFrontMotor, leftRearMotor);
    rightSide = new MotorControllerGroup(rightFrontMotor, rightRearMotor);
    myDrive = new DifferentialDrive(leftSide, rightSide);    

    myDrive.setSafetyEnabled(false);
  }

  @Override
  public void periodic() {
  }

  public void arcadeDrive(double speed, double rotation) {
    myDrive.arcadeDrive(speed, rotation);
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    myDrive.tankDrive(leftSpeed, rightSpeed);
  }

  public void curvatureDrive(double xSpeed, double zRotation, boolean turnInPlace) {
    myDrive.curvatureDrive(xSpeed, zRotation, turnInPlace);
  }

 

  public void rotateClockwise90() {
    double end = RobotContainer.imu.getAngle() + 90.0;
    double current = RobotContainer.imu.getAngle();
    while (current < end) {
      myDrive.arcadeDrive(0,0.7);
      current = RobotContainer.imu.getAngle();
    }
  }
}
