/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author phuonghoangnguyen
 */
public class PhieuMuon {

    private int Id;
    private int IđocGia;
    private Date NgayMuon;
    private Date NgayTra;
    private Boolean Xoa;

    /**
     * @return the Id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * @return the IđocGia
     */
    public int getIđocGia() {
        return IđocGia;
    }

    /**
     * @param IđocGia the IđocGia to set
     */
    public void setIđocGia(int IđocGia) {
        this.IđocGia = IđocGia;
    }

    /**
     * @return the NgayMuon
     */
    public Date getNgayMuon() {
        return NgayMuon;
    }

    /**
     * @param NgayMuon the NgayMuon to set
     */
    public void setNgayMuon(Date NgayMuon) {
        this.NgayMuon = NgayMuon;
    }

    /**
     * @return the NgayTra
     */
    public Date getNgayTra() {
        return NgayTra;
    }

    /**
     * @param NgayTra the NgayTra to set
     */
    public void setNgayTra(Date NgayTra) {
        this.NgayTra = NgayTra;
    }

    /**
     * @return the Xoa
     */
    public Boolean getXoa() {
        return Xoa;
    }

    /**
     * @param Xoa the Xoa to set
     */
    public void setXoa(Boolean Xoa) {
        this.Xoa = Xoa;
    }
}
