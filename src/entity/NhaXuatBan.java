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
public class NhaXuatBan {

    private int Id;
    private String TenNXB;
    private String DiaChi;
    private String SDT;
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
     * @return the DiaChi
     */
    public String getDiaChi() {
        return DiaChi;
    }

    /**
     * @param DiaChi the DiaChi to set
     */
    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    /**
     * @return the SDT
     */
    public String getSDT() {
        return SDT;
    }

    /**
     * @param SDT the SDT to set
     */
    public void setSDT(String SDT) {
        this.SDT = SDT;
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
