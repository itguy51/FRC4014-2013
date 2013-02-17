/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hightechhigh.twentythirteen.mecanum;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author Josh
 */
public class ClimbArm {
    private Victor rotate_victor, chain_victor;
    private AnalogChannel rotationSensor;
    private PIDController rotatePID;
    private double P_CONST, I_CONST, D_CONST;
    private double ARM_CONST = constants.BASE_STICK_IN_STALL;
    private double ARM_OUT_CONST = constants.BASE_STICK_OUT_STALL;
    public ClimbArm(int rotate, int chain, int sensorChannel){
        rotate_victor = new Victor(rotate);
        chain_victor = new Victor(chain);
        rotationSensor = new AnalogChannel(sensorChannel);
        //rotatePID = new PIDController(constants.ARM_P, constants.ARM_I, constants.ARM_D, rotationSensor, rotate_victor);
        rotatePID = new PIDController(constants.ARM_P, constants.ARM_I, constants.ARM_D, rotationSensor, rotate_victor);
        rotatePID.disable();
        rotatePID.setInputRange(0.0, 5.0);
        rotatePID.setOutputRange(-0.65, 0.65);
        chain_victor.set(0);
        rotate_victor.set(0);
    }
    public double getSensorVoltage(){
        return rotationSensor.getVoltage();
    }
    public void setPIDState(boolean state){
        if(state && !rotatePID.isEnable()){
            rotatePID.enable();
        }else if(!state && rotatePID.isEnable()){
            rotatePID.disable();
        }
    }
    public void setChain(double val){
        chain_victor.set(val);
    }
    public void setRotate(double val){
        rotate_victor.set(val);
    }
    
    
    
    
    //PID stuff
    public void incrementP(){
            P_CONST += 0.001;
            invokeReload();
    }
    public void incrementI(){
            I_CONST += 0.0001;
            invokeReload();
    }
    public void incrementD(){
            D_CONST += 0.001;
            invokeReload();
    }
    public void decrementP(){
            P_CONST -= 0.001;
            invokeReload();
    }
    public void decrementI(){
            I_CONST -= 0.0001;
            invokeReload();
    }
    public void decrementD(){
            D_CONST -= 0.001;
            invokeReload();
    }
    public void incrementArm(){
        ARM_CONST += 0.001;
        System.out.println("CONST UP: " + ARM_CONST);
    }
    public void decrementArm(){
        ARM_CONST -= 0.001;
        System.out.println("CONST DOWN: " + ARM_CONST);
    }
    public double getArmInConst(){
        return ARM_CONST;
    }
    public double getArmOutConst(){
        return ARM_OUT_CONST;
    }
    public void fireArm(){
        rotate_victor.set(ARM_CONST);
        System.out.println("CONST FIRED: " + ARM_CONST);
    }
    public void printPower(){
        System.out.println("POWER: "  + ARM_CONST);
    }
    public void invokeReload(){
        rotatePID.setPID(P_CONST, I_CONST, D_CONST);
    }
    public void printPID(){
            System.out.println("P: " + P_CONST);
            System.out.println("I: " + I_CONST);
            System.out.println("D: " + D_CONST);
            System.out.println("V: " + getSensorVoltage());
    }
   
    
}
