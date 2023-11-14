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
public class brgmasuk {
    private int idbm;
    private barang idbarang = new barang();
    private String tanggal;
    private int qty;

    public brgmasuk() {
    }

    public brgmasuk(int idbm, barang idbarang, String tanggal, int qty) {
        this.idbm = idbm;
        this.idbarang = idbarang;
        this.tanggal = tanggal;
        this.qty = qty;
    }

    public int getIdbm() {
        return idbm;
    }

    public barang getIdbarang() {
        return idbarang;
    }

    public int getQty() {
        return qty;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setIdbm(int idbm) {
        this.idbm = idbm;
    }

    public void setIdbarang(barang idbarang) {
        this.idbarang = idbarang;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    
    
    public brgmasuk getById(int id) {
        brgmasuk bm = new brgmasuk();
        ResultSet rs = koneksi.selectQuery("SELECT "
                                        + "     bm.idbm AS idbm, "
                                        + "     bm.qty AS qty, "
                                        + "     bm.tanggal AS tanggal, "
                                        + "     b.idbarang AS idbarang, "
                                        + "     b.namabarang AS namabarang "
                                        + "     FROM brgmasuk bm "
                                        + "     LEFT JOIN barang b ON b.idbarang = bm.idbarang "
                                        + "     WHERE bm.idbarang = '" + id + "'");
        try
        {
            while(rs.next()) {
                bm = new brgmasuk();
                bm.setIdbm(rs.getInt("idbm"));
                bm.getIdbarang().setIdbarang(rs.getInt("idbarang"));
                bm.getIdbarang().setNamabarang(rs.getString("namabarang"));
                bm.setQty(rs.getInt("qty"));
                bm.setTanggal(rs.getString("tanggal"));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return bm;
    }
    
    public ArrayList<brgmasuk> getAll() {
        ArrayList<brgmasuk> Listbm = new ArrayList();
        ResultSet rs = koneksi.selectQuery("SELECT "
                                        + "     bm.idbm AS idbm, "
                                        + "     bm.qty AS qty, "
                                        + "     bm.tanggal AS tanggal, "
                                        + "     b.idbarang AS idbarang, "
                                        + "     b.namabarang AS namabarang "
                                        + "     FROM brgmasuk bm "
                                        + "     LEFT JOIN barang b ON b.idbarang = bm.idbarang ");
        
    try
        {
            while(rs.next()) {
                System.out.println(rs.getString("idbarang"));
                brgmasuk bm = new brgmasuk();
                bm = new brgmasuk();
                bm.setIdbm(rs.getInt("idbm"));
                bm.getIdbarang().setIdbarang(rs.getInt("idbarang"));
                bm.getIdbarang().setNamabarang(rs.getString("namabarang"));
                bm.setQty(rs.getInt("qty"));
                bm.setTanggal(rs.getString("tanggal"));
                
                Listbm.add(bm);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return Listbm;
    }
    
    public void save() {
        if(getById(idbm).getIdbm() == 0) {
            String SQL = "INSERT INTO brgmasuk (idbarang, tanggal, qty) VALUES ("
                    + "     '" + this.getIdbarang().getIdbarang() + "', "
                    + "     '" + this.tanggal + "', "
                    + "     '" + this.qty + "' )";
            this.idbm = koneksi.insertQueryGetId(SQL);
        } else {
            String SQL = "UPDATE brgmasuk SET "
                    + "     idbarang = '" + this.getIdbarang().getIdbarang() + "', "
                    + "     tanggal = '" + this.getTanggal() + "', "
                    + "     qty = '" + this.getQty() + "' "
                    + "     WHERE idbm = '" + this.idbm + "'";
            koneksi.executeQuery(SQL);
        }
    }
    
    public void delete() {
        String SQL = "DELETE FROM brgmasuk WHERE idbm = '" + this.idbm + "'";
        koneksi.executeQuery(SQL);
    }
    
    public ArrayList<brgmasuk> search(String keyword, String tglAwal, String tglAkhir){
        ArrayList<brgmasuk> Listbm = new ArrayList();
        String sql = 
                "SELECT "
                + "     bm.idbm AS idbm, "
                + "     bm.qty AS qty, "
                + "     bm.tanggal AS tanggal, "
                + "     b.idbarang AS idbarang, "
                + "     b.namabarang AS namabarang "
                + "     FROM brgmasuk bm "
                + "     LEFT JOIN barang b ON b.idbarang = bm.idbarang "
                + "     WHERE b.namabarang = '" + keyword + "'"
                + "     OR bm.tanggal >= '" + tglAwal + "'"
                + "     AND bm.tanggal <= '" + tglAkhir + "'";;
                
            ResultSet rs = koneksi.selectQuery(sql);
            try{
                while(rs.next()){
                brgmasuk bm = new brgmasuk();
                bm = new brgmasuk();
                bm.setIdbm(rs.getInt("idbm"));
                bm.getIdbarang().setIdbarang(rs.getInt("idbarang"));
                bm.getIdbarang().setNamabarang(rs.getString("namabarang"));
                bm.setQty(rs.getInt("qty"));
                bm.setTanggal(rs.getString("tanggal"));
                
                Listbm.add(bm);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return Listbm;
    }
}
