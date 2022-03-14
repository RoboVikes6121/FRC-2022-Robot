// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/** Add your docs here. */
public class Arm {

    TalonFX arm;

    public Arm(TalonFX a){
        arm = a;
        arm.setInverted(false);
        arm.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 10,15,1));
    arm.configClearPositionOnLimitF(false, 0);
    arm.configClearPositionOnLimitR(false, 0);
    arm.configClearPositionOnQuadIdx(false, 0);
    arm.setSelectedSensorPosition(0, 0, 0);
    }

}
