/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien;

import data.ConnectionContext;
import entity.TacGia;
import entity.TimKiem;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.NhanVienDangNhapModel;

/**
 * FXML Controller class
 *
 * @author phuonghoangnguyen
 */
public class TraCuuController implements Initializable {

    @FXML
    private TextField txtMaSach;
    @FXML
    private ComboBox cbbTacGia;
    @FXML
    private TextField txtTenSach;
    @FXML
    private ComboBox cbbLoaiSach;
    @FXML
    private DatePicker DPNhayNhap;
    @FXML
    private ComboBox cbbNhaXuatBan;
    @FXML
    private Button btnTimKiem;
    @FXML
    private Button btnXoa;
    @FXML
    private TableView<TimKiem> tbvSach;
    @FXML
    private TableColumn<TimKiem, String> colMaSach;
    @FXML
    private TableColumn<TimKiem, String> colTenSach;
    @FXML
    private TableColumn<TimKiem, String> colLoaiSach;
    @FXML
    private TableColumn<TimKiem, String> colTacGia;
    @FXML
    private TableColumn<TimKiem, String> colNXB;
    @FXML
    private TableColumn<TimKiem, Date> colNgayNhap;

    private NhanVienDangNhapModel nhanVien;
    private Connection connect;
    private TimKiem TimKiemSelected;
    private String maSach = "";
    private String tenSach = "";

//    private String searchString = "";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connect = ConnectionContext.ketNoi();
        initTableView();
        setComboboxValuesTG();
        setComboboxValuesLS();
        setComboboxValuesNXB();
    }

    public void setNhanVien(NhanVienDangNhapModel nv) {
        nhanVien = nv;
    }

    private void setComboboxValuesTG() {
        try {
            String query = "SELECT * FROM tac_gia";
            PreparedStatement pre = connect.prepareStatement(query);
            //           pre.setString(1, (String)cbbTacGia.getSelectionModel().getSelectedItem());
            ResultSet rs = pre.executeQuery();
            ObservableList<String> listCbbTacGia = FXCollections.observableArrayList();
            while (rs.next()) {
                listCbbTacGia.add(rs.getString("TenTacGia"));
            }
            cbbTacGia.setItems(listCbbTacGia);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    private void setComboboxValuesLS() {
        try {
            String query = "SELECT * FROM loai_sach";
            PreparedStatement pre = connect.prepareStatement(query);
            //           pre.setString(1, (String)cbbTacGia.getSelectionModel().getSelectedItem());
            ResultSet rs = pre.executeQuery();
            ObservableList<String> listCbbLoaiSach = FXCollections.observableArrayList();
            while (rs.next()) {
                listCbbLoaiSach.add(rs.getString("TenLoaiSach"));
            }
            cbbLoaiSach.setItems(listCbbLoaiSach);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    private void setComboboxValuesNXB() {
        try {
            String query = "SELECT * FROM nha_xuat_ban";
            PreparedStatement pre = connect.prepareStatement(query);
            //           pre.setString(1, (String)cbbTacGia.getSelectionModel().getSelectedItem());
            ResultSet rs = pre.executeQuery();
            ObservableList<String> listCbbNXB = FXCollections.observableArrayList();
            while (rs.next()) {
                listCbbNXB.add(rs.getString("TenNXB"));
            }
            cbbNhaXuatBan.setItems(listCbbNXB);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    @FXML
    private void onClickTimKiem(ActionEvent event) {
        getListSearch();
    }

    private void initTableView() {
        colTenSach.setCellValueFactory(new PropertyValueFactory<>("TenSach"));
        colMaSach.setCellValueFactory(new PropertyValueFactory<>("MaSach"));
        colTacGia.setCellValueFactory(new PropertyValueFactory<>("TenTacGia"));
        colLoaiSach.setCellValueFactory(new PropertyValueFactory<>("TenLoaiSach"));
        colNXB.setCellValueFactory(new PropertyValueFactory<>("TenNXB"));
        colNgayNhap.setCellValueFactory(new PropertyValueFactory<>("NgayNhap"));

        getListSearch();
    }

    private void getListSearch() {
        String query = "SELECT MaSach, TenSach, TenLoaiSach, TenTacGia, TenNXB, NgayNhap FROM tac_gia tg, loai_sach ls, nha_xuat_ban nxb, sach WHERE tg.Id = sach.IdTacGia and ls.Id = sach.IdLoaiSach and nxb.Id = sach.IdNXB";

        try {
            PreparedStatement prep = connect.prepareStatement(query);
//            if (!searchString.equals("")) {
//                prep.setString(1, "%" + searchString + "%");
//                prep.setString(2, "%" + searchString + "%");
//                prep.setString(3, "%" + searchString + "%");
//                prep.setString(4, "%" + searchString + "%");
//                prep.setString(5, searchString);
//            }
            ResultSet result = prep.executeQuery();
            ObservableList<TimKiem> listSach = FXCollections.observableArrayList();
            while (result.next()) {
                TimKiem s = new TimKiem();
                s.setMaSach(result.getString("MaSach"));
                s.setTenSach(result.getString("TenSach"));
                s.setTenTacGia(result.getString("TenTacGia"));
                s.setTenLoaiSach(result.getString("TenLoaiSach"));
                s.setTenNXB(result.getString("TenNXB"));
                s.setNgayNhap(result.getDate("NgayNhap"));

                listSach.add(s);
            }

            tbvSach.setItems(listSach);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
