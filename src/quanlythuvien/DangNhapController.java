/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien;

import common.OpenForm;
import data.ConnectionContext;
import helper.MD5Encrypt;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.NhanVienDangNhap;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connect = ConnectionContext.ketNoi();
    }

    @FXML
    public void loginSubmit(ActionEvent event) throws SQLException {
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
                    NhanVienDangNhap nv = new NhanVienDangNhap();
                    nv.setTenDangNhap(userName);
                    nv.setHo(result.getString("Ho"));
                    nv.setTenLot(result.getString("TenLot"));
                    nv.setTen(result.getString("Ten"));

                    OpenForm form = new OpenForm();
                    FXMLLoader loader = form.open("TrangChu.fxml", "Trang chủ");
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                    TrangChuController controller = loader.getController();
                    controller.setNhanVien(nv);

                    connect.close();
                } else {
                    lbError.setText("Sai Tên đăng nhập/ Mật khẩu");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @FXML
    public void resetForm(ActionEvent event) {
        txtUserName.setText("");
        txtPassword.setText("");
        lbError.setText("");
    }

    private boolean validateData() {
        if (txtUserName.getText().trim().equals("")
                || txtPassword.getText().trim().equals("")) {
            lbError.setText("Vui lòng điền đầy đủ Tên đăng nhập/ Mật khẩu");
            return false;
        }
        return true;
    }
}
