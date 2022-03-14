// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/** Add your docs here. */
public class DriveTrain {
    TalonSRX driveLeftA; 
        TalonSRX driveLeftB;
        TalonSRX driveRightA;
        TalonSRX driveRightB;

    public DriveTrain(TalonSRX la, TalonSRX lb, TalonSRX ra, TalonSRX rb){
        //initialize the motors from the constructor
    TalonSRX driveLeftA = la;
    TalonSRX driveLeftB = lb;
    TalonSRX driveRightA = ra;
    TalonSRX driveRightB = rb;
        

    driveLeftA.setInverted(false);
    driveLeftB.setInverted(false);
    driveRightA.setInverted(false);
    driveRightB.setInverted(true);
   
    //SmartDashboard.putBoolean("right auton", startRight);
    //SmartDashboard.putBoolean("left auton", startleft);
    
    // configuring the b motor to follow the a motor
    driveLeftB.follow(driveLeftA);
    driveRightB.follow(driveRightA);

    //clear position sensors
    driveRightA.configClearPositionOnLimitF(false, 0);
    driveRightA.configClearPositionOnLimitR(false, 0);
    driveRightA.configClearPositionOnQuadIdx(false, 0);
    driveLeftA.configClearPositionOnLimitF(false, 0);
    driveLeftA.configClearPositionOnLimitR(false, 0);
    driveLeftA.configClearPositionOnQuadIdx(false, 0);
    }

    public void driveForward(double power){
        driveLeftA.set(ControlMode.PercentOutput, power);
        driveRightA.set(ControlMode.PercentOutput, power);
      }
      
      public void turnLeft(double power){
      
        driveLeftA.set(ControlMode.PercentOutput, -power);
        driveRightA.set(ControlMode.PercentOutput, power);
      
      }
      public void turnRight(double power){
      
        driveLeftA.set(ControlMode.PercentOutput, power);
        driveRightA.set(ControlMode.PercentOutput, -power);
      }
      public void forwardTurnLeft(double power){
        driveLeftA.set(ControlMode.PercentOutput, -power);
        driveRightA.set(ControlMode.PercentOutput, +power);
      }
      public void forwardTurnRight(double power){
        driveLeftA.set(ControlMode.PercentOutput, +power);
        driveRightA.set(ControlMode.PercentOutput, -power);
      }
      

}
