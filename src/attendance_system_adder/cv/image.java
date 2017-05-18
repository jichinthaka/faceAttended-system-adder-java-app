/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance_system_adder.cv;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.opencv.core.*;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import static org.opencv.imgproc.Imgproc.COLOR_RGB2GRAY;
import static org.opencv.imgproc.Imgproc.cvtColor;
import org.opencv.objdetect.CascadeClassifier;
//import org.opencv.contrib.*;

/**
 *
 * @author Imesh Chinthaka
 */
public class image {
    ImageIcon displayIcon;
    JFrame displayFrame;
    Imgcodecs ic;
    

    public image() {
    }
    
    static{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    
    public BufferedImage Mat2BufferedImage(Mat m){
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if ( m.channels() > 1 ) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = m.channels()*m.cols()*m.rows();
        byte [] b = new byte[bufferSize];
        m.get(0,0,b); // get all the pixels
        BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);  
        return image;

    }
    
    public void displayImage(Image img2)
    {   
        //BufferedImage img=ImageIO.read(new File("/HelloOpenCV/lena.png"));
        if (displayIcon==null){
            displayIcon =new ImageIcon(img2);
        }else{
            displayIcon.setImage(img2);
        }
        if (displayFrame==null){
            displayFrame = new JFrame();
            displayFrame.setDefaultCloseOperation(displayFrame.DISPOSE_ON_CLOSE);
            displayFrame.setLocation(450, 0);
        }
        displayFrame.setLayout(new FlowLayout());        
        displayFrame.setSize(img2.getWidth(null)+50, img2.getHeight(null)+50);     
        JLabel lbl=new JLabel();
        lbl.setIcon(displayIcon);
        displayFrame.add(lbl);
        displayFrame.setVisible(true);
//        displayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
    public void faceDetctDisplayImage(Mat img)
    {   
        Mat FaceDetec = this.FaceDetec(img);
        BufferedImage img2 = this.Mat2BufferedImage(FaceDetec);
        //BufferedImage img=ImageIO.read(new File("/HelloOpenCV/lena.png"));
        if (displayIcon==null){
            displayIcon =new ImageIcon(img2);
        }else{
            displayIcon.setImage(img2);
        }
        if (displayFrame==null){
            displayFrame = new JFrame();
            displayFrame.setDefaultCloseOperation(displayFrame.DISPOSE_ON_CLOSE);
            displayFrame.setLocation(450, 0);
        }
        displayFrame.setLayout(new FlowLayout());        
        displayFrame.setSize(img2.getWidth(null)+50, img2.getHeight(null)+50);     
        JLabel lbl=new JLabel();
        lbl.setIcon(displayIcon);
        displayFrame.add(lbl);
        if (!displayFrame.isVisible()){
            displayFrame.setVisible(true);
        }else{
            displayFrame.repaint();
        }
        
//        displayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
    public BufferedImage readImage(String filePath, int index){
        if (ic==null){
            ic = new Imgcodecs();
        }
        Mat imread = ic.imread(filePath,1);
        return this.Mat2BufferedImage(imread);
    }
    public Mat readImageToMat(String filePath, int index){
        if (ic==null){
            ic = new Imgcodecs();
        }
        Mat imread = ic.imread(filePath,1);
        return imread;
    }
    
    
    public void saveImage(String filePath, Mat img2){
        if (ic==null){
            ic = new Imgcodecs();
        }
        ic.imwrite(filePath, img2);
        
    }
    
    public Mat getFaceDetec(Mat image) {
        Mat face = null;
        
        System.out.println("\nRunning DetectFaceDemo");
        

        CascadeClassifier faceDetector = new CascadeClassifier(".\\resource\\haarcascade_frontalface_default.xml");

        // Detect faces in the image.
        // MatOfRect is a special container class for Rect.
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);
        
        
        
    //    System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
    //    // Draw a bounding box around each face.
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(255, 0, 0));
            face = new Mat(image, rect);
        }
    //    // Save the visualized detection.
    //    String filename = "faceDetection.png";
    //    System.out.println(String.format("Writing %s", filename));
    //    imwrite(filename, image);
        //FaceRecognizer fr;//= new LBPHFaceRecognizer();
        return face;
    }
    
    
    
    
    
    public Mat FaceDetec(Mat image) {
        Mat face = null;
        
        System.out.println("\nRunning DetectFaceDemo");
        

        CascadeClassifier faceDetector = new CascadeClassifier(".\\resource\\haarcascade_frontalface_default.xml");

        // Detect faces in the image.
        // MatOfRect is a special container class for Rect.
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);
        
        
        
    //    System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
    //    // Draw a bounding box around each face.
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(255, 0, 0));
            
        }
        return image;
    }
    
    
    public Mat RGBtoGRAY(Mat mat){
        Mat gray = new Mat();
        
        Imgproc.cvtColor(mat, gray, COLOR_RGB2GRAY);
        
        return gray;
    }
    
    public void closeDisplayFrame(){
        displayFrame.setVisible(false);
        displayFrame.dispose();
    }

    
    public static void main(String[] args) {
//        image img = new image();
//        Mat readImage = img.readImageToMat("111.jpg", 0);
//        Mat RGBtoGRAY = img.RGBtoGRAY(readImage);
//        Mat faceDetecInRect = img.FaceDetec(RGBtoGRAY);
//        System.out.println(readImage);
//        //System.out.println(faceDetecInRect);
//        
//        //Mat RGBtoGRAY = img.RGBtoGRAY(readImage);
//        
//        
//       // img.displayImage(readImage);
//        img.saveImage("222.png", RGBtoGRAY);
        
        
        
        
    }
    
}
