/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hightechhigh.twentythirteen.mecanum;

/**
 *
 * @author Josh
 */
public class constants {
    static final int LEFT_TOP_JAG = 4;//1;
    static final int RIGHT_TOP_JAG = 3;//2;
    static final int RIGHT_BOTTOM_JAG = 6;//2;
    static final int LEFT_BOTTOM_JAG = 7;//1;
    static final double DRIVE_SENSITIVITY = 0.005;
    static final double DEADBAND_VAL = 0.15;
    static final int ROTATE_VICTOR = 1;
    static final int CHAIN_VICTOR = 2;
    static final double ARM_BAND = 0.6;
    static final double CLIMB_BAND = 0.8;
    static final int MODIFIER_DEADBAND = 4;
    static final double ARM_P = 0.0;
    static final double ARM_I = 0.0;
    static final double ARM_D = 0.0;
    static final int ARM_SENSOR = 1;
    static final double BASE_STICK_IN_STALL = 0.28014843749999985;
    static final double BASE_STICK_OUT_STALL = -0.221;
    
    
    
    
    
    
    //Arm Stick Buttons
    static final int MANUAL_ARM_ROTATE_BUTTON = 1;
    static final int MANUAL_CLAW_RUN_BUTTON = 2;
    static final int HOLD_IN_BUTTON = 12;
    static final int HOLD_OUT_BUTTON = 11;
    static final int INCREMENT_HOLD_BUTTON = 8;
    static final int DECREMENT_HOLD_BUTTON = 7;
    static final int SET_CENTER_VALUE_BUTTON = 3;
    static final int PRINT_POWER_BUTTON = 6;
}
