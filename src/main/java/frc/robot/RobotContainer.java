// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.Wait;
import frc.robot.commands.driveCommands.*;
import frc.robot.commands.shooterCommands.*;
import frc.robot.commands.shooterCommands.AIS.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;


public class RobotContainer {

  // Subsystems
  public final static DrivetrainSubsystem myDrive = new DrivetrainSubsystem();
  private final ShooterSubsystem myShooter = new ShooterSubsystem();
  private final ClimberSubsystem myClimber = new ClimberSubsystem();
  private final IntakeSubsystem myIntake = new IntakeSubsystem();
  private final TurretSubsystem myTurret = new TurretSubsystem();

  public static boolean shooterReady = false;

  // Controllers 
  public static XboxController cont = new XboxController(Constants.DRIVE_CONT);
  public static XboxController weapons = new XboxController(Constants.WEAPONS_CONT);
  public static Joystick joy = new Joystick(0);

  // Devices
  public static Limelight myLimelight = new Limelight();
  public static IMU imu = new IMU();
  public static ColorSensor myColorSensor = new ColorSensor();


  public RobotContainer() {
    
      myDrive.setDefaultCommand(new ManualDrive(myDrive,
      () -> cont.getRightX(),
      () -> -cont.getLeftY(),
      () -> cont.getRightBumper()));
      
      

      
      myClimber.setDefaultCommand(new ManualHang(myClimber,
      () -> cont.getRightTriggerAxis(),
      () -> cont.getLeftTriggerAxis()));
    
          
    // myShooter.setDefaultCommand(new FireBall(myShooter));
    // myTurret.setDefaultCommand(new TargetHoop(myTurret));

    
    
    myTurret.setDefaultCommand(new ManualTurretControl(myTurret,
    () -> weapons.getRightX()));
    

    
    
    myIntake.setDefaultCommand(new HandFeeder(myIntake,
    () -> weapons.getBButton(),
    () -> weapons.getStartButton()));
    
    
    
    myShooter.setDefaultCommand(new ManualShooting(myShooter, 
    () -> weapons.getXButton(),
    () -> weapons.getAButton(),
    () -> weapons.getBackButton()));
    


    configureButtonBindings();
  }

  private void configureButtonBindings() {
    new POVButton(cont, Constants.dpadUp).whenPressed(new AutoHang(myClimber, Constants.TOP_HANGER_SETPOINT));          // Make Hangers Go To Top Posiition

    new POVButton(cont, Constants.dpadDown).whenPressed(new AutoHang(myClimber, Constants.BOTTOM_HANGER_SETPOINT));     // Make Hangers Go to Bottom (lock) Position

    new JoystickButton(weapons, Constants.Y_BUTTON).whileHeld(new TargetHoop(myTurret));                                // Target Turret
      
    // new JoystickButton(weapons, Constants.LEFT_BUMPER).whenPressed(new ControlIntakeBar(myIntake));                  // Toggle Intake Bar (A Button)
     new POVButton(weapons, Constants.dpadUp).whenPressed(new ControlIntakeBar(myIntake));
  }

  public Command getAutonomousCommand() {    
    // return new PlayCottonEyeJoe(myShooter, 15);
    
    // return new Wait(5);

    
    return new ParallelCommandGroup(

      new SequentialCommandGroup(
      new ControlIntakeBar(myIntake),
      new AutoFeeder(myIntake, false, 5),
      new AutoFeeder(myIntake, true, 5),
      new AutoFeeder(myIntake, false, 3)
      ),

      new SequentialCommandGroup(
        new TimedForward(myDrive, 5, 0.0),
        new ParallelCommandGroup(
          new TimedForward(myDrive, 2.3, 0.6)     
        ),
        new TimedForward(myDrive, 2, -0.6),
        new TimedForward(myDrive, 4, 0.0)
      ),

      new SequentialCommandGroup(
        new AutoShooting(myShooter, true, false, 2, 0.49),
        new AutoShooting(myShooter, true, true, 1.5, 0.49),
        new AutoShooting(myShooter, true, false, 6.7, 0.52),
        new AutoShooting(myShooter, true, true, 4, 0.52)
      )
    );

  }
}