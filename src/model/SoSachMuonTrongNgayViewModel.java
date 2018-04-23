/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author phuonghoangnguyen
 */
public class SoSachMuonTrongNgayViewModel {

    private String ngayMuon;
    private int soLuongMuon;

    /**
     * @return the ngayMuon
     */
    public String getNgayMuon() {
        return ngayMuon;
    }

    /**
     * @param ngayMuon the ngayMuon to set
     */
    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    /**
     * @return the soLuongMuon
     */
    public int getSoLuongMuon() {
        return soLuongMuon;
    }

    /**
     * @param soLuongMuon the soLuongMuon to set
     */
    public void setSoLuongMuon(int soLuongMuon) {
        this.soLuongMuon = soLuongMuon;
    }
}
