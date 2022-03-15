// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

/** Add your docs here. */
public class Intake {  
    VictorSPX Motor; 
    public Intake(VictorSPX V){
    Motor = V;  
    }
public void OhmNom() {
    Motor.set(ControlMode.PercentOutput, .5);
}
public void PewPew(double  Speed) {
    Motor.set(ControlMode.PercentOutput, -Speed);

}
public void StopIntake() {
    Motor.set(ControlMode.PercentOutput, 0);
    
}
}
