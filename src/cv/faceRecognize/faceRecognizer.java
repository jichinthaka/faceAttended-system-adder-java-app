/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv.faceRecognize;

/**
 *
 * @author Imesh Chinthaka
 */
import org.opencv.face.*;
import attendance_system_adder.cv.image;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
//import cv.faceRecognize.fileHandler1;
import java.io.File;
import java.io.IOException;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opencv.core.Core;
import static org.opencv.core.CvType.CV_32SC1;

//class fileHandler {
//    
//    List<Mat> images;
//    List imageLables;
//    List dirs;
//    File [] listOfFolders;
//    image im;
//    
//    
//
//    public fileHandler() {
//        this.images = new ArrayList<Mat>();
//        this.imageLables = new ArrayList();
//    } 
//    
//    public void collecter(String path){
//        image image = new image();
////        Image images = new Image[10];
//        
//        File folder = new File(path);
//        listOfFolders = folder.listFiles();
//        for (int i=0; i < listOfFolders.length;i++){
//            File [] listOfFiles = listOfFolders[i].listFiles();
//            for (int j=0; j < listOfFiles.length;j++){
////                System.out.println(listOfFolders[i].getName());
//                imageLables.add(listOfFolders[i].getName());
////                System.out.println(imageLables);
//                System.out.println(listOfFiles[j].getPath());
//                Mat readImageToMat = image.readImageToMat(listOfFiles[j].getPath(), 1);
//                images.add(readImageToMat);
//                BufferedImage Mat2BufferedImage = image.Mat2BufferedImage(readImageToMat);
//                image.displayImage(Mat2BufferedImage);
//                
//            }
////            System.out.println(imageLables);
//        }
//    }
//    
//    public List getImages(){
//        return images;
//    }
//    
//    public List getImageLables(){
//        return imageLables;
//    }
//    
//}

public class faceRecognizer {
    fileHandler fileHandler;
    FaceRecognizer fr;
    LBPHFaceRecognizer LBPHFaceRecognizer;
    image im;
    String path;
//    Mat[] faces;
    ArrayList faces;
    
    public faceRecognizer() {
        LBPHFaceRecognizer = Face.createLBPHFaceRecognizer();
        
        this.fileHandler = new fileHandler();
        path = ".\\photos\\";
        faces = new ArrayList();
        im = new image();
    }
    
    static{
        
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    
    public void facesFromPhotos(){
        fileHandler.collecter(path);
        ArrayList<Mat> images = fileHandler.getImages();
//        System.out.println(images.get(0).toString());
//        
//        System.out.println(images.toString());
//        
//        System.out.println(images.size());
//        BufferedImage Mat2BufferedImage = im.Mat2BufferedImage((Mat)f.getImages().get(160));
//        im.displayImage(Mat2BufferedImage);

        System.out.println(images.size());
        
        for(Mat image:images){
            System.out.println(image.toString());
//            if((Mat)image==null){
//                System.out.println("nnn");
//            }
            
            BufferedImage Mat2BufferedImage = im.Mat2BufferedImage((Mat)image);
            im.displayImage(im.Mat2BufferedImage((Mat) image));
//            String toString = im.getFaceDetec((Mat) image).toString();
//            System.out.println("aa");
            faces.add(im.getFaceDetec((Mat) image));
        }
    }
    
    public Mat ListToMat(List<Integer> a){
        System.out.println(a);
        Mat m =new Mat(a.size(), 1, CvType.CV_32SC1);
//        IntBuffer inf = m.createBuffer();
        System.out.println(m);
        int i = 0;
        for (int lable:a){
            System.out.println(Arrays.toString(m.get(i, 1)));
            m.put(i, 0, lable);
            System.out.println(Arrays.toString(m.get(i, 0)));
            i+=1;
        }
        System.out.println(m);
        return m;
    }
    
    public void tainFaceRecognizer(){
        Mat lableMat = new Mat(fileHandler.getImageLablesNo().size(), 1, CV_32SC1);
        //IntBuffer labelsBuf = lableMat.;
        
        for(int i=0; i<=fileHandler.getImageLablesNo().size();i++){
            
        }
        
        LBPHFaceRecognizer.train(faces, lableMat);
        
        
        
    }
    
    public void trainpy(){
        String cmd = "F:\\";
        String py="faceTrainSave";
        try {
            Runtime.getRuntime().exec("python "+cmd + py + ".py");
        } catch (IOException ex) {
            Logger.getLogger(faceRecognizer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveFacerecognizer(){
        LBPHFaceRecognizer.save("abc.yaml");
    }
    
    public static void main(String[] args) {
//        fileHandler fh =new fileHandler();
//        fh.collecter(".\\photos\\");
        
//        
//        
        
        faceRecognizer fr = new faceRecognizer();
        fr.trainpy();
        //fr.facesFromPhotos();
        //fr.tainFaceRecognizer();
        //fr.saveFacerecognizer();

//        fileHandler f = new fileHandler();
//        image image = new image();
//        
//        f.collecter(".\\photos\\");
//        System.out.println(f.listOfFolders);
//        
////        for (Object<M> i: f.getImages()){
////            
////        }
////        System.out.println(f.imageLables.toString());
////        System.out.println(f.images.toString());
//        System.out.println(f.getImages().size());
//
//        BufferedImage Mat2BufferedImage = image.Mat2BufferedImage((Mat) f.getImages().get(0));
//        image.displayImage(Mat2BufferedImage);
    }
    
    
}
