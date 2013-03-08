/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hightechhigh.twentythirteen.mecanum;

import edu.wpi.first.wpilibj.DriverStation;

/**
 *
 * @author Josh
 */
public class Driver extends DriverStation{
    public String getPrintable(){
        return getAlliance() + " Alliance";
    }
    public double getTime(){
        return getMatchTime();
    }
}
