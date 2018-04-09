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
public class NhanVien {

    private int Id;
    private String Ho;
    private String TenLot;
    private String Ten;
    private String TenDangNhap;
    private String MatKhau;
    private Date NgayTao;

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
     * @return the Ho
     */
    public String getHo() {
        return Ho;
    }

    /**
     * @param Ho the Ho to set
     */
    public void setHo(String Ho) {
        this.Ho = Ho;
    }

    /**
     * @return the TenLot
     */
    public String getTenLot() {
        return TenLot;
    }

    /**
     * @param TenLot the TenLot to set
     */
    public void setTenLot(String TenLot) {
        this.TenLot = TenLot;
    }

    /**
     * @return the Ten
     */
    public String getTen() {
        return Ten;
    }

    /**
     * @param Ten the Ten to set
     */
    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    /**
     * @return the TenDangNhap
     */
    public String getTenDangNhap() {
        return TenDangNhap;
    }

    /**
     * @param TenDangNhap the TenDangNhap to set
     */
    public void setTenDangNhap(String TenDangNhap) {
        this.TenDangNhap = TenDangNhap;
    }

    /**
     * @return the MatKhau
     */
    public String getMatKhau() {
        return MatKhau;
    }

    /**
     * @param MatKhau the MatKhau to set
     */
    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    /**
     * @return the NgayTao
     */
    public Date getNgayTao() {
        return NgayTao;
    }

    /**
     * @param NgayTao the NgayTao to set
     */
    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }
}
