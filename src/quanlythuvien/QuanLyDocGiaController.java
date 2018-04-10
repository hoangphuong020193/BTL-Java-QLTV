/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien;

import common.Dialog;
import common.OpenForm;
import data.ConnectionContext;
import entity.DocGia;
import helper.VNCharacterUtils;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import model.NhanVienDangNhapModel;

/**
 * FXML Controller class
 *
 * @author phuonghoangnguyen
 */
public class QuanLyDocGiaController implements Initializable {

    @FXML
    private TextField txtMaDG;
    @FXML
    private TextField txtHo;
    @FXML
    private TextField txtTenLot;
    @FXML
    private TextField txtTen;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txtTimKiem;
    @FXML
    private TableView<DocGia> tbvDocGia;
    @FXML
    private TableColumn<DocGia, String> colMaDG;
    @FXML
    private TableColumn<DocGia, String> colHo;
    @FXML
    private TableColumn<DocGia, String> colTenLot;
    @FXML
    private TableColumn<DocGia, String> colTen;
    @FXML
    private TableColumn<DocGia, Date> colNgayTao;
    @FXML
    private CheckBox chkShowDGDeleted;

    private Connection connect;
    private NhanVienDangNhapModel nhanVien;
    private DocGia docGiaSelected;
    private String maDG = "";
    private String ho = "";
    private String tenLot = "";
    private String ten = "";
    private String searchString = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connect = ConnectionContext.ketNoi();
        initTableView();
    }

    public void setNhanVien(NhanVienDangNhapModel nv) {
        nhanVien = nv;
    }

    @FXML
    private void onClickSave(ActionEvent event) {
        if (maDG.equals("")) {
            taoMoiDG();
        } else {
            chinhSuaDG();
        }
    }

    @FXML
    private void onKeyPressSearch(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            searchString = txtTimKiem.getText();
            getListDocGia();
        }
    }

    @FXML
    private void onClickHuy(ActionEvent event) {
        txtMaDG.setText("");
        txtHo.setText("");
        txtTenLot.setText("");
        txtTen.setText("");
        btnSave.setText("Tạo");
    }

    @FXML
    private void onClickBackHome(MouseEvent event) throws SQLException {
        try {
            OpenForm form = new OpenForm();
            FXMLLoader loader = form.open("TrangChu.fxml", "Trang chủ");
            ((Node) (event.getSource())).getScene().getWindow().hide();
            TrangChuController controller = loader.getController();
            controller.setNhanVien(nhanVien);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            connect.close();
        }
    }

    @FXML
    private void onSelectDocGia(MouseEvent event) {
        docGiaSelected = tbvDocGia.getSelectionModel().getSelectedItem();
        btnSave.setText("Lưu");
        txtMaDG.setText(docGiaSelected.getMaDocGia());
        txtHo.setText(docGiaSelected.getHo());
        txtTenLot.setText(docGiaSelected.getTenLot());
        txtTen.setText(docGiaSelected.getTen());
    }

    @FXML
    private void onClickCheckboxHienDG(MouseEvent event) {
        getListDocGia();
    }

    private void initTableView() {
        colMaDG.setCellValueFactory(new PropertyValueFactory<>("MaDocGia"));
        colHo.setCellValueFactory(new PropertyValueFactory<>("Ho"));
        colTenLot.setCellValueFactory(new PropertyValueFactory<>("TenLot"));
        colTen.setCellValueFactory(new PropertyValueFactory<>("Ten"));
        colNgayTao.setCellValueFactory(new PropertyValueFactory<>("NgayTao"));

        TableColumn<DocGia, DocGia> colXoa = new TableColumn<>("");
        colXoa.setSortable(false);
        colXoa.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        colXoa.setCellFactory(
                new Callback<TableColumn<DocGia, DocGia>, TableCell<DocGia, DocGia>>() {

            @Override
            public TableCell<DocGia, DocGia> call(TableColumn<DocGia, DocGia> p) {
                return new ButtonCell();
            }
        });

        tbvDocGia.getColumns().add(colXoa);
        getListDocGia();
    }

    private void getListDocGia() {
        String query = "SELECT * FROM doc_gia";

        if (!chkShowDGDeleted.isSelected()) {
            query = query + " WHERE Xoa = 0 ";
        }

        if (!searchString.equals("")) {

            if (chkShowDGDeleted.isSelected()) {
                query = query + " WHERE ";
            } else {
                query = query + " AND ";
            }

            query = query + " (MaDocGia LIKE ? "
                    + "OR Ho LIKE ? "
                    + "OR TenLot LIKE ? "
                    + "OR Ten LIKE ? "
                    + "OR CONCAT(Ho, ' ', TenLot, ' ', Ten) = ?) ";
        }

        try {
            PreparedStatement prep = connect.prepareStatement(query);
            if (!searchString.equals("")) {
                prep.setString(1, "%" + searchString + "%");
                prep.setString(2, "%" + searchString + "%");
                prep.setString(3, "%" + searchString + "%");
                prep.setString(4, "%" + searchString + "%");
                prep.setString(5, searchString);
            }
            ResultSet result = prep.executeQuery();
            ObservableList<DocGia> listDocGia = FXCollections.observableArrayList();
            while (result.next()) {
                DocGia dg = new DocGia();
                dg.setId(result.getInt("Id"));
                dg.setHo(result.getString("Ho"));
                dg.setTenLot(result.getString("TenLot"));
                dg.setTen(result.getString("Ten"));
                dg.setMaDocGia(result.getString("MaDocGia"));
                dg.setNgayTao(result.getDate("NgayTao"));
                dg.setXoa(result.getBoolean("Xoa"));
                listDocGia.add(dg);
            }

            tbvDocGia.setItems(listDocGia);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private boolean validateData() {
        ho = txtHo.getText().trim();
        tenLot = txtTenLot.getText().trim();
        ten = txtTen.getText().trim();
        if (ten.equals("") || ten.equals("")) {
            Dialog.errorBox("Họ/ Tên không được trống", "Lỗi", "Lỗi");
            return false;
        }
        return true;
    }

    private void taoMoiDG() {
        String query = "INSERT INTO doc_gia (MaDocGia, Ho, TenLot, Ten, Xoa) "
                + "VALUES (?,?,?,?,?)";
        try {
            if (validateData()) {
                PreparedStatement prep = connect.prepareStatement(query);

                maDG = ("DG-" + VNCharacterUtils.removeAccent(ho + tenLot + ten)
                        + "-" + (new Date()).getTime()).replace(" ", "");

                prep.setString(1, maDG);
                prep.setString(2, ho);
                prep.setString(3, tenLot);
                prep.setString(4, ten);
                prep.setInt(5, 0);
                prep.executeUpdate();
                getListDocGia();
                Dialog.infoBox("Thêm độc giả thành công", "Thêm độc giả", null);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private void chinhSuaDG() {
        String query = "UPDATE doc_gia SET "
                + "Ho = ?, "
                + "TenLot = ?, "
                + "Ten = ? "
                + "WHERE Id = ?";
        try {
            if (validateData()) {
                PreparedStatement prep = connect.prepareStatement(query);
                prep.setString(1, ho);
                prep.setString(2, tenLot);
                prep.setString(3, ten);
                prep.setInt(4, docGiaSelected.getId());
                prep.executeUpdate();
                getListDocGia();
                Dialog.infoBox("Chỉnh sửa độc giả thành công", "Sửa độc giả", null);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private class ButtonCell extends TableCell<DocGia, DocGia> {

        final Button cellButton = new Button("Xoá");

        ButtonCell() {

            cellButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    Dialog.confirmBox("Bạn có muốn xoá độc giả này?", "Xóa", null)
                            .ifPresent(response -> {
                                if (response == ButtonType.OK) {
                                    docGiaSelected = (DocGia) ButtonCell.this.getTableView()
                                            .getItems()
                                            .get(ButtonCell.this.getIndex());
                                    XoaDG();
                                }
                            });
                }
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(DocGia t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty && !t.getXoa()) {
                setGraphic(cellButton);
            } else {
                setGraphic(null);
            }
        }
    }

    private void XoaDG() {
        String query = "UPDATE doc_gia SET Xoa = 1 WHERE Id = ?";
        try {
            PreparedStatement prep = connect.prepareStatement(query);
            prep.setInt(1, docGiaSelected.getId());
            prep.executeUpdate();
            getListDocGia();
            Dialog.infoBox("Xoá độc giả thành công", "Xoá", null);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
