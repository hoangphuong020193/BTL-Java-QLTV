/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author phuonghoangnguyen
 */
public class LoaiSach {

    private int Id;
    private String TenLoaiSach;
    private String KieuSach;
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
