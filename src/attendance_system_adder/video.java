/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance_system_adder;

import attendance_system_adder.cv.image;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opencv.calib3d.*;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.*;


/**
 *
 * @author Imesh Chinthaka
 */
public class video implements Observer {

    VideoCapture vc;
    Mat mat;
    Mat grayMat;
    Mat faceMat;
    image img;
    BufferedImage Mat2BufferedImage;
    videoControllerFrame vdf;
    int captureImgCount = 1;
    String imgPath;
    messageBox mb;
    boolean isdisplayClose;

    public video() {
        vdf = new videoControllerFrame();
        vdf.setDefaultCloseOperation(vdf.DISPOSE_ON_CLOSE);
        vdf.addObserver(this);
    }

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public synchronized void displayVideo(int index) {
        isdisplayClose = false;
        if (vc == null) {
            vc = new VideoCapture(index);
        }
        if (img == null) {
            img = new image();
        }
        if (mat == null) {
            mat = new Mat();
        }

        vdf.setVisible(true);

        while (true) {
            if (isdisplayClose) {
                img.closeDisplayFrame();
                vc.release();
                break;
            }
            vc.read(mat);
            grayMat = img.RGBtoGRAY(mat);
            Mat2BufferedImage = img.Mat2BufferedImage(grayMat);
            img.displayImage(Mat2BufferedImage);

        }
    }

    public void faceDetctDisplayVideo(int index) {
        isdisplayClose = false;
        if (vc == null) {
            vc = new VideoCapture(index);
        }
        if (img == null) {
            img = new image();
        }
        if (mat == null) {
            mat = new Mat();
        }

        vdf.setVisible(true);

        while (true) {
            if (isdisplayClose) {
                img.closeDisplayFrame();
                vc.release();
                break;
            }
            vc.read(mat);
            grayMat = img.RGBtoGRAY(mat);
//            Mat2BufferedImage= img.Mat2BufferedImage(grayMat);
            img.faceDetctDisplayImage(grayMat);

//            try {
//                this.sleep(50);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(video.class.getName()).log(Level.SEVERE, null, ex);
//            }

        }
    }

    public void captureImageAndSave() {

        img.saveImage(imgPath + "\\" + Integer.toString(captureImgCount) + ".jpg", grayMat);
        captureImgCount += 1;
    }

    public void captureFaceAndSave() {
        faceMat = img.getFaceDetec(grayMat);
        if (faceMat == null) {
            mb = new messageBox("There is no face detected");
            mb.setVisible(true);
            return;
        }
        img.saveImage(imgPath + "\\" + Integer.toString(captureImgCount) + ".jpg", faceMat);
        captureImgCount += 1;
    }

    public void setImgPath(String path) {
        imgPath = path;
    }

    public Image getFace() {
        return img.Mat2BufferedImage(faceMat);
    }

    public void closeVideoDisplay() {
        isdisplayClose = true;
    }

    @Override
    public void update(Observable o, Object arg) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//       img.saveImage("123.jpg", mat);
        if (1 == (int) arg) {
            captureImageAndSave();
        } else if (2 == (int) arg) {
            captureFaceAndSave();
        } else if (3 == (int) arg) {
            closeVideoDisplay();
            vdf.setVisible(false);
        };
    }

    public static void main(String[] args) throws InterruptedException {
        video video = new video();
        video.faceDetctDisplayVideo(0);

    }

}
