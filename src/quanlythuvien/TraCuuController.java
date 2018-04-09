/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import model.NhanVienDangNhap;

/**
 * FXML Controller class
 *
 * @author phuonghoangnguyen
 */
public class TraCuuController implements Initializable {

    private NhanVienDangNhap nhanVien;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setNhanVien(NhanVienDangNhap nv) {
        nhanVien = nv;
    }
}
