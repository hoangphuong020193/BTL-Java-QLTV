/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien;

import commands.MuonSachCommand;
import common.Dialog;
import common.OpenForm;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DocGiaViewModel;
import model.NhanVienDangNhapModel;
import model.SachMuonViewModel;
import model.SachViewModel;
import queries.SachQuery;

/**
 * FXML Controller class
 *
 * @author phuonghoangnguyen
 */
public class MuonSachController implements Initializable {

    @FXML
    private TextField txtMaDG;
    @FXML
    private TextField txtTenDG;
    @FXML
    private Button btnTImDocGia;
    @FXML
    private Button btnTaoPhieuMuon;
    @FXML
    private TableView<SachViewModel> tbvSachKhaDung;
    @FXML
    private TableColumn<SachViewModel, String> colMaSachKhaDung;
    @FXML
    private TableColumn<SachViewModel, String> colTenSachKhaDung;
    @FXML
    private TableColumn<SachViewModel, Integer> colSoLuongKhaDung;
    @FXML
    private TableColumn<SachViewModel, String> colLoaiSach;
    @FXML
    private TableColumn<SachViewModel, String> colKieuSach;
    @FXML
    private Button btnThem;
    @FXML
    private TableView<SachMuonViewModel> tbvSachMuon;
    @FXML
    private TableColumn<SachMuonViewModel, String> colMaSachMuon;
    @FXML
    private TableColumn<SachMuonViewModel, String> colTenSachMuon;
    @FXML
    private Button btnHuy;
    @FXML
    private Button btnBot;

    private NhanVienDangNhapModel nhanVien;
    private DocGiaViewModel docGiaSelected;
    private ObservableList<SachViewModel> listSachKhaDung
            = FXCollections.observableArrayList();
    private ObservableList<SachMuonViewModel> listSachMuon
            = FXCollections.observableArrayList();
    private SachViewModel sachKhaDungSelected;
    private SachMuonViewModel sachMuonSelected;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTableViewSachKhaDung();
        inintTableViewSachMuon();
    }

    public void setNhanVien(NhanVienDangNhapModel nv) {
        nhanVien = nv;
    }

    @FXML
    private void onSelectChonSachKhaDung(MouseEvent event) {
        sachKhaDungSelected = tbvSachKhaDung.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void onSelectSachMuon(MouseEvent event) {
        sachMuonSelected = tbvSachMuon.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void onTimDocGia(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        URL u = getClass().getClassLoader().getResource("quanlythuvien/TimDocGiaPopup.fxml");
        FXMLLoader loader = new FXMLLoader(u);
        Parent root = loader.load();

        TimDocGiaPopupController controller = loader.getController();
        controller.currentDocGiaProperty().addListener((obs, oldDG, newDG) -> {
            setDocGia(newDG);
        });

        Scene scene = new Scene(root);
        stage.setTitle("Tìm độc giả");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(root.getScene().getWindow());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    private void onClickHuy(ActionEvent event) {
        txtMaDG.setText("");
        txtTenDG.setText("");
        btnTaoPhieuMuon.setDisable(true);
        listSachMuon.removeAll(listSachMuon);
        btnBot.setDisable(true);
        btnThem.setDisable(true);
        getListSachKhaDung();
        docGiaSelected = new DocGiaViewModel();
        sachKhaDungSelected = new SachViewModel();
        sachMuonSelected = new SachMuonViewModel();
    }

    @FXML
    private void btnTaoPhieuMuon(ActionEvent event) throws SQLException {
        if (docGiaSelected != null && listSachMuon.size() > 0) {

            Dialog.confirmBox("Bạn muốn tạo phiếu mượn cho "
                    + docGiaSelected.getHoTenDG(), "Tạo phiếu mượn", null)
                    .ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            try {
                                if (MuonSachCommand.taoPhieuMuon(docGiaSelected,
                                        listSachMuon)) {
                                    Dialog.infoBox("Tạo phiếu mượn thành công",
                                            "Thành công", null);
                                    this.onClickHuy(event);
                                } else {
                                    Dialog.errorBox("Tạo phiếu mượn thất bại",
                                            "Thất bại", null);
                                }
                            } catch (SQLException ex) {
                                System.out.println("Error: " + ex);
                            }
                        }
                    });
        } else {
            Dialog.errorBox("Phải chọn ít nhất 1 quyển sách", "Lỗi", null);
        }
    }

    @FXML
    private void onClickThem(ActionEvent event) {
        if (sachKhaDungSelected != null) {

            if (sachKhaDungSelected.getSoLuong() > 0) {

                SachMuonViewModel sachMuon = new SachMuonViewModel();
                sachMuon.setIdSach(sachKhaDungSelected.getId());
                sachMuon.setMaSach(sachKhaDungSelected.getMaSach());
                sachMuon.setTenSach(sachKhaDungSelected.getTenSach());

                boolean isTonTai = false;

                for (SachMuonViewModel item : listSachMuon) {
                    if (item.getMaSach() == sachMuon.getMaSach()) {
                        isTonTai = true;
                    }
                }

                if (isTonTai) {
                    Dialog.infoBox("Sách đã tồn tại.", "Đã tồn tại", null);
                } else {
                    listSachMuon.add(sachMuon);

                    for (SachViewModel item : listSachKhaDung) {
                        if (item.getId() == sachMuon.getIdSach()) {
                            item.setSoLuong(item.getSoLuong() - 1);
                        }
                    }
                    tbvSachKhaDung.refresh();
                }
                sachKhaDungSelected = new SachViewModel();
            }
        } else {
            Dialog.infoBox("Sách đã hết số lượng được mượn.", "Hết sách", null);
        }
    }

    @FXML
    private void onClickBot(ActionEvent event) {
        if (sachMuonSelected != null) {
            for (SachMuonViewModel item : listSachMuon) {
                if (item.getIdSach() == sachMuonSelected.getIdSach()) {
                    listSachMuon.remove(item);
                    break;
                }
            }
            tbvSachMuon.refresh();

            for (SachViewModel item : listSachKhaDung) {
                if (item.getId() == sachMuonSelected.getIdSach()) {
                    item.setSoLuong(item.getSoLuong() + 1);
                }
            }
            tbvSachKhaDung.refresh();
            sachMuonSelected = new SachMuonViewModel();
        }
    }

    @FXML
    private void onClickBackHome(MouseEvent event) {
        try {
            OpenForm form = new OpenForm();
            FXMLLoader loader = form.open("TrangChu.fxml", "Trang chủ");
            ((Node) (event.getSource())).getScene().getWindow().hide();
            TrangChuController controller = loader.getController();
            controller.setNhanVien(nhanVien);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private void initTableViewSachKhaDung() {
        colMaSachKhaDung.setCellValueFactory(new PropertyValueFactory<>("maSach"));
        colTenSachKhaDung.setCellValueFactory(new PropertyValueFactory<>("tenSach"));
        colLoaiSach.setCellValueFactory(new PropertyValueFactory<>("tenLoaiSach"));
        colKieuSach.setCellValueFactory(new PropertyValueFactory<>("kieuSach"));
        colSoLuongKhaDung.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        colSoLuongKhaDung.setEditable(true);

        getListSachKhaDung();
    }

    private void inintTableViewSachMuon() {
        colMaSachMuon.setCellValueFactory(new PropertyValueFactory<>("maSach"));
        colTenSachMuon.setCellValueFactory(new PropertyValueFactory<>("tenSach"));

        tbvSachMuon.setItems(listSachMuon);
    }

    private void getListSachKhaDung() {
        listSachKhaDung = SachQuery.getSachKhaDung("");
        tbvSachKhaDung.setItems(listSachKhaDung);
    }

    private void setDocGia(DocGiaViewModel docGia) {
        docGiaSelected = docGia;
        txtMaDG.setText(docGia.getMaDocGia());
        txtTenDG.setText(docGia.getHoTenDG());
        btnThem.setDisable(false);
        btnBot.setDisable(false);
        btnTaoPhieuMuon.setDisable(false);
    }
}
