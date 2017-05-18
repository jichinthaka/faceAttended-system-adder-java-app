/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv.faceRecognize;

import attendance_system_adder.cv.image;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.opencv.core.Mat;

/**
 *
 * @author Imesh Chinthaka
 */
 public class fileHandler {
    
    ArrayList<Mat> images;
    List imageLables;
    List imageLables_no;
    List dirs;
    File [] listOfFolders;
    image im;
    Mat lable_no;
    
    

    public fileHandler() {
        this.images = new ArrayList<Mat>();
        this.imageLables = new ArrayList();
        this.imageLables_no = new ArrayList();
    } 
    
    public void collecter(String path){
        image image = new image();
//        Image images = new Image[10];
        
        File folder = new File(path);
        listOfFolders = folder.listFiles();
        
        for (int i=0; i < listOfFolders.length;i++){
            File [] listOfFiles = listOfFolders[i].listFiles();
            System.out.println(listOfFolders[i].getName());
            if (listOfFolders[i].isFile()){
                continue;
            }
            for (int j=0; j < listOfFiles.length-1;j++){
//                System.out.println(listOfFolders[i].getName());
                imageLables.add(listOfFolders[i].getName());
                imageLables_no.add(i);
//                System.out.println(imageLables);
                System.out.println(listOfFiles[j].getPath());
                Mat readImageToMat = image.readImageToMat(listOfFiles[j].getPath(), 1);
                System.out.println(readImageToMat);
//                image.saveImage(".\\savephotos\\1.jpg", readImageToMat);
                images.add(readImageToMat);
//                BufferedImage Mat2BufferedImage = image.Mat2BufferedImage(readImageToMat);
//                image.displayImage(Mat2BufferedImage);
                
            }
//            System.out.println(imageLables);
        }
    }
    
    public ArrayList<Mat> getImages(){
        return images;
    }
    
    public List getImageLables(){
        return imageLables;
    }
    
    public List getImageLablesNo(){
        return imageLables_no;
    }
    
    
    
    public static void main(String[] args) {
        fileHandler f = new fileHandler();
        image image = new image();
        
        f.collecter(".\\photos\\");
        System.out.println(f.listOfFolders);
        
//        for (Object<M> i: f.getImages()){
//            
//        }
//        System.out.println(f.imageLables.toString());
//        System.out.println(f.images.toString());
        System.out.println(f.getImages().size());

        BufferedImage Mat2BufferedImage = image.Mat2BufferedImage((Mat) f.getImages().get(0));
        image.displayImage(Mat2BufferedImage);
    }
}


//class xxx{
//    public static void main(String[] args) {
//        fileHandler1 f = new fileHandler1();
//        image image = new image();
//        
//        f.collecter(".\\photos\\");
//        System.out.println(f.listOfFolders.toString());
////        System.out.println(f.imageLables.toString());
////        System.out.println(f.images.toString());
//
//        BufferedImage Mat2BufferedImage = image.Mat2BufferedImage((Mat) f.getImages().get(0));
//        image.displayImage(Mat2BufferedImage);
//    }
//}
