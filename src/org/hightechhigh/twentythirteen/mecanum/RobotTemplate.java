/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.hightechhigh.twentythirteen.mecanum;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {
    private Joystick drive;
    private RobotDrive rdrive;
    //private double xv, yv, tv;
    private double mag, tv;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        System.out.println("cRIO Ready.");
        drive = new Joystick(1);
        rdrive = new RobotDrive(constants.LEFT_TOP_JAG, constants.LEFT_BOTTOM_JAG, constants.RIGHT_TOP_JAG, constants.RIGHT_BOTTOM_JAG);
        rdrive.setSensitivity(constants.DRIVE_SENSITIVITY);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
       
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        /*if(drive.getRawButton(1)){
            System.out.println("X: " + drive.getX());
            System.out.println("Y: " + (drive.getY() * -1.0));
            System.out.println("ROT: " + drive.getTwist());
        }*/
        tv = drive.getTwist();
        if(Math.abs(tv) < constants.DEADBAND_VAL){
            tv = 0;
        }else{
            tv *= Math.abs(tv);
            tv *= 0.5;
        }
        if(drive.getRawButton(1)){
            System.out.println("X: " + drive.getX());
            System.out.println("Y: " + (drive.getY() * -1.0));
            System.out.println("ROT: " + drive.getTwist());
        }
        mag = drive.getMagnitude();
        if(Math.abs(mag) < constants.DEADBAND_VAL){
            mag = 0;
        }else{
            mag *= Math.abs(mag);
        }
        rdrive.mecanumDrive_Polar(mag, (float)drive.getDirectionDegrees(), tv);
        /*xv = drive.getX();
        if(Math.abs(xv) < constants.DEADBAND_VAL){
            xv = 0;
        }else{
            xv *= Math.abs(xv);
        }
        yv = drive.getY() * -1.0;
        if(Math.abs(yv) < constants.DEADBAND_VAL){
            yv = 0;
        }else{
            yv *= Math.abs(yv);
        }
        if(Math.abs(tv) < constants.DEADBAND_VAL){
            tv = 0;
        }else{
            tv *= Math.abs(tv);
        }
        rdrive.mecanumDrive_Cartesian(xv, yv, tv, 0);*/
        Timer.delay(0.01);
    }
    
}
