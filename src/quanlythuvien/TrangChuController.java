/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien;

import common.OpenForm;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.NhanVienDangNhap;

/**
 * FXML Controller class
 *
 * @author phuonghoangnguyen
 */
public class TrangChuController implements Initializable {

    private NhanVienDangNhap nhanVien;
    @FXML
    private Label lbNVDangNhap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setNhanVien(NhanVienDangNhap nv) {
        nhanVien = nv;
        lbNVDangNhap.setText("Chào " + nv.getTen());
    }

    @FXML
    private void onClickQLDG(MouseEvent event) {
        FXMLLoader loader = navigatePage(event, "QuanLyDocGia.fxml", "Quản lý độc giả");
        QuanLyDocGiaController controller = loader.getController();
        controller.setNhanVien(nhanVien);
    }

    @FXML
    private void onClickQLS(MouseEvent event) {
    }

    @FXML
    private void onClickTraCuu(MouseEvent event) {
    }

    @FXML
    private void onClickMuonSach(MouseEvent event) {
    }

    @FXML
    private void onClickBaoCao(MouseEvent event) {
    }

    @FXML
    private void onClickTraSach(MouseEvent event) {
    }

    private FXMLLoader navigatePage(MouseEvent event, String formSource, String tile) {
        FXMLLoader loader = null;
        try {
            OpenForm form = new OpenForm();
            loader = form.open(formSource, tile);
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            return loader;
        }
    }
}
