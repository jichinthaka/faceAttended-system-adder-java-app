/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance_system_adder;

import java.util.Observable;

/**
 *
 * @author Imesh Chinthaka
 */
public class videoController extends Observable{
//    videoControllerFrame vcf;

    public videoController() {
//        vcf = new videoControllerFrame();
//        vcf.setVisible(true);
//        
    }
    
    public void capturePhoto(){
        setChanged();
        notifyObservers(1);
    }
    
    public void captureFace(){
        setChanged();
        notifyObservers(2);
    }
    
    public void closeVideo(){
        setChanged();
        notifyObservers(3);
    }
    
    
}
