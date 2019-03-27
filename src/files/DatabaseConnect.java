package files;

import java.sql.*;
//import java.util.*;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;

public class DatabaseConnect {
    public static Connection con;
    static PreparedStatement command;
    static ResultSet result;
public static String getIp(){
                String IP=null;
try
        {
            String pcusername = (System.getenv("USERNAME"));
            File file = new File("C:\\Users\\"+pcusername+"\\Documents\\Qis\\ipsetup.txt");
            //set a string for host saved in a file;
                    if (!file.exists()) {
                    file.createNewFile();}
                    
            FileReader fr = new FileReader(file.getAbsoluteFile());
            BufferedReader bw = new BufferedReader(fr);
            String concat=null;//step2: write it
            while((concat=bw.readLine())!=null)
                {
                    //System.out.print(IP);
                    IP =concat;
                }
            bw.close();
            //System.out.print(IP);
                        }
    catch (Exception ex)
            {ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Configuration error\n","Failed",3);
            }
            return IP;
}
public static boolean setIp(String IP){
    boolean a;
    //String content = textFieldName.getText();    //step1: get the content of the textfield
    try {
                String pcusername = (System.getenv("USERNAME"));
                File file = new File("C:\\Users\\"+pcusername+"\\Documents\\Qis\\ipsetup.txt");

                // if file doesnt exists, then create it
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            if(IP== null){
                IP="";
            }
            bw.write(IP);    //step2: write it
        }
                a=true;
                //JOptionPane.showMessageDialog(null, "Your Configuration is set\n","Success",1);

//                System.out.println("Done");

            }
    catch (Exception ex)
            {ex.printStackTrace();
                a= false;            
                //JOptionPane.showMessageDialog(null, "Configuration error\n","Failed",3);
            }
    return a;
    }

    public static boolean connect()
    { Boolean a=true;
        try
        {
            String pcusername = (System.getenv("USERNAME"));
            File file = new File("C:\\Users\\"+pcusername+"\\Documents\\Qis\\ipsetup.txt");
            //set a string for host saved in a file;
                    if (!file.exists()) {
                    file.createNewFile();}
                    
            FileReader fr = new FileReader(file.getAbsoluteFile());
            BufferedReader bw = new BufferedReader(fr);
            String IP=null;
            String concat=null;//step2: write it
            while((concat=bw.readLine())!=null)
                {
                    //System.out.print(IP);
                    IP =concat;
                }
            bw.close();
            //System.out.print(IP);
            //String IP = "localhost";
            String databaseName = "qisjava";
            String driver = "com.mysql.jdbc.Driver";    //the mysql driver from the jar
           //String url = "jdbc:derby://localhost:1527/QIS";
             String url = "jdbc:mysql://"+ IP + "/"+databaseName+"?useLegacyDatetimeCode=TRUE";
           //String url = "jdbc:mysql://"+ IP + "/" + databaseName; 
            String username = "root";   //database username
            String password = "";   //database password
            Class.forName(driver);  //so that is uses the driver
            
            con = DriverManager.getConnection(url,username,password);   //connects to the database
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Cannot connect to the database! Info: \n" + ex.getMessage());
            //System.exit(-1);
            a= false;
        }
        return a;
    }
    
     public static boolean runQuery(String query)
    {
        try
        {
            command = con.prepareStatement(query);
            command.executeUpdate();
            con.close();
            return true;
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Error running the query! Info: \n" + ex.getMessage());
            return false;
        }
    }
    
    public static String getValue(String query, String col)
    {
        try
        {
            String value = "";
            command = con.prepareStatement(query);
            result = command.executeQuery();
            
            while(result.next())
            {
                value = result.getString(col);
            }
            con.close();
            return value;
        }
        
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Error running the query! Info: \n" + ex.getMessage());
            return null;
        }
    } 
    
}
