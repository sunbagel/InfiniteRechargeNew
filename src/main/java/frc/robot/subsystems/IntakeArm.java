/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeArm extends SubsystemBase {
  /**
   * Creates a new IntakeArm.
   */
  CANSparkMax intakeArm;
  DigitalInput stopSwitch;
  Counter counter;
  public IntakeArm() {
    intakeArm = new CANSparkMax(Constants.ARM_SPARKMAX, MotorType.kBrushed);
    // stopSwitch = new DigitalInput(1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void drop(){
    intakeArm.set(1);
  }

  public void stop(){
    intakeArm.stopMotor();
  }

  public void lift(){
    intakeArm.set(-1);
  }

  public boolean isSwitchSet(){
    return stopSwitch.get();
  }
}
