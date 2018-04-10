/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien;

import commands.SachCommand;
import common.Dialog;
import common.OpenForm;
import java.net.URL;
import java.util.Date;
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
    private SachViewModel sachSelected;
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
    }

    @FXML
    private void onClickCheckboxHienSach(MouseEvent event) {
        getListSach();
    }

    @FXML
    private void onClickSave(ActionEvent event) {
    }

    @FXML
    private void onClickHuy(ActionEvent event) {
        txtMaSach.setText("");
        txtTenSach.setText("");
        txtSoLuong.setText("");
        cbbLoaiSach.valueProperty().set(null);
        cbbTacGia.valueProperty().set(null);
        cbbNXB.valueProperty().set(null);
        dpkNgayNhap.setValue(null);
        btnSave.setText("Tạo");
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
}
