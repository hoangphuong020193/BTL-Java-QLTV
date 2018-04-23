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
import model.NhanVienDangNhapModel;

/**
 * FXML Controller class
 *
 * @author phuonghoangnguyen
 */
public class TrangChuController implements Initializable {

    private NhanVienDangNhapModel nhanVien;
    @FXML
    private Label lbNVDangNhap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setNhanVien(NhanVienDangNhapModel nv) {
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
        FXMLLoader loader = navigatePage(event, "QuanLySach.fxml", "Quản lý sách");
        QuanLySachController controller = loader.getController();
        controller.setNhanVien(nhanVien);
    }

    @FXML
    private void onClickTraCuu(MouseEvent event) {
        FXMLLoader loader = navigatePage(event, "TraCuu.fxml", "Tra cứu sách");
        TraCuuController controller = loader.getController();
        controller.setNhanVien(nhanVien);
    }

    @FXML
    private void onClickMuonSach(MouseEvent event) {
        FXMLLoader loader = navigatePage(event, "MuonSach.fxml", "Mượn sách");
        MuonSachController controller = loader.getController();
        controller.setNhanVien(nhanVien);
    }

    @FXML
    private void onClickBaoCao(MouseEvent event) {
        FXMLLoader loader = navigatePage(event, "ThongKe.fxml", "Thống kê");
        ThongKeController controller = loader.getController();
        controller.setNhanVien(nhanVien);
    }

    @FXML
    private void onClickTraSach(MouseEvent event) {
        FXMLLoader loader = navigatePage(event, "TraSach.fxml", "Trả sách");
        TraSachController controller = loader.getController();
        controller.setNhanVien(nhanVien);
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
