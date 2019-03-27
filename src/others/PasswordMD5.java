package others;
import files.DatabaseConnect;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PasswordMD5 {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        String password = "jargpkcljs";

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        System.out.println(sb.toString());
        String cPassword =sb.toString();
         try{
            DatabaseConnect.connect();
            Connection con = DatabaseConnect.con;
            PreparedStatement command = con.prepareStatement("select username from tbl_tempoaccount where password like '"+cPassword+"'");
            ResultSet result = command.executeQuery();
            while(result.next())
            {
               System.out.println("your in");
            }
            con.close();
            command.close();
            result.close();
            }
        catch(Exception ex)
            {
            }

    }

}