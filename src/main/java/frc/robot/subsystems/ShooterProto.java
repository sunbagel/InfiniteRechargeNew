/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ShooterProto extends SubsystemBase {
  /**
   * Creates a new ShooterProto.
   */
  CANSparkMax leftRoller;
  CANSparkMax rightRoller;
  Servo safetyServo;
  double hThreshold = 1; //change as needed, not used yet because idk
  double lThreshold = 0.5; //same as above
  double servoOn = 1;
  double servoOff = 0;
  
  public ShooterProto() {
    leftRoller = new CANSparkMax(Constants.SHOOTER_LEFT, MotorType.kBrushed);
    rightRoller = new CANSparkMax(Constants.SHOOTER_RIGHT, MotorType.kBrushed);
    leftRoller.setInverted(true);
    rightRoller.setInverted(true);
    // safetyServo = new Servo(Constants.SHOOTER_SERVO);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    //Safety Mechanism, will engage when wheels are lower than spinup
    //Manual disengage also possible for if jams occur

    //If manual disengage pressed
    // if (RobotContainer.safetyDisengageButton.get()){
    //   safetyServo.set(servoOff);
    // }
    // //If spun up properly
    // else if (lAtSpeed() && rAtSpeed()){
    //   safetyServo.set(servoOff);
    // }
    // //None are true: Safety engaged
    // else{
    //   safetyServo.set(servoOn);
    // }
    
  }

  //voltage may be negative, idk lets find out when we test
  // boolean lAtSpeed(){
  //   if (RobotContainer.getShooterButton()){
  //     return leftRoller.getAppliedOutput()>=0.95;
  //   }
  //   else if (RobotContainer.getSlowShooterButton()){
  //     return leftRoller.getAppliedOutput()>=0.45;
  //   }
  //   else{
  //     return false;
  //   }
  // }

  // boolean rAtSpeed(){
  //   if (RobotContainer.getShooterButton()){
  //     return rightRoller.getAppliedOutput()>=0.95;
  //   }
  //   else if (RobotContainer.getSlowShooterButton()){
  //     return rightRoller.getAppliedOutput()>=0.45;
  //   }
  //   else{
  //     return false;
  //   }
  // }

  public void shoot(double val){
    leftRoller.set(val);
    rightRoller.set(val);
  }

  public void stop(){
    leftRoller.set(0);
    rightRoller.set(0);
  }
}
