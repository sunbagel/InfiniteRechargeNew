/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

//idk what this is
/*import javax.print.attribute.standard.JobHoldUntil;*/

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.IntakeBall;
import frc.robot.commands.MoveArm;
import frc.robot.commands.ShootBall;
import frc.robot.commands.StartDriving;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeArm;
import frc.robot.subsystems.ShooterProto;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static Chassis chassis;
  public static ShooterProto shooter;
  public static IntakeArm arm;
  public static Intake intake;
  public static Conveyor conveyor;

  public static XboxController xbox;
  public static Joystick stick;
  public static JoystickButton intakeButton;
  public static JoystickButton ejectButton;
  public static JoystickButton shooterButton;
  public static JoystickButton slowShooterButton;
  public static JoystickButton dropArmButton;
  public static JoystickButton liftArmButton;
  public static JoystickButton safetyDisengageButton;

  public static StartDriving startDriving;




  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    //subsystems
    chassis = new Chassis();
    shooter = new ShooterProto();
    arm = new IntakeArm();
    intake = new Intake();
    conveyor = new Conveyor();

    // Configure the button bindings
    configureButtonBindings();

    //commands
    conveyor.setDefaultCommand(new IntakeBall());
    chassis.setDefaultCommand(new StartDriving());
    shooter.setDefaultCommand(new ShootBall());
    intake.setDefaultCommand(new IntakeBall());
    arm.setDefaultCommand(new MoveArm());
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    xbox = new XboxController(0);
    stick = new Joystick(1);

    //trigger
    shooterButton = new JoystickButton(stick, 1);
    //thumb
    slowShooterButton = new JoystickButton(stick, 2);

    //middle left
    ejectButton = new JoystickButton(stick, 3);
    //middle right
    intakeButton = new JoystickButton(stick, 4);
    //outer left
    dropArmButton = new JoystickButton(stick, 5);
    //outer right
    liftArmButton = new JoystickButton(stick, 6);
    //no idea
    safetyDisengageButton = new JoystickButton(stick, 7); //to change as specified by Ethan
  }

  public static double getYLeft(){
    double kleft = xbox.getY(Hand.kLeft);
    if(Math.abs(kleft) <= 0.1){
      return 0;
    } else {
      return kleft*Math.abs(kleft); //Math.abs to preserve sign
    }
  }

  public static double getYRight(){
    double kright = xbox.getY(Hand.kRight);
    if(Math.abs(kright) <= 0.1){
      return 0;
    } else {
      return kright*Math.abs(kright); //Math.abs to preserve sign
    }
  }

  public static double getRightTrigger(){
    return xbox.getTriggerAxis(Hand.kRight);
  }
  public static double getLeftTrigger(){
    return xbox.getTriggerAxis(Hand.kLeft);
  }

  public static double getStickY(){  //used for ball shooter
    if(Math.abs(stick.getY()) <= 0.1){
      return 0;
    } else {
      return stick.getY();
    }
  }

  public static boolean getShooterButton(){
    return shooterButton.get();
  }

  public static boolean getSlowShooterButton(){
    return slowShooterButton.get();
  }

  public static boolean getEjectButton(){
    return ejectButton.get();
  }
  
  public static boolean getIntakeButton(){
    return intakeButton.get();
  }

  public static boolean getDropArmButton(){
    return dropArmButton.get();
  }

  public static boolean getLiftArmButton(){
    return liftArmButton.get();
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return startDriving;
  }
    // An ExampleCommand will run in autonomous
    
  //}
}
