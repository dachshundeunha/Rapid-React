// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {
    public static boolean ALLIANCE = true;                          // true = blue, false = red

    public static final double METER_TO_INCH = 39.3701;             // Conversion from Meters to Inches
    public static final double INCH_TO_METER = 0.0245;              // Conversion from Inches to Meters
    public static final double TO_RADIANS = Math.PI / 180.0;        // Conversion from Degrees to Radians

    // Drivetrain Subsystem (0s)
    public static final int LEFT_FRONT_MOTOR = 1;                   // Left Front Motor TalonSRX ID
    public static final int LEFT_REAR_MOTOR = 2;                    // Left Rear Motor TalonSRX ID
    public static final int RIGHT_FRONT_MOTOR = 3;                  // Right Front Motor TalonSRX ID
    public static final int RIGHT_REAR_MOTOR = 4;                   // Right Rear Motor TalonSRX ID

        // Autonomous Drive
        public static final double AUTO_DRIVE_SPEED = 0.6;          // Speed during Autonomous
        public static final double AUTO_DRIVE_ROTATION = 0.7;       // Rotation Speed during Autonomous

        public static final double STAGE_ONE_DISTANCE = 1.0;        // Autonomous Mvt. 1, Forward Distance X Meters
        public static final double STAGE_TWO_ANGLE = 0.0;           // Autonomous Mvt. 2, Turn X Degrees
        public static final double STAGE_THREE_DISTANCE = 2.0;      // Autonomous Mvt. 3, Forward Distance X Meters
        
        // PID Control [WIP]
        public static final double dkP = 0.03;
        public static final double dkI = 0.00;
        public static final double dkD = 0.00;
        public static final double dkF = 0.00;
        public static final double dkToleranceDegrees = 2.0f;
        

    // Automatic Integrated Shooter System
    public static final int TOP_SHOOTER = 21;                       // Top Shooter TalonFX ID
    public static final int BOTTOM_SHOOTER = 22;                    // Bottom Shooter TalonFX ID
    public static final int TURRET = 23;                            // Turret TalonFX ID

    public static final int INTAKE_MOTOR = 40;                      // Intake Motor TalonSRX ID
    public static final int HOLD_WHEEL_1 = 41;                      // Hold Wheel 1 (Blorange) TalonSRX ID
    public static final int HOLD_WHEEL_2 = 42;                      // Hold Wheel 2 (Blue) TalonSRX ID
    public static final int THE_EPITOME_OF_STUPIDITY = 49;          // ( ͡° ͜ʖ ͡°)

    public static final int INTAKE_FORWARD_CHANNEL = 0;             // Deploy Intake Bar Pneumatic
    public static final int INTAKE_REVERSE_CHANNEL = 1;             // Retract Intake Bar Pneumatic

        // AIS
        public static final double OPTIMAL_DISTANCE = 16.35;          // Perfect distance from Limelight lens to Hub vision target
        public static final double ALLOWED_DISTANCE_ERROR = 0.5;    // Allowed distance error from optimal in order to shoot 

        public static final double ALLOWED_X_ERROR = 0.5;             // Allowed X Error for Auto Turret Aim
        public static final double CRUISE_VELOCITY = 0.05;          // Velocity max of range too low to significantly impact shoot-while-moving
        public static final double ALLOWED_VELOCITY = 0.5;          // Velocity max of range where short-while-moving works

        public static final double ADJUSTMENT_FACTOR = 0.1;         // Constant that converts robot X-component velocity to adjusting ball motion
        public static final double ROAM_VELOCITY = 0.3;             // When Limelight finds no vision target
        public static final double LEFT_HANGER_LIMIT = 49000.0;     // At 270 Degrees
        public static final double RIGHT_HANGER_LIMIT = -49000.0;   // At 90 Degrees
        public static final double UPPER_TURRET_LIMIT = 50000.0;
        public static final double LOWER_TURRET_LIMIT = -50000.0;

        public static final double ROTATION_CONSTANT = 0.04;        // Used in TargetHoop Command 
        public static final double SHOOTER_SPEED = 0.6;             // Shooter Speed 
        public static final double FEEDER_SPEED = 0.7;              // Feeder Speed
        public static final double INTAKE_SPEED = 0.4;              // Intake Speed
        public static final double TIME_NEEDED_TO_SPIT = 1;         // Time required to eject enemy ball from intake

        // Turret PID [WIP]
        public static final double tkP = 0.03;
        public static final double tkI = 0.00;
        public static final double tkD = 0.00;
        public static final double tkF = 0.00;


    // Hanger Subsystem (30s)
    public static final int LEFT_HANGER = 31;                       // Left Hanger TalonFX ID
    public static final int RIGHT_HANGER = 32;                      // Right Hanger TalonFX ID

        // Hanger PID 
        public static final double hkP = 0.1;
        public static final double hkI = 0.04;
        public static final double hkD = 0.03;

        public static final double TOP_HANGER_SETPOINT = -105000.0;  // Up to upper lock point
        public static final double BOTTOM_HANGER_SETPOINT = 14000;    // Down to lower hook point
        public static final double ALLOWED_HANGER_DIFF = 8000;      // Error Range for Hanger Endpoint

    // Xbox Controllers
    public static final int DRIVE_CONT = 0;                         // Driver Controller ID
    public static final int WEAPONS_CONT = 1;                       // Weapons Controller ID

        // POV
        public static final int dpadUp = 0;
        public static final int dpadUpRight = 45;
        public static final int dpadRight = 90;
        public static final int dpadDownRight = 135;
        public static final int dpadDown = 180;
        public static final int dpadDownLeft = 225;
        public static final int dPadLeft = 270;
        public static final int dPadUpLeft = 315;

        // Buttons
        public static final int A_BUTTON = 1;
        public static final int B_BUTTON = 2;
        public static final int X_BUTTON = 3;
        public static final int Y_BUTTON = 4;
        public static final int LEFT_BUMPER = 5;
        public static final int RIGHT_BUMPER = 6;
        public static final int BACK_BUTTON = 7;
        public static final int START_BUTTON = 8;
        public static final int LEFT_JOYSTICK_BUTTON = 9;
        public static final int RIGHT_JOYSTICK_BUTTON = 10;


    // Color Sensor
    public static final int MINIMUM_PRESENCE_DISTANCE = 0;          // TODO: Test
    public static final int MAXIMUM_PRESENCE_DISTANCE = 0;          // TODO: Test
}