/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien;

import common.OpenForm;
import data.ConnectionContext;
import helper.MD5Encrypt;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.NhanVienDangNhapModel;

/**
 * FXML Controller class
 *
 * @author phuonghoangnguyen
 */
public class DangNhapController implements Initializable {

    @FXML
    private Label lbError;
    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtPassword;

    private Connection connect;
    private NhanVienDangNhapModel nv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connect = ConnectionContext.ketNoi();
    }

    @FXML
    public void loginSubmit(ActionEvent event) throws IOException {
        if (dangNhap()) {
            OpenForm form = new OpenForm();
            FXMLLoader loader = form.open("TrangChu.fxml", "Trang chủ");
            ((Node) (event.getSource())).getScene().getWindow().hide();
            TrangChuController controller = loader.getController();
            controller.setNhanVien(nv);
        }
    }

    @FXML
    public void resetForm(ActionEvent event) {
        txtUserName.setText("");
        txtPassword.setText("");
        lbError.setText("");
    }

    @FXML
    private void onKeyPress(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            if (dangNhap()) {
                OpenForm form = new OpenForm();
                FXMLLoader loader = form.open("TrangChu.fxml", "Trang chủ");
                ((Node) (event.getSource())).getScene().getWindow().hide();
                TrangChuController controller = loader.getController();
                controller.setNhanVien(nv);
            }
        }
    }

    private boolean validateData() {
        if (txtUserName.getText().trim().equals("")
                || txtPassword.getText().trim().equals("")) {
            lbError.setText("Vui lòng điền đầy đủ Tên đăng nhập/ Mật khẩu");
            return false;
        }
        return true;
    }

    private boolean dangNhap() {
        try {
            if (validateData()) {
                String userName = txtUserName.getText().trim().toLowerCase();
                String passWord = MD5Encrypt.md5(txtPassword.getText().trim());

                String query = "SELECT Ho, TenLot, Ten FROM nhan_vien "
                        + "WHERE TenDangNhap = ? AND MatKhau = ? LIMIT 1";

                PreparedStatement prep = connect.prepareStatement(query);
                prep.setString(1, userName);
                prep.setString(2, passWord);
                ResultSet result = prep.executeQuery();
                if (result.next()) {
                    nv = new NhanVienDangNhapModel();
                    nv.setTenDangNhap(userName);
                    nv.setHo(result.getString("Ho"));
                    nv.setTenLot(result.getString("TenLot"));
                    nv.setTen(result.getString("Ten"));
                    connect.close();
                    return true;
                } else {
                    lbError.setText("Sai Tên đăng nhập/ Mật khẩu");
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }
}
