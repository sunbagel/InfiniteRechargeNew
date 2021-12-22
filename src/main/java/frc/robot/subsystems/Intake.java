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

public class Intake extends SubsystemBase {
  /**
   * Creates a new Intake.
   */

  Spark intake;
  public Intake() {
    intake = new Spark(Constants.INTAKE_TALON);
    //Done to make sure intake and conveyor spin in different directions
    intake.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void pull(){
    intake.set(0.5);
  }

  public void eject(){
    intake.set(-0.5);
  }

  public void stop(){
    intake.stopMotor();
  }
}
