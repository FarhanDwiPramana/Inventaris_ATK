/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

/**
 *
 * @author HP
 */
import java.sql.*;
public class koneksi {
    
    public static Connection koneksi;

    public static PreparedStatement prepareStatement(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Connection connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Berhasil Koneksi");
        }   catch (ClassNotFoundException ex){
            System.out.println ("Gagal Koneksi"+ex);
        }
        
        String url ="jdbc:mysql://localhost:3306/uas";
        try {
            koneksi = DriverManager.getConnection(url,"root","");
            System.out.println("Berhasil Koneksi Database");
        }   catch (SQLException ex){
            System.out.println("Gagal Koneksi Database "+ex);
        }
        return koneksi;
    }
    
    public static void bukaKoneksi()
    {
        if(koneksi == null)
        {
            try
            {
                String url = "jdbc:mysql://localhost:3306/uas";
                String user = "root";
                String password = "";
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                koneksi = DriverManager.getConnection(url, user, password);
            }
            catch (SQLException t)
            {
                System.out.println("Error koneksi!");
            }
        }
    }

    public static int insertQueryGetId(String query)
    {
        bukaKoneksi();
        int num = 0;
        int result = -1;

        try
        {
            Statement stmt = koneksi.createStatement();
            num = stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next())
            {
                result = rs.getInt(1);
            }

            rs.close();
            stmt.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result = -1;
        }

        return result;
    }

    public static boolean executeQuery(String query)
    {
        bukaKoneksi();
        boolean result = false;

        try
        {
            Statement stmt = koneksi.createStatement();
            stmt.executeUpdate(query);

            result = true;

            stmt.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }

    public static ResultSet selectQuery(String query)
    {
        bukaKoneksi();
        ResultSet rs = null;
        try
        {
            Statement stmt = koneksi.createStatement();
            rs = stmt.executeQuery(query);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return rs;
    }
}
