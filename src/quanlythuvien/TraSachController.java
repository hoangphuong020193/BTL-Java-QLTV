/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien;

import commands.SachCommand;
import commands.TraSachCommand;
import common.Dialog;
import common.OpenForm;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import model.NhanVienDangNhapModel;
import model.PhieuMuonViewModel;
import model.SachMuonViewModel;
import queries.TraSachQuery;

/**
 * FXML Controller class
 *
 * @author phuonghoangnguyen
 */
public class TraSachController implements Initializable {

    @FXML
    private TextField txtMaDG;
    @FXML
    private TextField txtTenDG;
    @FXML
    private DatePicker dpkNgayMuon;
    @FXML
    private Button btnTim;
    @FXML
    private TableView<PhieuMuonViewModel> tbvPhieuMuon;
    @FXML
    private TableColumn<PhieuMuonViewModel, String> colMaDG;
    @FXML
    private TableColumn<PhieuMuonViewModel, String> colTenDG;
    @FXML
    private TableColumn<PhieuMuonViewModel, Date> colNgayMuon;
    @FXML
    private TableColumn<PhieuMuonViewModel, Date> colNgayTra;
    @FXML
    private TableView<SachMuonViewModel> tbvSachMuon;
    @FXML
    private TableColumn<SachMuonViewModel, String> colMaSach;
    @FXML
    private TableColumn<SachMuonViewModel, String> colTenSach;

    private NhanVienDangNhapModel nhanVien;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTableViewPhieuMuon();
        initTableViewSachMuon();
    }

    public void setNhanVien(NhanVienDangNhapModel nv) {
        nhanVien = nv;
    }

    @FXML
    private void onClickTim(ActionEvent event) {
        getListPhieuMuon();
    }

    @FXML
    private void onSelectPhieuMuon(MouseEvent event) {
        PhieuMuonViewModel phieuMuon = tbvPhieuMuon.getSelectionModel().getSelectedItem();
        getListSachMuon(phieuMuon.getId());
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

    private class ButtonCell extends TableCell<PhieuMuonViewModel, PhieuMuonViewModel> {

        final Button cellButton = new Button("Trả sách");

        ButtonCell() {

            cellButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    Dialog.confirmBox("Bạn có muốn hoàn tất phiếu mượn?", "Trả sách", null)
                            .ifPresent(response -> {
                                if (response == ButtonType.OK) {
                                    PhieuMuonViewModel phieuMuon = (PhieuMuonViewModel) ButtonCell.this.getTableView()
                                            .getItems()
                                            .get(ButtonCell.this.getIndex());
                                    if (TraSachCommand.traSach(phieuMuon.getId())) {
                                        Dialog.infoBox("Trả sách thành công", "Trả sách", null);
                                        getListPhieuMuon();
                                    }
                                }
                            });
                }
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(PhieuMuonViewModel t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty && t.getNgayTra() == null) {
                setGraphic(cellButton);
            } else {
                setGraphic(null);
            }
        }
    }

    private void initTableViewPhieuMuon() {
        colMaDG.setCellValueFactory(new PropertyValueFactory<>("maDocGia"));
        colTenDG.setCellValueFactory(new PropertyValueFactory<>("tenDocGia"));
        colNgayMuon.setCellValueFactory(new PropertyValueFactory<>("ngayMuon"));
        colNgayTra.setCellValueFactory(new PropertyValueFactory<>("ngayTra"));

        colNgayMuon.getSortType();

        TableColumn<PhieuMuonViewModel, PhieuMuonViewModel> colTra = new TableColumn<>("");
        colTra.setSortable(false);
        colTra.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        colTra.setCellFactory(
                new Callback<TableColumn<PhieuMuonViewModel, PhieuMuonViewModel>, TableCell<PhieuMuonViewModel, PhieuMuonViewModel>>() {

            @Override
            public TableCell<PhieuMuonViewModel, PhieuMuonViewModel> call(TableColumn<PhieuMuonViewModel, PhieuMuonViewModel> p) {
                return new ButtonCell();
            }
        });

        tbvPhieuMuon.getColumns().add(colTra);
        getListPhieuMuon();
    }

    private void initTableViewSachMuon() {
        colMaSach.setCellValueFactory(new PropertyValueFactory<>("maSach"));
        colTenSach.setCellValueFactory(new PropertyValueFactory<>("tenSach"));
        getListPhieuMuon();
    }

    private void getListPhieuMuon() {
        String maDG = txtMaDG.getText().trim();
        String tenDG = txtTenDG.getText().trim();
        Date ngayMuon = dpkNgayMuon.getValue() != null
                ? Date.valueOf(dpkNgayMuon.getValue()) : null;

        ObservableList<PhieuMuonViewModel> listSach
                = TraSachQuery.getPhieuMuon(maDG, tenDG, ngayMuon);
        tbvPhieuMuon.setItems(listSach);
    }

    private void getListSachMuon(int idPhieuMuon) {
        ObservableList<SachMuonViewModel> listSachMuon
                = TraSachQuery.getSachMuon(idPhieuMuon);
        tbvSachMuon.setItems(listSachMuon);
    }
}
