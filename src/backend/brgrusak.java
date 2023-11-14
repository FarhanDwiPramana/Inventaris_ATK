/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.sql.ResultSet;
import java.util.ArrayList;
import koneksi.koneksi;

/**
 *
 * @author HP
 */
public class brgrusak {
    private int idbr;
    private barang idbarang = new barang();
    private String tanggal;
    private String ket;
    private int qty;

    public brgrusak() {
    }

    public brgrusak(int idbr,barang idbarang, String tanggal, String ket, int qty) {
        this.idbr = idbr;
        this.idbarang = idbarang;
        this.tanggal = tanggal;
        this.ket = ket;
        this.qty = qty;
    }

    public int getIdbr() {
        return idbr;
    }

    public barang getIdbarang() {
        return idbarang;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getKet() {
        return ket;
    }

    public int getQty() {
        return qty;
    }

    public void setIdbr(int idbr) {
        this.idbr = idbr;
    }

    public void setIdbarang(barang idbarang) {
        this.idbarang = idbarang;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    
    
    public brgrusak getById(int id) {
        brgrusak br = new brgrusak();
        ResultSet rs = koneksi.selectQuery("SELECT "
                                        + "     br.idbr AS idbr, "
                                        + "     br.qty AS qty, "
                                        + "     br.tanggal AS tanggal, "
                                        + "     br.ket AS ket, "
                                        + "     b.idbarang AS idbarang, "
                                        + "     b.namabarang AS namabarang "
                                        + "     FROM brgrusak br "
                                        + "     LEFT JOIN barang b ON b.idbarang = br.idbarang "
                                        + "     WHERE br.idbarang = '" + id + "'");
        try
        {
            while(rs.next()) {
                br = new brgrusak();
                br.setIdbr(rs.getInt("idbr"));
                br.getIdbarang().setIdbarang(rs.getInt("idbarang"));
                br.getIdbarang().setNamabarang(rs.getString("namabarang"));
                br.setQty(rs.getInt("qty"));
                br.setTanggal(rs.getString("tanggal"));
                br.setKet(rs.getString("ket"));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return br;
    }
    
    public ArrayList<brgrusak> getAll() {
        ArrayList<brgrusak> Listbr = new ArrayList();
        ResultSet rs = koneksi.selectQuery("SELECT "
                                        + "     br.idbr AS idbr, "
                                        + "     br.qty AS qty, "
                                        + "     br.tanggal AS tanggal, "
                                        + "     br.ket AS ket, "
                                        + "     b.idbarang AS idbarang, "
                                        + "     b.namabarang AS namabarang "
                                        + "     FROM brgrusak br "
                                        + "     LEFT JOIN barang b ON b.idbarang = br.idbarang ");
        
    try
        {
            while(rs.next()) {
                System.out.println(rs.getString("idbarang"));
                brgrusak br = new brgrusak();
                br = new brgrusak();
                br.setIdbr(rs.getInt("idbr"));
                br.getIdbarang().setIdbarang(rs.getInt("idbarang"));
                br.getIdbarang().setNamabarang(rs.getString("namabarang"));
                br.setQty(rs.getInt("qty"));
                br.setTanggal(rs.getString("tanggal"));
                br.setKet(rs.getString("ket"));
                
                Listbr.add(br);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return Listbr;
    }
    
    public void save() {
        if(getById(idbr).getIdbr() == 0) {
            String SQL = "INSERT INTO brgrusak (idbarang, tanggal, qty, ket) VALUES ("
                    + "     '" + this.getIdbarang().getIdbarang() + "', "
                    + "     '" + this.tanggal + "', "
                    + "     '" + this.qty + "', "
                    + "     '" + this.ket + "' )";
            this.idbr = koneksi.insertQueryGetId(SQL);
        } else {
            String SQL = "UPDATE brgrusak SET "
                    + "     idbarang = '" + this.getIdbarang().getIdbarang() + "', "
                    + "     tanggal = '" + this.getTanggal() + "', "
                    + "     qty = '" + this.getQty() + "', "
                    + "     ket = '" + this.getKet() + "', "
                    + "     WHERE idbr = '" + this.idbr + "'";
            koneksi.executeQuery(SQL);
        }
    }
    
    public void delete() {
        String SQL = "DELETE FROM brgrusak WHERE idbr = '" + this.idbr + "'";
        koneksi.executeQuery(SQL);
    }
    
    public ArrayList<brgrusak> search(String keyword, String tglAwal, String tglAkhir){
        ArrayList<brgrusak> Listbr = new ArrayList();
        String sql = 
                "SELECT "
                + "     br.idbr AS idbr, "
                + "     br.qty AS qty, "
                + "     br.tanggal AS tanggal, "
                + "     br.ket AS ket, "
                + "     b.idbarang AS idbarang, "
                + "     b.namabarang AS namabarang "
                + "     FROM brgrusak br "
                + "     LEFT JOIN barang b ON b.idbarang = br.idbarang "
                + "     WHERE b.namabarang = '" + keyword + "'"
                + "     OR br.tanggal >= '" + tglAwal + "'"
                + "     AND br.tanggal <= '" + tglAkhir + "'";
            ResultSet rs = koneksi.selectQuery(sql);
            try{
                while(rs.next()){
                brgrusak br = new brgrusak();
                br = new brgrusak();
                br.setIdbr(rs.getInt("idbr"));
                br.getIdbarang().setIdbarang(rs.getInt("idbarang"));
                br.getIdbarang().setNamabarang(rs.getString("namabarang"));
                br.setQty(rs.getInt("qty"));
                br.setTanggal(rs.getString("tanggal"));
                br.setKet(rs.getString("ket"));
                
                Listbr.add(br);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return Listbr;
    }
    
}
