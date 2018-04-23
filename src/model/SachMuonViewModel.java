/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author phuonghoangnguyen
 */
public class SachMuonViewModel {

    private int idPhieuMuon;
    private int idSach;
    private String maSach;
    private String tenSach;
    private boolean xoa;

    /**
     * @return the idSach
     */
    public int getIdSach() {
        return idSach;
    }

    /**
     * @param idSach the idSach to set
     */
    public void setIdSach(int idSach) {
        this.idSach = idSach;
    }

    /**
     * @return the maSach
     */
    public String getMaSach() {
        return maSach;
    }

    /**
     * @param maSach the maSach to set
     */
    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    /**
     * @return the tenSach
     */
    public String getTenSach() {
        return tenSach;
    }

    /**
     * @param tenSach the tenSach to set
     */
    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    /**
     * @return the xoa
     */
    public boolean isXoa() {
        return xoa;
    }

    /**
     * @param xoa the xoa to set
     */
    public void setXoa(boolean xoa) {
        this.xoa = xoa;
    }

    /**
     * @return the idPhieuMuon
     */
    public int getIdPhieuMuon() {
        return idPhieuMuon;
    }

    /**
     * @param idPhieuMuon the idPhieuMuon to set
     */
    public void setIdPhieuMuon(int idPhieuMuon) {
        this.idPhieuMuon = idPhieuMuon;
    }
}
