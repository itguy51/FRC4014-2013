/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hightechhigh.twentythirteen.mecanum;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author Josh
 */
public class ClimbArm {
    private Victor rotate_victor, chain_victor;
    private AnalogChannel rotationSensor;
    private DigitalInput topSwitch, bottomSwitch;
    private PIDController rotatePID;
    private double P_CONST, I_CONST, D_CONST;
    private double ARM_CONST = constants.BASE_STICK_IN_STALL;
    private double ARM_OUT_CONST = constants.BASE_STICK_OUT_STALL;
    private Relay hookRelay;
    private boolean override;
    public ClimbArm(int rotate, int chain, int sensorChannel){
        rotate_victor = new Victor(rotate);
        //rotate_victor.disable();
        chain_victor = new Victor(chain);
        topSwitch = new DigitalInput(2);
        bottomSwitch = new DigitalInput(1);
        rotationSensor = new AnalogChannel(sensorChannel);
        //rotatePID = new PIDController(constants.ARM_P, constants.ARM_I, constants.ARM_D, rotationSensor, rotate_victor);
        rotatePID = new PIDController(constants.ARM_P, constants.ARM_I, constants.ARM_D, rotationSensor, rotate_victor);
        rotatePID.disable();
        rotatePID.setInputRange(0.0, 5.0);
        rotatePID.setOutputRange(-0.65, 0.65);
        chain_victor.set(0);
        rotate_victor.set(0);
        hookRelay = new Relay(1);
        hookRelay.set(Relay.Value.kOff);
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
    public void setChain(double val, boolean over){
        if(!topSwitch.get() && val > 0 && !over){
            val = 0;
            //System.out.println("Top Swtich down, ignoring.");
        }else if(!bottomSwitch.get() && val < 0 && !over){
            val = 0;
        }
        if(over){
            val *= 0.1;
        }
        chain_victor.set(val);
    }
    public void setChain(double val){
        setChain(val, override);
    }
    public void setRotate(double val){
        System.out.println("CURRENT ROTATE: " + val);
        rotate_victor.set(val);
    }
    
    
    public void setHookForward(){
        hookRelay.set(Relay.Value.kReverse);
    }
    public void setHookBack(){
        hookRelay.set(Relay.Value.kForward);
    }
    public void stopHooks(){
        hookRelay.set(Relay.Value.kOff);
    }
    public void setOverride(boolean ovr){
        override = ovr;
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
    public void incrementArmIn(){
        ARM_CONST += 0.005;
        System.out.println("IN CONST UP: " + ARM_CONST);
    }
    public void decrementArmIn(){
        ARM_CONST -= 0.005;
        System.out.println("IN CONST DOWN: " + ARM_CONST);
    }
    public void incrementArmOut(){
        ARM_OUT_CONST += 0.005;
        System.out.println("OUT CONST UP: " + ARM_CONST);
    }
    public void decrementArmOut(){
        ARM_OUT_CONST -= 0.005;
        System.out.println("OUT CONST DOWN: " + ARM_CONST);
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
   
    public void automaticOutRun(){
        setRotate(0.2);
        Timer.delay(700);
        setRotate(ARM_OUT_CONST);
    }
    public void automaticInRun(){
        setRotate(-0.2);
        Timer.delay(700);
        setRotate(ARM_CONST);
    }
    public void step1(){
        while(bottomSwitch.get()){
            setChain(-0.70);
            Timer.delay(0.010);
        }
        setChain(0.0);
    }
}


