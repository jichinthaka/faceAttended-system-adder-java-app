/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Imesh Chinthaka
 */
public class fileTransfer {

    public static void main(String[] args) {
//        String url = "https://c-house.herokuapp.com/upload";
        String url = "http://192.168.1.2/symfony/web/home";
//        String url = "http://127.0.0.1:8000/upload";
        String charset = "UTF-8";
        String param = "value";
        File textFile = new File("222.jpg");
        File textFile1 = new File("123.jpg");
        File binaryFile = new File("/path/to/file.bin");
        String boundary = Long.toHexString(System.currentTimeMillis()); // Just generate some unique random value.
        String CRLF = "\r\n"; // Line separator required by multipart/form-data.

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
        } catch (MalformedURLException ex) {
            Logger.getLogger(fileTransfer.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        } catch (IOException ex) {
            Logger.getLogger(fileTransfer.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        connection.setDoOutput(true);
//        connection.
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
//        connection.setRequestProperty("Content-Type", "image/jpg");

        try (
                OutputStream output = connection.getOutputStream();
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true); //            StringWriter stringWriter = new StringWriter();
                //            PrintWriter writer = new PrintWriter(stringWriter);
                ) {
            // Send normal param.
//            writer.append("--" + boundary).append(CRLF);
//            writer.append("Content-Disposition: form-data; name=\"param\"").append(CRLF);
//            writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
//            writer.append(CRLF).append(param).append(CRLF).flush();
//            System.out.println(stringWriter.toString());

            // Send text file.
            writer.append("--" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"222\"; filename=\"" + textFile.getName() + "\"").append(CRLF);
            writer.append("Content-Type: image/jpg; charset=" + charset).append(CRLF); // Text file itself must be saved in this charset!
//            System.out.println(writer);
            writer.append(CRLF).flush();
            System.out.println(output);
            Files.copy(textFile.toPath(), output);
            System.out.println(output);
            System.out.println(textFile.getName());
            output.flush(); // Important before continuing with writer!
            writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.

            //...........................
            writer.append("--" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"textFile1\"; filename=\"" + textFile1.getName() + "\"").append(CRLF);
            writer.append("Content-Type: image/jpg; charset=" + charset).append(CRLF); // Text file itself must be saved in this charset!
//            System.out.println(writer);
            writer.append(CRLF).flush();
            System.out.println(output);
            Files.copy(textFile1.toPath(), output);
            System.out.println(output);
            System.out.println(textFile1.getName());
            output.flush(); // Important before continuing with writer!
            writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.

            //....................
            // Send binary file.
//            writer.append("--" + boundary).append(CRLF);
//            writer.append("Content-Disposition: form-data; name=\"binaryFile\"; filename=\"" + binaryFile.getName() + "\"").append(CRLF);
//            writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(binaryFile.getName())).append(CRLF);
//            writer.append("Content-Transfer-Encoding: binary").append(CRLF);
//            writer.append(CRLF).flush();
//            Files.copy(binaryFile.toPath(), output);
//            output.flush(); // Important before continuing with writer!
//            writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.
            // End of multipart/form-data.
            writer.append("--" + boundary + "--").append(CRLF).flush();
        } catch (IOException ex) {
            Logger.getLogger(fileTransfer.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Request is lazily fired whenever you need to obtain information about response.
        int responseCode = 0;
        try {
            responseCode = ((HttpURLConnection) connection).getResponseCode();
        } catch (IOException ex) {
            Logger.getLogger(fileTransfer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(responseCode); // Should be 200

    }
}
