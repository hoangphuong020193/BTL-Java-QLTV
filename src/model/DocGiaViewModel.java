/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author phuonghoangnguyen
 */
public class DocGiaViewModel {

    private int id;
    private String hoTenDG;
    private String maDocGia;
    private String ho;
    private String tenLot;
    private String ten;
    private Date ngayTao;
    private Boolean xoa;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the hoTenDG
     */
    public String getHoTenDG() {
        return (this.ho + " " + this.tenLot + " " + this.ten).replace("  ", " ");
    }

    /**
     * @return the maDocGia
     */
    public String getMaDocGia() {
        return maDocGia;
    }

    /**
     * @param maDocGia the maDocGia to set
     */
    public void setMaDocGia(String maDocGia) {
        this.maDocGia = maDocGia;
    }

    /**
     * @return the ho
     */
    public String getHo() {
        return ho;
    }

    /**
     * @param ho the ho to set
     */
    public void setHo(String ho) {
        this.ho = ho;
    }

    /**
     * @return the tenLot
     */
    public String getTenLot() {
        return tenLot;
    }

    /**
     * @param tenLot the tenLot to set
     */
    public void setTenLot(String tenLot) {
        this.tenLot = tenLot;
    }

    /**
     * @return the ten
     */
    public String getTen() {
        return ten;
    }

    /**
     * @param ten the ten to set
     */
    public void setTen(String ten) {
        this.ten = ten;
    }

    /**
     * @return the ngayTao
     */
    public Date getNgayTao() {
        return ngayTao;
    }

    /**
     * @param ngayTao the ngayTao to set
     */
    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    /**
     * @return the xoa
     */
    public Boolean getXoa() {
        return xoa;
    }

    /**
     * @param xoa the xoa to set
     */
    public void setXoa(Boolean xoa) {
        this.xoa = xoa;
    }
}
