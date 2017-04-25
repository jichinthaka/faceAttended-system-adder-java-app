/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance_system_adder;

import attendance_system_adder.video;
import attendance_system_adder.cv.image;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Imesh Chinthaka
 */
public class addPerson  {
    video video;
    image image;
    String imgPath;
    
    Image[] images;
    File folder;
    File[] listOfFiles;

    public addPerson() {
    }
    
    public synchronized void addPhoto(){
        
        if (video==null){
            video = new video();
//            video.start();
        }
        video.setImgPath(imgPath);
        video.faceDetctDisplayVideo(0);
    }
    
    public void creadDir(String name) throws IOException{
        imgPath = ".\\photos\\"+name;
        
        File folder = new File(imgPath);
        
        folder.mkdir();
        
        
    }
    
    public Image getFace(){
       return video.getFace();
    }
    
    public void clearAll(){
        video = null;
    }
    
//    public static void main(String[] args) throws IOException {
//        addPerson ap = new addPerson();
//        ap.creadDir("22222222222222");
//    }
    
    public String getImagePath(){
        return imgPath;
    }
    
    public synchronized Image[] getImageFromFile(){
        image = new image();
        images = new Image[10];
        folder = new File(imgPath);
        listOfFiles = folder.listFiles();
        for (int i=0; i < listOfFiles.length;i++){
            images[i]= image.Mat2BufferedImage(image.readImageToMat(listOfFiles[i].getPath(), 1));
            if (i>=9){
                break;
            }
        }
        return images;
    }
}
