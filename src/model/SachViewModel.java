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
public class SachViewModel {

    private int Id;
    private String MaSach;
    private String TenSach;
    private int IdLoaiSach;
    private String TenLoaiSach;
    private String KieuSach;
    private int IdTacGia;
    private String TenTacGia;
    private int IdNXB;
    private String TenNXB;
    private Date NgayNhap;
    private int SoLuong;
    private boolean Xoa;

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
     * @return the IdLoaiSach
     */
    public int getIdLoaiSach() {
        return IdLoaiSach;
    }

    /**
     * @param IdLoaiSach the IdLoaiSach to set
     */
    public void setIdLoaiSach(int IdLoaiSach) {
        this.IdLoaiSach = IdLoaiSach;
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
     * @return the IdTacGia
     */
    public int getIdTacGia() {
        return IdTacGia;
    }

    /**
     * @param IdTacGia the IdTacGia to set
     */
    public void setIdTacGia(int IdTacGia) {
        this.IdTacGia = IdTacGia;
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
     * @return the IdNXB
     */
    public int getIdNXB() {
        return IdNXB;
    }

    /**
     * @param IdNXB the IdNXB to set
     */
    public void setIdNXB(int IdNXB) {
        this.IdNXB = IdNXB;
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

    /**
     * @return the SoLuong
     */
    public int getSoLuong() {
        return SoLuong;
    }

    /**
     * @param SoLuong the SoLuong to set
     */
    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    /**
     * @return the Xoa
     */
    public boolean isXoa() {
        return Xoa;
    }

    /**
     * @param Xoa the Xoa to set
     */
    public void setXoa(boolean Xoa) {
        this.Xoa = Xoa;
    }

    /**
     * @return the KieuSach
     */
    public String getKieuSach() {
        return KieuSach;
    }

    /**
     * @param KieuSach the KieuSach to set
     */
    public void setKieuSach(String KieuSach) {
        this.KieuSach = KieuSach;
    }
}
