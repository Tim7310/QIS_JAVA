/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files;

import java.io.File;

/**
 *
 * @author QPDLENOVO1
 */
public class createDirectory {
    
    public static void create(){
                        String username = (System.getenv("USERNAME"));
        //To create multiple directories/folders
        File files = new File("C:\\Users\\"+username+"\\Documents\\Qis");
        if (!files.exists()) {
            if (files.mkdirs()) {
                //System.out.println("Multiple directories are created!");
            } else {
                //System.out.println("Failed to create multiple directories!");
            }
        }
                        
                                        }
    
}
