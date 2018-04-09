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
public class SachMuon {

    private int IdPhieuMuon;
    private int IdSach;
    private Boolean Xoa;

    /**
     * @return the IdPhieuMuon
     */
    public int getIdPhieuMuon() {
        return IdPhieuMuon;
    }

    /**
     * @param IdPhieuMuon the IdPhieuMuon to set
     */
    public void setIdPhieuMuon(int IdPhieuMuon) {
        this.IdPhieuMuon = IdPhieuMuon;
    }

    /**
     * @return the IdSach
     */
    public int getIdSach() {
        return IdSach;
    }

    /**
     * @param IdSach the IdSach to set
     */
    public void setIdSach(int IdSach) {
        this.IdSach = IdSach;
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
