// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public final class Limelight {
    private final String _tableName;
    private final NetworkTable limeTable;
    
    private double limelightMountAngleDegrees = 25.0;
    private double limelightLensHeightInches = 20.0;
    private double goalHeightInches = 103.0;

    public Limelight() {
        this._tableName = "limelight";
        this.limeTable = NetworkTableInstance.getDefault().getTable(this._tableName);
    }

    public boolean hasTarget() {
        NetworkTableEntry tv = limeTable.getEntry("tv");
        return tv.getDouble(0.0) == 1.0;
        
    }

    public double getHorizontalOffset() {
        NetworkTableEntry tx = limeTable.getEntry("tx");
        return tx.getDouble(0.0);
    }

    public double getVerticalOffset() {
        NetworkTableEntry ty = limeTable.getEntry("ty");
        return ty.getDouble(0.0);
    }

    public double getTotalArea() {
        NetworkTableEntry ta = limeTable.getEntry("ta");
        return ta.getDouble(0.0);
    }

    private double angleToGoalDegrees() {
        return limelightMountAngleDegrees + getVerticalOffset();
    }

    private double angleToGoalRadians() {
        return angleToGoalDegrees() * Constants.TO_RADIANS;
    }

    public double distanceFromLimelightToGoalInches() {
        return (goalHeightInches - limelightLensHeightInches)/Math.tan(angleToGoalRadians());
    }
}
