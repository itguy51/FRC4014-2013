/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hightechhigh.twentythirteen.mecanum;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author Josh
 */
public class ClimbArm {
    private Victor rotate_victor, chain_victor;
    private AnalogChannel rotationSensor;
    public ClimbArm(int rotate, int chain, int sensorChannel){
        rotate_victor = new Victor(rotate);
        chain_victor = new Victor(chain);
        chain_victor.set(0);
        rotate_victor.set(0);
    }
    public void setChain(double val){
        chain_victor.set(val);
    }
    public void setRotate(double val){
        rotate_victor.set(val);
    }
   
    
}
