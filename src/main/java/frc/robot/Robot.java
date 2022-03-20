package frc.robot;

import java.sql.Driver;

//import javax.lang.model.util.ElementScanner6;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
//import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
//import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.DigitalInput;
//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMax.IdleMode;
//import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.ADXRS450_Gyro;

//new import
import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import edu.wpi.first.wpilibj.interfaces.Gyro;
//import edu.wpi.first.wpilibj.SPI;


public class Robot extends TimedRobot {
  //Definitions for the hardware. Change this if you change what stuff you have plugged in
  TalonSRX driveLeftA = new TalonSRX(3);
  TalonSRX driveLeftB = new TalonSRX(4);
  TalonSRX driveRightA = new TalonSRX(1);
  TalonSRX driveRightB = new TalonSRX(2);
  DriveTrain drivetrain = new DriveTrain(driveLeftA, driveLeftB, driveRightA, driveRightB);
  Constant constant = new Constant();
  
  
  DigitalInput downwardlimitswitch = new DigitalInput(0);
  DigitalInput upwardlimitswitch = new DigitalInput(1);
  double RightAdjust = .65;
  Timer m_timer = new Timer();
  TalonFX a = new TalonFX(5);
  Arm arm = new Arm(a);
  
  
  UsbCamera camera1;
  UsbCamera camera2;
  //private static final double kAngleSetpoint = 0.0;
	//private static final double kP = 0.005; // propotional turning constant
  

   


  // private final DifferentialDrive m_robotDrive = new DifferentialDrive(driveLeftA, driveRightA);
  Joystick operator = new Joystick(1);
  XboxController driver = new XboxController(0);

  //Constants for controlling the arm. consider tuning these for your particular robot
 /**  final double armHoldUp = 0.08;
  final double armHoldDown = 0.13;
  final double armTravel = 0.5;

  final double armTimeUp = 0.5;
  final double armTimeDown = 0.35;
  boolean startleft = false;
  boolean startRight = false;
  //Varibles needed for the code
  boolean armUp = true; //Arm initialized to up because that's how it would start a match
  boolean burstMode = false;
  double lastBurstTime = 0;
  DigitalInput downwardlimitswitch = new DigitalInput(0);
  DigitalInput upwardlimitswitch = new DigitalInput(1);
  boolean godown = false;
  boolean goup = false;
  double autoStart = 0;
  boolean goForAuto = false;
*/

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */




  @Override
  public void robotInit() {
    //Configure motors to turn correct direction. You may have to invert some of your motors
    //driveLeftA.setInverted(false);
    //driveLeftB.setInverted(false);
    //driveRightA.setInverted(false);
    //driveRightB.setInverted(true);
    // arm.setInverted(false);
    //SmartDashboard.putBoolean("right auton", startRight);
    //SmartDashboard.putBoolean("left auton", startleft);
    // configuring the b motor to follow the a motor
    //driveLeftB.follow(driveLeftA);
    //driveRightB.follow(driveRightA);

   //not sure if needed, may remove
    // driveRightA.configClearPositionOnLimitF(false, 0);
    // driveRightA.configClearPositionOnLimitR(false, 0);
    // driveRightA.configClearPositionOnQuadIdx(false, 0);
    // driveLeftA.configClearPositionOnLimitF(false, 0);
    // driveLeftA.configClearPositionOnLimitR(false, 0);
    // driveLeftA.configClearPositionOnQuadIdx(false, 0);
    // arm.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 10,15,1));
    // arm.configClearPositionOnLimitF(false, 0);
    // arm.configClearPositionOnLimitR(false, 0);
    // arm.configClearPositionOnQuadIdx(false, 0);
    // arm.setSelectedSensorPosition(0, 0, 0);
    // set to false to track position
    // set to true to stop tracking and reset to 0
    // tedious
    camera1 = CameraServer.startAutomaticCapture(0);
    camera2 = CameraServer.startAutomaticCapture(1);
    
    //add a thing on the dashboard to turn off auto if needed
   // SmartDashboard.putBoolean("Go For Auto", false);
    //goForAuto = SmartDashboard.getBoolean("Go For Auto", false);
  }

  @Override
  public void autonomousInit() {
    // reset and start timer
    m_timer.reset();
    m_timer.start();
    
  }
 
  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() { 
   
    //leftAuton();
    //rightAuton();
    //centerAuton();
    middleAuton();

  }

    /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    //could be a repeat and might be deleted
    // driveLeftB.follow(driveLeftA);
    // driveRightB.follow(driveRightA);

  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    double forward = 0;
    double turn = 0;
    // SmartDashboard.putNumber("left drive power", driveLeftA.getActiveTrajectoryVelocity());
    // SmartDashboard.putNumber("right drive power", driveRightA.getActiveTrajectoryVelocity());
    //SmartDashboard.putNumber("arm angle", arm.getSelectedSensorPosition());
    forward = -driver.getLeftY();
    turn = -driver.getRightX()*constant.speedMultiplier;
    
    double driveLeftPower = forward*constant.speedMultiplier - turn;
    double driveRightPower = forward*constant.speedMultiplier + turn;
    SmartDashboard.putBoolean("down switch", downwardlimitswitch.get());
    SmartDashboard.putBoolean("up switch", upwardlimitswitch.get());
    
    
    driveLeftA.set(ControlMode.PercentOutput, driveLeftPower);
    driveRightA.set(ControlMode.PercentOutput, driveRightPower);

    boolean toggle = false;
if(constant.godown){
  arm.armGoDown();
  if(downwardlimitswitch.get()==false){
    arm.armStop();
    constant.godown = false;
  }
}
if(constant.goup){
  arm.armGoUp();
  if(upwardlimitswitch.get()==false){
    arm.armStop();
    constant.goup = false;
  }
}
    //Intake controls
     if(operator.getRawButton(16)){ 
       //button 16 pressed arm down start intake
    // armDown();
    constant.godown = true;
     SmartDashboard.putString("arm position", "down");
    
     Intake.OhmNom();
     }
     if(operator.getRawButton(11)){
       //button 11 pressed arm up stop intake
    //armUp();
    constant.goup = true;
    SmartDashboard.putString("arm position", "up"); 
     }
    if(driver.getRightBumper() == true){
      constant.speedMultiplier = .6;
    }
    if(operator.getRawButton(12)){
        //button 12 pressed intake ball
        Intake.OhmNom();
    }else if(operator.getRawButton(constant.button13)){
      //button 13 pressed eject ball
      // fast shooter
        Intake.PewPew(Constant.shooterSpeed);
      }else{
        Intake.StopIntake();
      }
    // if(operator.getRawButton(15)){
    //     //button 15 pressed stop rollers
        
    //     intake.StopIntake();
    //   }
    
         //set to 0
      if(operator.getRawButton(14)){
        arm.armStop();
      }
      if(operator.getRawButton(7)){
        Intake.PewPew(Constant.shooterSpeed);
        // slow shooter
      }

    }

  @Override
  public void disabledInit() {
    //On disable turn off everything
    //done to solve issue with motors "remembering" previous setpoints after reenable
    driveLeftA.set(ControlMode.PercentOutput, 0);
    driveLeftB.set(ControlMode.PercentOutput, 0);
    driveRightA.set(ControlMode.PercentOutput, 0);
    driveRightB.set(ControlMode.PercentOutput, 0);
    //arm.set(ControlMode.Position,0);
    Intake.StopIntake();
   // SmartDashboard.putBoolean("down switch", downwardlimitswitch.get());
    //SmartDashboard.putBoolean("up switch", upwardlimitswitch.get());
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

  public void armUp(){
     
    SmartDashboard.putString("arm position", "up");
    while(arm.arm.getSelectedSensorPosition() < -0){ 
      double forward = -driver.getLeftY();
      double turn = -driver.getRightX()*Constant.speedMultiplier;
      //speedMultiplier = .2;
      //RightAdjust = .2;
      double driveLeftPower = forward*Constant.speedMultiplier - turn;
      double driveRightPower = forward*RightAdjust + turn;
     
      
      
      driveLeftA.set(ControlMode.PercentOutput, driveLeftPower);
      driveRightA.set(ControlMode.PercentOutput, driveRightPower);
    arm.arm.set(ControlMode.PercentOutput, .2); // yeserdy was set to .35
    }
  //  else{
    arm.arm.set(ControlMode.PercentOutput, 0);
  // }
}
  public void armDown(){
   // arm.set(ControlMode.Position, 0);
    SmartDashboard.putString("arm position", "down");
    while(arm.arm.getSelectedSensorPosition() > -85000){ 
     arm.arm.set(ControlMode.PercentOutput, -.2); // yesterday as set to -.4
     double forward = -driver.getLeftY();

     double turn = -driver.getRightX()*Constant.speedMultiplier;
     //speedMultiplier = .2;
      //RightAdjust = .2;
      
     double driveLeftPower = forward*Constant.speedMultiplier - turn;
     double driveRightPower = forward*RightAdjust + turn;
    
     
     
     driveLeftA.set(ControlMode.PercentOutput, driveLeftPower);
     driveRightA.set(ControlMode.PercentOutput, driveRightPower);
     }
  //  else{
     arm.arm.set(ControlMode.PercentOutput, 0);
  //   }
  }

  public void leftAuton(){
  //starting on the left side
   
  if (m_timer.get() < 0){
    driveForward(0);
  } else if(m_timer.get() > 0 & m_timer.get() < 1.5){
  driveForward(.3);
  }
  else if(m_timer.get() > 1.5 & m_timer.get() < 1.9){
   turnLeft(.3);
  }
  else if(m_timer.get() > 1.9 & m_timer.get() < 4.4){
   Intake.PewPew(Constant.shooterSpeed);
  }
  else if(m_timer.get() > 4.4 & m_timer.get() < 8.4){
   driveForward(-.4);
   }
  else if(m_timer.get() > 8.4);
   driveForward(0);
  }

  public void rightAuton(){
  //starting on right side
  if (m_timer.get() < 0){
    driveForward(0);
} else if(m_timer.get() > 0 & m_timer.get() < 1.5){
  driveForward(.3);
 }
 else if(m_timer.get() > 1.5 & m_timer.get() < 1.9){
   turnRight(.3);
 } 
 else if(m_timer.get() > 1.9 & m_timer.get() < 4.4){
   Intake.PewPew(Constant.shooterSpeed);
 }
 else if(m_timer.get() > 4.4 & m_timer.get() < 8.4){
   driveForward(-.4);
 }
 else if(m_timer.get() > 8.4);
   driveForward(0);
//else if(m_timer.get() > 5 & m_timer.get() < 9){
 //  turnLeft(.5);
////}
// else if(m_timer.get() > 9 & m_timer.get() < 11){
  // driveForward(.3);
 //}
}
  public void centerAuton(){
// turn center
if (m_timer.get() < 0){
  driveForward(0);
}
  else if(m_timer.get() > 0 & m_timer.get() < 2){
    Intake.PewPew(Constant.shooterSpeed);
}
  else if(m_timer.get() > 2 & m_timer.get() < 8){
    driveForward(-.3);
}
  else if(m_timer.get() > 8){
    driveForward(0);
  }
  
// else if(m_timer.get() > 4 & m_timer.get() < 9){
 //   turnRight(.4);
//}

}
  public void middleAuton(){
    if (m_timer.get() < 0){
      driveForward(0);
    }
      else if(m_timer.get() > 0 & m_timer.get() < 2){
        Intake.PewPew(Constant.shooterSpeed);;
    }
      else if(m_timer.get() > 2 & m_timer.get() < 6){
        driveForward(0);
    }
    else if(m_timer.get() > 6 & m_timer.get() < 12){
      driveForward(-.3);

    }
      else if(m_timer.get() > 12){
        driveForward(0);
  }

  // public void dumb ball(){
  // } 
}
}
