/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hightechhigh.twentythirteen.mecanum;

import com.sun.squawk.microedition.io.FileConnection;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.microedition.io.Connector;

/**
 *
 * @author Josh
 */
public class Logger {
    DataOutputStream theFile;
    FileConnection fc;
    Driver dv;
    boolean printLn = false;
    public Logger(boolean printl){
        dv = new Driver();
        printLn = printl;
        try {
            fc = (FileConnection)Connector.open("file:///log.txt", Connector.WRITE);
            fc.create();
            theFile = fc.openDataOutputStream();
        } catch (Exception e) {
            System.err.println("Error. Could Not Open File.");
        }
        try {
            theFile.writeChars(dv.getPrintable());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void writeEntry(String log){
        try {
            theFile.writeChars("[" + dv.getTime() + "]" + log);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if(printLn){
            System.out.println(log);
        }
    }
    public void iterationLoop(){
        writeEntry("Robot OK. (Battery Voltage " + dv.getBatteryVoltage() + ")");
    }
}
