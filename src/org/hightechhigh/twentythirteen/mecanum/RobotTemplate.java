/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.hightechhigh.twentythirteen.mecanum;


import edu.wpi.first.wpilibj.CANJaguar;
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
    private Joystick drive, armstick;
    private RobotDrive rdrive;
    private CANJaguar topLeft, botLeft, topRight, botRight;
    
    //private double xv, yv, tv;
    private double mag, tv;
    private LimitSwitch ls_bottom, ls_top;
    private ClimbArm arm;
    private double hookVal;
    //private Dashboard dash;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        //dash = new Dashboard();
        //dash.
        /*try{
        botRight = new CANJaguar(constants.RIGHT_BOTTOM_JAG);
        botLeft = new CANJaguar(constants.LEFT_BOTTOM_JAG);
        topRight = new CANJaguar(constants.RIGHT_TOP_JAG);
        topLeft = new CANJaguar(constants.LEFT_TOP_JAG);
        }catch(Exception e){
            System.err.println(e);
        }*/
        System.out.println("*pulls out knife*");
        getWatchdog().setEnabled(false);
        System.out.println("*kills watchdog*");
        drive = new Joystick(1);
        armstick = new Joystick(2);
        rdrive = new RobotDrive(constants.LEFT_TOP_JAG, constants.LEFT_BOTTOM_JAG, constants.RIGHT_TOP_JAG, constants.RIGHT_BOTTOM_JAG);
        //rdrive = new RobotDrive(topLeft, botLeft, topRight, botRight);

        rdrive.setSensitivity(constants.DRIVE_SENSITIVITY);
        ls_bottom = new LimitSwitch(1);
        ls_top = new LimitSwitch(2);
        arm = new ClimbArm(constants.ROTATE_VICTOR, constants.CHAIN_VICTOR, constants.ARM_SENSOR);
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
        /*tv = drive.getTwist();
        if(Math.abs(tv) < constants.DEADBAND_VAL){
            tv = 0;
        }else{
            tv *= Math.abs(tv);
            tv *= 0.5;
        }*/
        if(ls_top.isClosed()){
            //Limit switch on port 1 would make this go.
        }else{
            //Limit switch on port 1 would make this dinner.
        }
        if(ls_bottom.isClosed()){
            //Limit switch on port 2 would make this go.
        }else{
            //Limit switch on port 2 would make this dinner.
        }
        mag = drive.getMagnitude();
        if(Math.abs(mag) < constants.DEADBAND_VAL){
            mag = 0;
        }else{
            mag *= Math.abs(mag);
        }
        mag *= 0.5;
        
        if(drive.getRawButton(1)){
            rdrive.mecanumDrive_Polar(mag, (float)drive.getDirectionDegrees(), 0);
        }else{
            //rdrive.arcadeDrive(drive);
            rdrive.arcadeDrive(drive.getX()/2, drive.getY()/2);
        }
        
        
        
        
        
        //Arm Code
        
        if(armstick.getRawButton(constants.INCREMENT_HOLD_BUTTON)){
           arm.incrementArm();
        }else if(armstick.getRawButton(constants.DECREMENT_HOLD_BUTTON)){
            arm.decrementArm();
        }else if(armstick.getRawButton(constants.PRINT_POWER_BUTTON)){
            arm.printPower();
        }
        
        double center = 0;
        double rotVal = 0;
        
        if(armstick.getRawButton(constants.MANUAL_ARM_ROTATE_BUTTON))
        {
            rotVal = (-armstick.getRawAxis(1) * Math.abs(armstick.getRawAxis(1)) * constants.ARM_BAND);
        }
        else
        {
            if(armstick.getRawButton(constants.HOLD_IN_BUTTON)){
                center = arm.getArmInConst();
            }else if(armstick.getRawButton(constants.HOLD_OUT_BUTTON)){
                center = arm.getArmOutConst();
            }
        }
         if(armstick.getRawButton(constants.SET_CENTER_VALUE_BUTTON))
        {
            rotVal = (-armstick.getRawAxis(1) * Math.abs(armstick.getRawAxis(1)) * constants.ARM_BAND);
        }
         arm.setRotate(normalize(rotVal + center));
        
        
        /* <Old Stuff>
        
        //if(armstick.getMagnitude() >= constants.DEADBAND_VAL * constants.MODIFIER_DEADBAND){
            if(armstick.getRawButton(constants.MANUAL_ARM_ROTATE_BUTTON)){
                rotVal = (-armstick.getRawAxis(1) * Math.abs(armstick.getRawAxis(1)) * constants.ARM_BAND);
                if(armstick.getRawButton(constants.HOLD_IN_BUTTON) && rotVal <= arm.getArmInConst()){
                    arm.fireArm();
                }else{
                    arm.setRotate(rotVal);
                }
                System.out.println("ROTATE VALUE: " + rotVal);
                
                
            }
            else if(armstick.getRawButton(constants.HOLD_OUT_BUTTON)){
                //arm.fireArm();
                arm.setRotate(constants.BASE_STICK_OUT_STALL);
            }else if(armstick.getRawButton(12)){
               
                arm.fireArm();
                //arm.setRotate(constants.BASE_STICK_OUT_STALL);
            }else {
                arm.setRotate(0);
                //System.out.println("ROTATE STOPPED (RELEASED)");
            }
            
            if(armstick.getRawButton(2)){
                hookVal = (armstick.getRawAxis(2) * -1.0 * constants.CLIMB_BAND);
                arm.setChain(hookVal);
                //System.out.println("HOOK VALUE: " + hookVal);
            }else{
                arm.setChain(0);
                //System.out.println("HOOK STOPPED (RELEASED)");
            }
        /*}else{
            arm.setRotate(0);
            arm.setChain(0);
            //System.out.println("ARM STOPPED");
        }*/
        /*if(armstick.getRawButton(7)){
                arm.decrementP();
        }else if(armstick.getRawButton(8)){
                arm.incrementP();
        }else if(armstick.getRawButton(9)){
                arm.decrementI();
        }else if(armstick.getRawButton(10)){
                arm.incrementI();
        }else if(armstick.getRawButton(11)){
                arm.decrementD();
        }else if(armstick.getRawButton(12)){
                arm.incrementD();
        }else if(armstick.getRawButton(5)){
                arm.printPID();
        }

        if(armstick.getRawButton(1)){
                arm.setPIDState(true);
        }else{
                arm.setPIDState(false);
        }*/
        
        
        ///Arm Code
        
        
        
        
        
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
    
    static double normalize(double input)
    {
        if(input >= 1){
            return 1;
        }else if(input <= -1){
            return -1;
        }else{
            return input;
        }
    }
    
}
