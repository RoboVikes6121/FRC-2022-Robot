// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;

/** Add your docs here. */
public class Constant {

    final double armHoldUp = 0.08;
    final double armHoldDown = 0.13;
    final double armTravel = 0.5;
    final double armTimeUp = 0.5;
    final double armTimeDown = 0.35;
    boolean startleft = false;
    boolean startRight = false;
    boolean armUp = true;
    boolean burstMode = false;
    double lastBurstTime = 0;
    boolean godown = false;
    boolean goup = false;
    double autoStart = 0;
    boolean goForAuto = false;
    final int button13 = 13;
    double speedMultiplier = 0.7;
    double shooterSpeed = 0.9;

    
    public void intakeBall(){

        //intake.set(ControlMode.PercentOutput, .5); // ohm nom
    
      }
      public void stopIntake(){
      //  intake.set(ControlMode.PercentOutput, 0);
      }
     
  
}
