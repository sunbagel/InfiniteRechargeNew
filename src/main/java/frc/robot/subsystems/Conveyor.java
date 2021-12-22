/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Conveyor extends SubsystemBase {
  /**
   * Creates a new Conveyor.
   */

  Spark conveyor;
  
  public Conveyor() {
    conveyor = new Spark(Constants.CONVEYOR_TALON);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void pull(){
    conveyor.set(0.5);
  }

  public void eject(){
    conveyor.set(-0.5);
  }

  public void stop(){
    conveyor.stopMotor();
  }
}
