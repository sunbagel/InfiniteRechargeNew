/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Chassis extends SubsystemBase {
  /**
   * Creates a new Chassis.
   */

  TalonSRX right, left;
  VictorSPX rightSlave, leftSlave;
  

  public Chassis() {
    //init speed controllers
    right = new TalonSRX(Constants.rightTalon);
    left = new TalonSRX(Constants.leftTalon);
    rightSlave = new VictorSPX(Constants.rightSlave);
    leftSlave = new VictorSPX(Constants.leftSlave);

    //set brakemode
    right.setNeutralMode(NeutralMode.Brake);
    left.setNeutralMode(NeutralMode.Brake);
    rightSlave.setNeutralMode(NeutralMode.Brake);
    leftSlave.setNeutralMode(NeutralMode.Brake);

    //invert
    //may need to invert left motors instead.. depends on direction of bot
    right.setInverted(true);
    rightSlave.setInverted(true);

    //set slaves
    rightSlave.follow(right);
    leftSlave.follow(left);

    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }

  public void tankDrive(double left, double right){
    this.left.set(ControlMode.PercentOutput, left);
    this.right.set(ControlMode.PercentOutput, right);
  }

  public void stop(){
    left.set(ControlMode.PercentOutput, 0);
    right.set(ControlMode.PercentOutput, 0);
  }

}
