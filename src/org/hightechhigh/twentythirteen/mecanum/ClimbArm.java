/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hightechhigh.twentythirteen.mecanum;

import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author Josh
 */
public class ClimbArm {
    private Victor rotate_victor, chain_victor;
    public ClimbArm(int rotate, int chain){
        rotate_victor = new Victor(rotate);
        chain_victor = new Victor(chain);
    }
    public void setChain(float val){
        chain_victor.set(val);
    }
    public void setRotate(float val){
        rotate_victor.set(val);
    }
    
    
}
