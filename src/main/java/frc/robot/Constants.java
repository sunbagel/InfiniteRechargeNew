/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static double wheelCircumference  = 19;
    public static double ticksPerRot =  2048;
    public static double distpertick = wheelCircumference/ticksPerRot;
    public static double talonTicksPerRot = ticksPerRot*4;
    public static double talonTickMultiplier = 4;

    //Speed Controller IDs (DON'T TOUCH UNLESS YOU KNOW WHAT YOU'RE DOING!!!)
    
    //Chassis
    public static final int leftSlave = 2, leftTalon = 1, rightSlave = 4, rightTalon =3;

    //arm
    public static final int ARM_SPARKMAX = 5;
    //wheels on the intake
    public static final int INTAKE_TALON = 9;
    //conveyor
    public static final int CONVEYOR_TALON = 8;
    
    
    public static final int SHOOTER_LEFT = 6;
    public static final int SHOOTER_RIGHT = 7;
    //undetermined so far (servo where?)
    public static final int SHOOTER_SERVO = 0;


    public static double lSpeedCoefficient = 0.5;
    public static double hSpeedCoefficient = 0.85;


}
