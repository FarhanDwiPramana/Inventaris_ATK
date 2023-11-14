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
public class brgkeluar {
    private int idbk;
    private barang idbarang = new barang();
    private String tanggal;
    private int qty;

    public brgkeluar() {
    }

    public brgkeluar(int idbk, barang idbarang, String tanggal, int qty) {
        this.idbk = idbk;
        this.idbarang = idbarang;
        this.tanggal = tanggal;
        this.qty = qty;
    }

    public int getIdbk() {
        return idbk;
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

    public void setIdbk(int idbk) {
        this.idbk = idbk;
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
    
    public brgkeluar getById(int id) {
        brgkeluar bk = new brgkeluar();
        ResultSet rs = koneksi.selectQuery("SELECT "
                                        + "     bk.idbk AS idbk, "
                                        + "     bk.qty AS qty, "
                                        + "     bk.tanggal AS tanggal, "
                                        + "     b.idbarang AS idbarang, "
                                        + "     b.namabarang AS namabarang "
                                        + "     FROM brgkeluar bk "
                                        + "     LEFT JOIN barang b ON b.idbarang = bk.idbarang "
                                        + "     WHERE bk.idbarang = '" + id + "'");
        try
        {
            while(rs.next()) {
                bk = new brgkeluar();
                bk.setIdbk(rs.getInt("idbk"));
                bk.getIdbarang().setIdbarang(rs.getInt("idbarang"));
                bk.getIdbarang().setNamabarang(rs.getString("namabarang"));
                bk.setQty(rs.getInt("qty"));
                bk.setTanggal(rs.getString("tanggal"));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return bk;
    }
    
    public ArrayList<brgkeluar> getAll() {
        ArrayList<brgkeluar> Listbk = new ArrayList();
        ResultSet rs = koneksi.selectQuery("SELECT "
                                        + "     bk.idbk AS idbk, "
                                        + "     bk.qty AS qty, "
                                        + "     bk.tanggal AS tanggal, "
                                        + "     b.idbarang AS idbarang, "
                                        + "     b.namabarang AS namabarang "
                                        + "     FROM brgkeluar bk "
                                        + "     LEFT JOIN barang b ON b.idbarang = bk.idbarang ");
        
    try
        {
            while(rs.next()) {
                System.out.println(rs.getString("idbarang"));
                brgkeluar bk = new brgkeluar();
                bk = new brgkeluar();
                bk.setIdbk(rs.getInt("idbk"));
                bk.getIdbarang().setIdbarang(rs.getInt("idbarang"));
                bk.getIdbarang().setNamabarang(rs.getString("namabarang"));
                bk.setQty(rs.getInt("qty"));
                bk.setTanggal(rs.getString("tanggal"));
                
                Listbk.add(bk);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return Listbk;
    }
    
    public void save() {
        if(getById(idbk).getIdbk() == 0) {
            String SQL = "INSERT INTO brgkeluar (idbarang, tanggal, qty) VALUES ("
                    + "     '" + this.getIdbarang().getIdbarang() + "', "
                    + "     '" + this.tanggal + "', "
                    + "     '" + this.qty + "' )";
            this.idbk = koneksi.insertQueryGetId(SQL);
        } else {
            String SQL = "UPDATE brgkeluar SET "
                    + "     idbarang = '" + this.getIdbarang().getIdbarang() + "', "
                    + "     tanggal = '" + this.getTanggal() + "', "
                    + "     qty = '" + this.getQty() + "' "
                    + "     WHERE idbk = '" + this.idbk + "'";
            koneksi.executeQuery(SQL);
        }
    }
    
    public void delete() {
        String SQL = "DELETE FROM brgkeluar WHERE idbk = '" + this.idbk + "'";
        koneksi.executeQuery(SQL);
    }
    
    public ArrayList<brgkeluar> search(String keyword, String tglAwal, String tglAkhir){
        ArrayList<brgkeluar> Listbk = new ArrayList();
        String sql = 
                "SELECT "
                                        + "     bk.idbk AS idbk, "
                                        + "     bk.qty AS qty, "
                                        + "     bk.tanggal AS tanggal, "
                                        + "     b.idbarang AS idbarang, "
                                        + "     b.namabarang AS namabarang "
                                        + "     FROM brgkeluar bk "
                                        + "     LEFT JOIN barang b ON b.idbarang = bk.idbarang "
                                        + "     WHERE b.namabarang = '" + keyword + "'"
                                        + "     OR bk.tanggal >= '" + tglAwal + "'"
                                        + "     AND bk.tanggal <= '" + tglAkhir + "'";
            ResultSet rs = koneksi.selectQuery(sql);
            try{
                while(rs.next()){
                brgkeluar bk = new brgkeluar();
                bk = new brgkeluar();
                bk.setIdbk(rs.getInt("idbk"));
                bk.getIdbarang().setIdbarang(rs.getInt("idbarang"));
                bk.getIdbarang().setNamabarang(rs.getString("namabarang"));
                bk.setQty(rs.getInt("qty"));
                bk.setTanggal(rs.getString("tanggal"));
                
                Listbk.add(bk);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return Listbk;
    }
    
    

}
