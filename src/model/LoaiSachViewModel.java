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

    private int id;
    private String tenLoaiSach;

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
     * @return the TenLoai
     */
    public String getTenLoaiSach() {
        return tenLoaiSach;
    }

    /**
     * @param TenLoai the TenLoai to set
     */
    public void setTenLoaiSach(String TenLoai) {
        this.tenLoaiSach = TenLoai;
    }
}
