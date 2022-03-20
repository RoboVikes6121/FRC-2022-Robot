// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

/** Add your docs here. */
public class Intake {  
    static VictorSPX Motor = new VictorSPX(6);

public static void OhmNom() {
    Motor.set(ControlMode.PercentOutput, .5);
}
public static void PewPew(double  Speed) {
    Motor.set(ControlMode.PercentOutput, -Speed);

}
public static void StopIntake() {
    Motor.set(ControlMode.PercentOutput, 0);
    
}
}
