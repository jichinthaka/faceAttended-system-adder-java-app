/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance_system_adder.cv;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.*;

/**
 *
 * @author Imesh Chinthaka
 */
public class imageShow {

    public imageShow() {
    }
    static{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    
    
    public static void main(String[] args) {
//        Imgcodecs ic = new Imgcodecs();
//        Mat imread = ic.imread("111.jpg",1);
        image img = new image();
        
        
    }
    
    
}
