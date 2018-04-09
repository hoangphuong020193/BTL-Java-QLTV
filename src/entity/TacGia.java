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
public class TacGia {

    private int Id;
    private String TenTacGia;
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
