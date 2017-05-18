/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance_system_adder;


import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import org.opencv.core.*;
import org.opencv.utils.*;
import org.opencv.calib3d.*;
import org.opencv.video.Video;
import org.opencv.videoio.*;
import org.opencv.core.Core;
import org.opencv.face.*;




/**
 *
 * @author Imesh Chinthaka
 */
public class Attendance_system_adder {
    
    static {
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        mainFrame mf = new mainFrame();
        mf.setDefaultCloseOperation(mf.EXIT_ON_CLOSE);
        mf.setVisible(true);
        
//        video v = new video();
//        v.displayVideo(0);
//        LBPHFaceRecognizer LBPHFaceRecognizer = Face.createLBPHFaceRecognizer();
//        LBPHFaceRecognizer.train(src, labels);
        
        
    }
    
}
