/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.File;
import java.sql.Date;

/**
 *
 * @author phuonghoangnguyen
 */
public class Sach {

    private int Id;
    private String TenSach;
    private String MaSach;
    private int IdTacGia;
    private int IdLoaiSach;
    private int IdNXB;
    private Date NgayNhap;
    private int SoLuong;
    private Boolean Xoa;
    private File Anh;

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
    public Boolean getXoa() {
        return Xoa;
    }

    /**
     * @param Xoa the Xoa to set
     */
    public void setXoa(Boolean Xoa) {
        this.Xoa = Xoa;
    }

    /**
     * @return the Anh
     */
    public File getAnh() {
        return Anh;
    }

    /**
     * @param Anh the Anh to set
     */
    public void setAnh(File Anh) {
        this.Anh = Anh;
    }
}
