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
public class TacGiaViewModel {

    private int Id;
    private String TacGia;

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
     * @return the TacGia
     */
    public String getTacGia() {
        return TacGia;
    }

    /**
     * @param TacGia the TacGia to set
     */
    public void setTacGia(String TacGia) {
        this.TacGia = TacGia;
    }
}
