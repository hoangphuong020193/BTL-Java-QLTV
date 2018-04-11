/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien;

import commands.SachCommand;
import common.Dialog;
import common.OpenForm;
import entity.Sach;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import model.LoaiSachViewModel;
import model.NhaXuatBanViewModel;
import model.NhanVienDangNhapModel;
import model.SachViewModel;
import model.TacGiaViewModel;
import queries.LoaiSachQuery;
import queries.NhaXuatBanQuery;
import queries.SachQuery;
import queries.TacGiaQuery;

/**
 * FXML Controller class
 *
 * @author phuonghoangnguyen
 */
public class QuanLySachController implements Initializable {

    @FXML
    private TextField txtMaSach;
    @FXML
    private TextField txtTenSach;
    @FXML
    private ComboBox<LoaiSachViewModel> cbbLoaiSach;
    @FXML
    private ComboBox<TacGiaViewModel> cbbTacGia;
    @FXML
    private ComboBox<NhaXuatBanViewModel> cbbNXB;
    @FXML
    private TextField txtSoLuong;
    @FXML
    private DatePicker dpkNgayNhap;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnHuy;
    @FXML
    private ImageView imgHome;
    @FXML
    private TextField txtTimKiem;
    @FXML
    private CheckBox chkShowBookDelete;
    @FXML
    private TableView<SachViewModel> tbvSach;
    @FXML
    private TableColumn<SachViewModel, String> colMaSach;
    @FXML
    private TableColumn<SachViewModel, String> colTenSach;
    @FXML
    private TableColumn<SachViewModel, String> colLoaiSach;
    @FXML
    private TableColumn<SachViewModel, String> colKieuSach;
    @FXML
    private TableColumn<SachViewModel, String> colTacGia;
    @FXML
    private TableColumn<SachViewModel, String> colNXB;
    @FXML
    private TableColumn<SachViewModel, Date> colNgayNhap;
    @FXML
    private TableColumn<SachViewModel, Integer> colSoLuong;

    private NhanVienDangNhapModel nhanVien;
    private SachViewModel sachSelected = new SachViewModel();
    private String searchString = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCBBLoaiSach();
        initCBBTacGia();
        initCBBNhaXuatBan();
        initTableViewSach();
    }

    public void setNhanVien(NhanVienDangNhapModel nv) {
        nhanVien = nv;
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

    @FXML
    private void onKeyPressSearch(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            searchString = txtTimKiem.getText();
            getListSach();
        }
    }

    @FXML
    private void onSelectSach(MouseEvent event) {
        sachSelected = tbvSach.getSelectionModel().getSelectedItem();
        btnSave.setText("Lưu");
        txtMaSach.setText(sachSelected.getMaSach());
        txtTenSach.setText(sachSelected.getTenSach());
        txtSoLuong.setText(Integer.toString(sachSelected.getSoLuong()));

        for (LoaiSachViewModel loaiSach : cbbLoaiSach.getItems()) {
            if (loaiSach.getId() == sachSelected.getIdLoaiSach()) {
                cbbLoaiSach.setValue(loaiSach);
                break;
            }
        }

        for (TacGiaViewModel tacGia : cbbTacGia.getItems()) {
            if (tacGia.getId() == sachSelected.getIdTacGia()) {
                cbbTacGia.setValue(tacGia);
                break;
            }
        }

        for (NhaXuatBanViewModel nxb : cbbNXB.getItems()) {
            if (nxb.getId() == sachSelected.getIdNXB()) {
                cbbNXB.setValue(nxb);
                break;
            }
        }
        dpkNgayNhap.setValue(sachSelected.getNgayNhap().toLocalDate());
    }

    @FXML
    private void onClickCheckboxHienSach(MouseEvent event) {
        getListSach();
    }

    @FXML
    private void onClickSave(ActionEvent event) {
        Sach sach = new Sach();
        sach.setId(sachSelected.getId());
        sach.setMaSach(txtMaSach.getText().trim());
        sach.setTenSach(txtTenSach.getText().trim());
        sach.setIdLoaiSach(cbbLoaiSach.getValue() != null ? cbbLoaiSach.getValue().getId() : 0);
        sach.setIdTacGia(cbbTacGia.getValue() != null ? cbbTacGia.getValue().getId() : 0);
        sach.setIdNXB(cbbNXB.getValue() != null ? cbbNXB.getValue().getId() : 0);
        sach.setSoLuong(!txtSoLuong.getText().equals("") ? Integer.parseInt(txtSoLuong.getText()) : 0);
        sach.setNgayNhap(dpkNgayNhap.getValue() != null ? Date.valueOf(dpkNgayNhap.getValue()) : null);
        if (validateData(sach)) {
            if (sach.getId() == 0) {
                if (SachCommand.ThemSach(sach)) {
                    getListSach();
                    Dialog.infoBox("Thêm sách thành công", "Thành công", null);
                    resetControl();
                }
            } else {
                if (SachCommand.SuaSach(sach)) {
                    getListSach();
                    Dialog.infoBox("Sửa sách thành công", "Thành công", null);
                    resetControl();
                }
            }
        }
    }

    @FXML
    private void onClickHuy(ActionEvent event) {
        resetControl();
    }

    private void initCBBLoaiSach() {
        ObservableList<LoaiSachViewModel> listLoai = LoaiSachQuery.getLoaiSach();
        cbbLoaiSach.setItems(listLoai);

        Callback<ListView<LoaiSachViewModel>, ListCell<LoaiSachViewModel>> factory = lv -> new ListCell<LoaiSachViewModel>() {

            @Override
            protected void updateItem(LoaiSachViewModel item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getTenLoaiSach());
            }
        };

        cbbLoaiSach.setCellFactory(factory);
        cbbLoaiSach.setButtonCell(factory.call(null));
    }

    private void initCBBTacGia() {
        ObservableList<TacGiaViewModel> listTacGia = TacGiaQuery.getTacGia();
        cbbTacGia.setItems(listTacGia);
        Callback<ListView<TacGiaViewModel>, ListCell<TacGiaViewModel>> factory = lv -> new ListCell<TacGiaViewModel>() {

            @Override
            protected void updateItem(TacGiaViewModel item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getTacGia());
            }
        };

        cbbTacGia.setCellFactory(factory);
        cbbTacGia.setButtonCell(factory.call(null));
    }

    private void initCBBNhaXuatBan() {
        ObservableList<NhaXuatBanViewModel> listNXB = NhaXuatBanQuery.getNXB();
        cbbNXB.setItems(listNXB);

        Callback<ListView<NhaXuatBanViewModel>, ListCell<NhaXuatBanViewModel>> factory = lv -> new ListCell<NhaXuatBanViewModel>() {

            @Override
            protected void updateItem(NhaXuatBanViewModel item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getTenNXB());
            }
        };

        cbbNXB.setCellFactory(factory);
        cbbNXB.setButtonCell(factory.call(null));
    }

    private void initTableViewSach() {
        colMaSach.setCellValueFactory(new PropertyValueFactory<>("MaSach"));
        colTenSach.setCellValueFactory(new PropertyValueFactory<>("TenSach"));
        colLoaiSach.setCellValueFactory(new PropertyValueFactory<>("TenLoaiSach"));
        colKieuSach.setCellValueFactory(new PropertyValueFactory<>("KieuSach"));
        colTacGia.setCellValueFactory(new PropertyValueFactory<>("TenTacGia"));
        colNXB.setCellValueFactory(new PropertyValueFactory<>("TenNXB"));
        colNgayNhap.setCellValueFactory(new PropertyValueFactory<>("NgayNhap"));
        colSoLuong.setCellValueFactory(new PropertyValueFactory<>("SoLuong"));

        TableColumn<SachViewModel, SachViewModel> colXoa = new TableColumn<>("");
        colXoa.setSortable(false);
        colXoa.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        colXoa.setCellFactory(
                new Callback<TableColumn<SachViewModel, SachViewModel>, TableCell<SachViewModel, SachViewModel>>() {

            @Override
            public TableCell<SachViewModel, SachViewModel> call(TableColumn<SachViewModel, SachViewModel> p) {
                return new ButtonCell();
            }
        });

        tbvSach.getColumns().add(colXoa);
        getListSach();
    }

    private class ButtonCell extends TableCell<SachViewModel, SachViewModel> {

        final Button cellButton = new Button("Xoá");

        ButtonCell() {

            cellButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    Dialog.confirmBox("Bạn có muốn xoá độc giả này?", "Xóa", null)
                            .ifPresent(response -> {
                                if (response == ButtonType.OK) {
                                    SachViewModel sach = (SachViewModel) ButtonCell.this.getTableView()
                                            .getItems()
                                            .get(ButtonCell.this.getIndex());
                                    if (SachCommand.XoaSach(sach.getId())) {
                                        Dialog.infoBox("Xoá sách thành công", "Xoá", null);
                                        getListSach();
                                    }
                                }
                            });
                }
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(SachViewModel t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty && !t.isXoa()) {
                setGraphic(cellButton);
            } else {
                setGraphic(null);
            }
        }
    }

    private void getListSach() {
        ObservableList<SachViewModel> listSach
                = SachQuery.getSach(searchString, chkShowBookDelete.isSelected());
        tbvSach.setItems(listSach);
    }

    private boolean validateData(Sach sach) {
        if (sach.getMaSach().equals("")
                || sach.getTenSach().equals("")
                || sach.getIdLoaiSach() == 0
                || sach.getIdTacGia() == 0
                || sach.getIdNXB() == 0
                || sach.getSoLuong() <= 0
                || sach.getNgayNhap() == null) {
            Dialog.errorBox("Tất cả các trường phải được nhập", "Lỗi", null);
            return false;
        }

        if (SachQuery.CheckMaSach(sach.getMaSach().trim(), sach.getId())) {
            Dialog.errorBox("Mã sách đã tồn tại", "Lỗi", null);
            return false;
        }
        return true;
    }

    private void resetControl() {
        txtMaSach.setText("");
        txtTenSach.setText("");
        txtSoLuong.setText("");
        cbbLoaiSach.valueProperty().set(null);
        cbbTacGia.valueProperty().set(null);
        cbbNXB.valueProperty().set(null);
        dpkNgayNhap.setValue(null);
        sachSelected = new SachViewModel();
        btnSave.setText("Tạo");
    }
}
