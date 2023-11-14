/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

/**
 *
 * @author HP
 */
import java.sql.*;
import java.util.ArrayList;
import koneksi.koneksi;
import java.util.Date;
import javax.swing.JTextField;
public class barang {
    private int idbarang;
    private String namabarang;
    private String kategori;
    private int qty;
    

    public barang() {
    }

    public barang(int idbarang, String namabarang, String kategori, int qty) {
        this.idbarang = idbarang;
        this.namabarang = namabarang;
        this.kategori = kategori;
        this.qty = qty;
        
    }

    public int getIdbarang() {
        return idbarang;
    }

    public void setIdbarang(int idbarang) {
        this.idbarang = idbarang;
    }

    public String getNamabarang() {
        return namabarang;
    }

    public void setNamabarang(String namabarang) {
        this.namabarang = namabarang;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    
    
    
    public barang getById(int id) {
        barang brg = new barang();
        ResultSet rs = koneksi.selectQuery("SELECT * FROM barang "
                                            + " WHERE idbarang = '" + id + "'");
        try 
        {
            while(rs.next()) 
            {
                brg = new barang();
                brg.setIdbarang(rs.getInt("idbarang"));
                brg.setNamabarang(rs.getString("namabarang"));
                brg.setQty(rs.getInt("qty"));
            }
        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }
        return brg;
    }
    
    public barang getByName(String nama) {
        barang brg = new barang();
        ResultSet rs = koneksi.selectQuery("SELECT * FROM barang "
                                            + " WHERE namabarang = '" + nama + "'");
        
        try 
        {
            while(rs.next()) 
            {
                brg = new barang();
                brg.setIdbarang(rs.getInt("idbarang"));
                brg.setNamabarang(rs.getString("namabarang"));
                brg.setQty(rs.getInt("qty"));
            }
        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }
        return brg;
    }
    public ArrayList<barang> getAll() {
        ArrayList<barang> ListBarang = new ArrayList();
        
        ResultSet rs = koneksi.selectQuery("SELECT * FROM barang");
        
        try 
        {
            while(rs.next()) 
            {
                barang brg = new barang();
                brg.setIdbarang(rs.getInt("idbarang"));
                brg.setNamabarang(rs.getString("namabarang"));
                brg.setQty(rs.getInt("qty"));
                
                ListBarang.add(brg);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return ListBarang;
    }
    public ArrayList<barang> search(String keyword) {
        ArrayList<barang> ListBarang = new ArrayList();
        
        String sql = "SELECT * FROM barang WHERE "
                    + "    namabarang LIKE '%" + keyword + "%' ";
        
        ResultSet rs = koneksi.selectQuery(sql);
        
        try 
        {
            while(rs.next()) {
                barang brg = new barang();
                brg.setIdbarang(rs.getInt("idbarang"));
                brg.setNamabarang(rs.getString("namabarang"));
                brg.setQty(rs.getInt("qty"));
                
                ListBarang.add(brg);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return ListBarang;
    }
    public void save() {
        if(getById(idbarang).getIdbarang() == 0) 
        {
            String SQL = "INSERT INTO barang (namabarang, qty) VALUES("
                    + "     '" + this.namabarang + "', "
                    + "     '" + this.qty + "' "
                    + "     )";
            this.idbarang = koneksi.insertQueryGetId(SQL);
        }
        else 
        {
            String SQL = "UPDATE barang SET "
                    + "     namabarang = '" + this.namabarang + "', "
                    + "     qty = '" + this.qty + "' "
                    + "     WHERE idbarang = '" + this.idbarang + "'";
            koneksi.executeQuery(SQL);
        }
    }
    public void delete() {
        String SQL = "DELETE FROM barang WHERE idbarang = '" + this.idbarang + "'";
        koneksi.executeQuery(SQL);
    }


}
