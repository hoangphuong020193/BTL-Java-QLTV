/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author phuonghoangnguyen
 */
public class LoaiSachViewModel {

    private int Id;
    private String TenLoaiSach;

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
     * @return the TenLoai
     */
    public String getTenLoaiSach() {
        return TenLoaiSach;
    }

    /**
     * @param TenLoai the TenLoai to set
     */
    public void setTenLoaiSach(String TenLoai) {
        this.TenLoaiSach = TenLoai;
    }
}
