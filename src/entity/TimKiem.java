/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class TimKiem {
    private String MaSach;
    private String TenSach;
    private String TenLoaiSach;
    private String TenTacGia;
    private String TenNXB;
    private Date NgayNhap;

    /**
     * @return the MaSach
     */
    public String getMaSach() {
        return MaSach;
    }

    /**
     * @param MaSach the MaSach to set
     */
    public void setMaSach(String MaSach) {
        this.MaSach = MaSach;
    }

    /**
     * @return the TenSach
     */
    public String getTenSach() {
        return TenSach;
    }

    /**
     * @param TenSach the TenSach to set
     */
    public void setTenSach(String TenSach) {
        this.TenSach = TenSach;
    }

    /**
     * @return the TenLoaiSach
     */
    public String getTenLoaiSach() {
        return TenLoaiSach;
    }

    /**
     * @param TenLoaiSach the TenLoaiSach to set
     */
    public void setTenLoaiSach(String TenLoaiSach) {
        this.TenLoaiSach = TenLoaiSach;
    }

    /**
     * @return the TenTacGia
     */
    public String getTenTacGia() {
        return TenTacGia;
    }

    /**
     * @param TenTacGia the TenTacGia to set
     */
    public void setTenTacGia(String TenTacGia) {
        this.TenTacGia = TenTacGia;
    }

    /**
     * @return the TenNXB
     */
    public String getTenNXB() {
        return TenNXB;
    }

    /**
     * @param TenNXB the TenNXB to set
     */
    public void setTenNXB(String TenNXB) {
        this.TenNXB = TenNXB;
    }

    /**
     * @return the NgayNhap
     */
    public Date getNgayNhap() {
        return NgayNhap;
    }

    /**
     * @param NgayNhap the NgayNhap to set
     */
    public void setNgayNhap(Date NgayNhap) {
        this.NgayNhap = NgayNhap;
    }
    
}
