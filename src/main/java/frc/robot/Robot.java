



package frc.robot;

import javax.lang.model.util.ElementScanner6;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

//new import
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.SPI;

public class Robot extends TimedRobot {
  //Definitions for the hardware. Change this if you change what stuff you have plugged in
  TalonSRX driveLeftA = new TalonSRX(3);
  TalonSRX driveLeftB = new TalonSRX(4);
  TalonSRX driveRightA = new TalonSRX(1);
  TalonSRX driveRightB = new TalonSRX(2);
  double speedMultiplier = 0.7;
  double RightAdjust = 0.73;
  Timer m_timer = new Timer();
  TalonFX arm = new TalonFX(5);
  VictorSPX intake = new VictorSPX(6);
  private static final double kAngleSetpoint = 0.0;
	private static final double kP = 0.005; // propotional turning constant

   


  // private final DifferentialDrive m_robotDrive = new DifferentialDrive(driveLeftA, driveRightA);
  Joystick operator = new Joystick(1);
  XboxController driver = new XboxController(0);

  //Constants for controlling the arm. consider tuning these for your particular robot
  final double armHoldUp = 0.08;
  final double armHoldDown = 0.13;
  final double armTravel = 0.5;

  final double armTimeUp = 0.5;
  final double armTimeDown = 0.35;

  //Varibles needed for the code
  boolean armUp = true; //Arm initialized to up because that's how it would start a match
  boolean burstMode = false;
  double lastBurstTime = 0;

  double autoStart = 0;
  boolean goForAuto = false;


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    //Configure motors to turn correct direction. You may have to invert some of your motors
    driveLeftA.setInverted(false);
    driveLeftB.setInverted(false);
    driveRightA.setInverted(false);
    driveRightB.setInverted(true);
    
    driveLeftB.follow(driveLeftA);
    driveRightB.follow(driveRightA);
    arm.setInverted(false);
   // arm.setIdleMode(IdleMode.kBrake);
    //arm.burnFlash();
    driveRightA.configClearPositionOnLimitF(false, 0);
    driveRightA.configClearPositionOnLimitR(false, 0);
    driveRightA.configClearPositionOnQuadIdx(false, 0);
    driveLeftA.configClearPositionOnLimitF(false, 0);
    driveLeftA.configClearPositionOnLimitR(false, 0);
    driveLeftA.configClearPositionOnQuadIdx(false, 0);
    arm.configClearPositionOnLimitF(false, 0);
    arm.configClearPositionOnLimitR(false, 0);
    arm.configClearPositionOnQuadIdx(false, 0);
    // set to false to track position
    // set to true to stop tracking and reset to 0
    // tedious
    
    //add a thing on the dashboard to turn off auto if needed
    SmartDashboard.putBoolean("Go For Auto", false);
    goForAuto = SmartDashboard.getBoolean("Go For Auto", false);
  }

  @Override
  public void autonomousInit() {
    
    m_timer.reset();
    m_timer.start();
    driveLeftA.setSelectedSensorPosition(0);
    driveRightA.setSelectedSensorPosition(0);
    arm.setSelectedSensorPosition(0);
  }
 
  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() { 
    /**drive to goal
     release ball
    backup 
     180
     intake down 
    driveLeftA.configClearPositionOnLimitF(false, 0);
    driveRightA.configClearPositionOnLimitF(false, 0);
    if (m_timer.get() < 5){
      driveForward(0);
  } else if(m_timer.get() > 5 & m_timer.get() < 6.2){
    driveForward(.3);
   }
   else if(m_timer.get() > 6.2 & m_timer.get() < 6.5){
     turnRight(.3);
   }
   else if(m_timer.get() > 6.5 & m_timer.get() < 9){
     driveForward(0);
   }
   else if(m_timer.get() > 9 & m_timer.get() < 10){
     driveForward(-.4);
   }
  else if(m_timer.get() > 10 & m_timer.get() < 12){
     turnLeft(.4);
  }
   else if(m_timer.get() > 6 & m_timer.get() < 9){
     driveForward(.3);
   }
   else{
    driveForward(0);
  }
   if(m_timer.get() < 5){
    driveForward(0);
  }
  else if(m_timer.get() > 6.2 & m_timer.get() < 6.5){
    turnLeft(.3);
  }
  else if(m_timer.get() > 6.5 & m_timer.get() < 9){
    driveForward(0);
  }
  else if(m_timer.get() > 9 & m_timer.get() < 10){
    driveForward(-.4);
  }
 else if(m_timer.get() > 10 & m_timer.get() < 12){
    turnLeft(.4);
 }
  else if(m_timer.get() > 6 & m_timer.get() < 9){
    driveForward(.3);
  }
  else{
   driveForward(0);
    SmartDashboard.putNumber("left pos", driveLeftA.getSelectedSensorPosition());
    SmartDashboard.putNumber("right pos", driveRightA.getSelectedSensorPosition());*/
  }
   
   
  private void driveLeftA(double d) {
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    driveLeftB.follow(driveLeftA);
    driveRightB.follow(driveRightA);
  //  arm.set(ControlMode.PercentOutput, .8);
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    //Set up arcade steer
    // double forward = -operator.getRawAxis(1);
    // double turn = -operator.getRawAxis(2);
		// Invert the direction of the turn if we are going backwards
		//turningValue = Math.copySign(turningValue, driver.getLeftY());
    double forward = 0;
    double turn = 0;
  
    // if(driver.getLeftX() < .1 && driver.getLeftX() > -.1){
      // turn = 0;
    // }
    // else if(driver.getLeftY() <.1 && driver.getLeftY() > -.1){
      // forward = 0;
    // }
    // else{
    forward = -driver.getLeftY();
    turn = -driver.getRightX()*speedMultiplier;
  // }
    
    double driveLeftPower = forward*speedMultiplier - turn;
    double driveRightPower = forward*RightAdjust + turn;
   
    
    intake.set(ControlMode.PercentOutput, .3);
    driveLeftA.set(ControlMode.PercentOutput, driveLeftPower);
    //driveLeftB.set(ControlMode.PercentOutput, driveLeftPower);
    driveRightA.set(ControlMode.PercentOutput, driveRightPower);
    // driveRightB.set(ControlMode.PercentOutput, driveRightPower);

    //Intake controls
     if(operator.getRawButton(11)){
    //  if(driver.getAButton()){
      arm.set(ControlMode.Position, 0);
      //arm.set(ControlMode.PercentOutput, .2);
      //armUp = false;
      System.out.println("11");
    }
    
    else if(operator.getRawButton(10)){
      //  if(driver.getAButton()){
        
        arm.set(ControlMode.Position, 10);
        System.out.println("10");
        //armUp = false;
      }
      else{
        arm.set(ControlMode.Position, 0);
      }
     //else if(operator.getRawButton(13)){
    //  else if(driver.getBButton()){
      //intake.set(0.3);
    //}
    //else{
      //intake.set(0.3);
    //}

    //Arm Controls
    // if(armUp){
    //   if(Timer.getFPGATimestamp() - lastBurstTime < armTimeUp){
    //     arm.set(ControlMode.Position,armHoldUp);
    //   }
    //   else{
    //     arm.set(ControlMode.Position,armHoldUp);
    //   }
    // }
    // else{
    //   if(Timer.getFPGATimestamp() - lastBurstTime < armTimeDown){
    //     arm.set(ControlMode.Position,-armTravel);
    //   }
    //   else{
    //     arm.set(ControlMode.Position,-armHoldDown);
    //   }
    // }
  
    //  if(operator.getRawButtonPressed(14) && !armUp){
    // //  if(driver.getYButton()){
    //   lastBurstTime = Timer.getFPGATimestamp();
    //   armUp = true;
    // }
    //  else if(operator.getRawButtonPressed(16) && armUp){
    // //  else if(driver.getXButton()){
    //   lastBurstTime = Timer.getFPGATimestamp();
    //   armUp = false;
    // }  

  }

  @Override
  public void disabledInit() {
    //On disable turn off everything
    //done to solve issue with motors "remembering" previous setpoints after reenable
    driveLeftA.set(ControlMode.PercentOutput, 0);
    driveLeftB.set(ControlMode.PercentOutput, 0);
    driveRightA.set(ControlMode.PercentOutput, 0);
    driveRightB.set(ControlMode.PercentOutput, 0);
    arm.set(ControlMode.Position,0);
    intake.set(ControlMode.PercentOutput, 0);
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
