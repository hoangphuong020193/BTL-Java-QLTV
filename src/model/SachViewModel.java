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

    private int id;
    private String maSach;
    private String tenSach;
    private int idLoaiSach;
    private String tenLoaiSach;
    private String kieuSach;
    private int idTacGia;
    private String tenTacGia;
    private int idNXB;
    private String tenNXB;
    private Date ngayNhap;
    private int soLuong;
    private boolean xoa;

    /**
     * @return the Id
     */
    public int getId() {
        return id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.id = Id;
    }

    /**
     * @return the MaSach
     */
    public String getMaSach() {
        return maSach;
    }

    /**
     * @param MaSach the MaSach to set
     */
    public void setMaSach(String MaSach) {
        this.maSach = MaSach;
    }

    /**
     * @return the TenSach
     */
    public String getTenSach() {
        return tenSach;
    }

    /**
     * @param TenSach the TenSach to set
     */
    public void setTenSach(String TenSach) {
        this.tenSach = TenSach;
    }

    /**
     * @return the IdLoaiSach
     */
    public int getIdLoaiSach() {
        return idLoaiSach;
    }

    /**
     * @param IdLoaiSach the IdLoaiSach to set
     */
    public void setIdLoaiSach(int IdLoaiSach) {
        this.idLoaiSach = IdLoaiSach;
    }

    /**
     * @return the TenLoaiSach
     */
    public String getTenLoaiSach() {
        return tenLoaiSach;
    }

    /**
     * @param TenLoaiSach the TenLoaiSach to set
     */
    public void setTenLoaiSach(String TenLoaiSach) {
        this.tenLoaiSach = TenLoaiSach;
    }

    /**
     * @return the IdTacGia
     */
    public int getIdTacGia() {
        return idTacGia;
    }

    /**
     * @param IdTacGia the IdTacGia to set
     */
    public void setIdTacGia(int IdTacGia) {
        this.idTacGia = IdTacGia;
    }

    /**
     * @return the TenTacGia
     */
    public String getTenTacGia() {
        return tenTacGia;
    }

    /**
     * @param TenTacGia the TenTacGia to set
     */
    public void setTenTacGia(String TenTacGia) {
        this.tenTacGia = TenTacGia;
    }

    /**
     * @return the IdNXB
     */
    public int getIdNXB() {
        return idNXB;
    }

    /**
     * @param IdNXB the IdNXB to set
     */
    public void setIdNXB(int IdNXB) {
        this.idNXB = IdNXB;
    }

    /**
     * @return the TenNXB
     */
    public String getTenNXB() {
        return tenNXB;
    }

    /**
     * @param TenNXB the TenNXB to set
     */
    public void setTenNXB(String TenNXB) {
        this.tenNXB = TenNXB;
    }

    /**
     * @return the NgayNhap
     */
    public Date getNgayNhap() {
        return ngayNhap;
    }

    /**
     * @param NgayNhap the NgayNhap to set
     */
    public void setNgayNhap(Date NgayNhap) {
        this.ngayNhap = NgayNhap;
    }

    /**
     * @return the SoLuong
     */
    public int getSoLuong() {
        return soLuong;
    }

    /**
     * @param SoLuong the SoLuong to set
     */
    public void setSoLuong(int SoLuong) {
        this.soLuong = SoLuong;
    }

    /**
     * @return the Xoa
     */
    public boolean isXoa() {
        return xoa;
    }

    /**
     * @param Xoa the Xoa to set
     */
    public void setXoa(boolean Xoa) {
        this.xoa = Xoa;
    }

    /**
     * @return the KieuSach
     */
    public String getKieuSach() {
        return kieuSach;
    }

    /**
     * @param KieuSach the KieuSach to set
     */
    public void setKieuSach(String KieuSach) {
        this.kieuSach = KieuSach;
    }
}
