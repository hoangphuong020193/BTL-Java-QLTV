/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien;

import commands.LoaiSachCommand;
import commands.NhaXuatBanCommand;
import commands.SachCommand;
import commands.TacGiaCommand;
import common.Dialog;
import common.OpenForm;
import entity.LoaiSach;
import entity.NhaXuatBan;
import entity.Sach;
import entity.TacGia;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
    @FXML
    private TextField txtLoaiSach;
    @FXML
    private TextField txtKieuSach;
    @FXML
    private Button btnSaveLoaiSach;
    @FXML
    private Button btnHuyLoaiSach;
    @FXML
    private TableView<LoaiSach> tbvLoaiSach;
    @FXML
    private TableColumn<LoaiSach, String> colLoaiSachLS;
    @FXML
    private TableColumn<LoaiSach, String> colKieuSachLS;
    @FXML
    private Button btnSaveTG;
    @FXML
    private Button btnHuyTG;
    @FXML
    private TableView<TacGia> tbvTacGia;
    @FXML
    private TableColumn<TacGia, String> colTenTacGiaTG;
    @FXML
    private TextField txtTenTacGiaTG;
    @FXML
    private TextField txtTenNXB;
    @FXML
    private TextField txtDiaChiNXB;
    @FXML
    private TextField txtSdtNXB;
    @FXML
    private Button btnSaveNXB;
    @FXML
    private Button btnHuyNXB;
    @FXML
    private TableView<NhaXuatBan> tbvNXB;
    @FXML
    private TableColumn<NhaXuatBan, String> colTenNXBNXB;
    @FXML
    private TableColumn<NhaXuatBan, String> colDiaChiNXB;
    @FXML
    private TableColumn<NhaXuatBan, String> colSdtNXB;

    private NhanVienDangNhapModel nhanVien;
    private SachViewModel sachSelected = new SachViewModel();
    private String searchString = "";
    private LoaiSach loaiSachSelected = new LoaiSach();
    private TacGia tacGiaSelected = new TacGia();
    private NhaXuatBan nxbSelected = new NhaXuatBan();
    private File anhSelected;
    
    @FXML
    private Button btnThemAnh;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCBBLoaiSach();
        initCBBTacGia();
        initCBBNhaXuatBan();
        initTableViewSach();
        initTableViewLoaiSach();
        initTableViewTacGia();
        initTableViewNXB();

        txtSoLuong.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtSoLuong.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public void setNhanVien(NhanVienDangNhapModel nv) {
        nhanVien = nv;
    }

    @FXML
    private void onChangeTabQLS(Event event) {
        getListSach();
        initCBBLoaiSach();
        initCBBTacGia();
        initCBBNhaXuatBan();
    }

    @FXML
    private void onChangeTabQLLS(Event event) {
        getListLoaiSach();
    }

    @FXML
    private void onChangeTabQLTG(Event event) {
        getListTacGia();
    }

    @FXML
    private void onChangeTabQLNXB(Event event) {
        getListNXB();
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
    private void onClickSaveSach(ActionEvent event) {
        Sach sach = new Sach();
        sach.setId(sachSelected.getId());
        sach.setMaSach(txtMaSach.getText().trim());
        sach.setTenSach(txtTenSach.getText().trim());
        sach.setIdLoaiSach(cbbLoaiSach.getValue() != null ? cbbLoaiSach.getValue().getId() : 0);
        sach.setIdTacGia(cbbTacGia.getValue() != null ? cbbTacGia.getValue().getId() : 0);
        sach.setIdNXB(cbbNXB.getValue() != null ? cbbNXB.getValue().getId() : 0);
        sach.setSoLuong(!txtSoLuong.getText().equals("") ? Integer.parseInt(txtSoLuong.getText()) : 0);
        sach.setNgayNhap(dpkNgayNhap.getValue() != null ? Date.valueOf(dpkNgayNhap.getValue()) : null);
        if (SachCommand.saveSach(sach)) {
            resetSach();
            getListSach();
        }
    }

    @FXML
    private void onClickHuySach(ActionEvent event) {
        resetSach();
    }

    @FXML
    private void onClickSaveLoaiSach(ActionEvent event) {
        loaiSachSelected.setTenLoaiSach(txtLoaiSach.getText().trim());
        loaiSachSelected.setKieuSach(txtKieuSach.getText().trim());
        if (LoaiSachCommand.saveLoaiSach(loaiSachSelected)) {
            getListLoaiSach();
            resetLoaiSach();
        }
    }

    @FXML
    private void onClickHuyLoaiSach(ActionEvent event) {
        resetLoaiSach();
    }

    @FXML
    private void onSelectLoaiSach(MouseEvent event) {
        loaiSachSelected = tbvLoaiSach.getSelectionModel().getSelectedItem();
        btnSaveLoaiSach.setText("Lưu");
        txtLoaiSach.setText(loaiSachSelected.getTenLoaiSach());
        txtKieuSach.setText(loaiSachSelected.getKieuSach());
    }

    @FXML
    private void onSelectTacGia(MouseEvent event) {
        tacGiaSelected = tbvTacGia.getSelectionModel().getSelectedItem();
        btnSaveTG.setText("Lưu");
        txtTenTacGiaTG.setText(tacGiaSelected.getTenTacGia());
    }

    @FXML
    private void onClickSaveTG(ActionEvent event) {
        tacGiaSelected.setTenTacGia(txtTenTacGiaTG.getText().trim());
        if (TacGiaCommand.saveTacGia(tacGiaSelected)) {
            getListTacGia();
            resetTacGia();
        }
    }

    @FXML
    private void onClickHuyTG(ActionEvent event) {
        resetTacGia();
    }

    @FXML
    private void onClickSaveNXB(ActionEvent event) {
        nxbSelected.setTenNXB(txtTenNXB.getText().trim());
        nxbSelected.setDiaChi(txtDiaChiNXB.getText().trim());
        nxbSelected.setSDT(txtSdtNXB.getText().trim());
        if (NhaXuatBanCommand.saveNXB(nxbSelected)) {
            getListNXB();
            resetNXB();
        }
    }

    @FXML
    private void onClickHuyNXB(ActionEvent event) {
        resetNXB();
    }

    @FXML
    private void onSelectNXB(MouseEvent event) {
        nxbSelected = tbvNXB.getSelectionModel().getSelectedItem();
        btnSaveNXB.setText("Lưu");
        txtTenNXB.setText(nxbSelected.getTenNXB());
        txtDiaChiNXB.setText(nxbSelected.getDiaChi());
        txtSdtNXB.setText(nxbSelected.getSDT());
    }

    @FXML
    private void onClickThemAnh(ActionEvent event) {
        try {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Chọn ảnh");
            File source = chooser.showOpenDialog(new Stage());
            chooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Images", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            
            anhSelected = source;
        } catch (Exception e) {
            System.out.println("Lỗi" + e.getMessage());
        }
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
                                    SachViewModel sach
                                            = (SachViewModel) ButtonCell.this.getTableView()
                                                    .getItems()
                                                    .get(ButtonCell.this.getIndex());
                                    if (SachCommand.xoaSach(sach.getId())) {
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

    private void initCBBLoaiSach() {
        ObservableList<LoaiSachViewModel> listLoai = LoaiSachQuery.getLoaiSach();
        cbbLoaiSach.setItems(listLoai);

        Callback<ListView<LoaiSachViewModel>, ListCell<LoaiSachViewModel>> factory
                = lv -> new ListCell<LoaiSachViewModel>() {

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
        Callback<ListView<TacGiaViewModel>, ListCell<TacGiaViewModel>> factory
                = lv -> new ListCell<TacGiaViewModel>() {

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

        Callback<ListView<NhaXuatBanViewModel>, ListCell<NhaXuatBanViewModel>> factory
                = lv -> new ListCell<NhaXuatBanViewModel>() {

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
        colMaSach.setCellValueFactory(new PropertyValueFactory<>("maSach"));
        colTenSach.setCellValueFactory(new PropertyValueFactory<>("tenSach"));
        colLoaiSach.setCellValueFactory(new PropertyValueFactory<>("tenLoaiSach"));
        colKieuSach.setCellValueFactory(new PropertyValueFactory<>("kieuSach"));
        colTacGia.setCellValueFactory(new PropertyValueFactory<>("tenTacGia"));
        colNXB.setCellValueFactory(new PropertyValueFactory<>("tenNXB"));
        colNgayNhap.setCellValueFactory(new PropertyValueFactory<>("ngayNhap"));
        colSoLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));

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

    private void initTableViewLoaiSach() {
        colLoaiSachLS.setCellValueFactory(new PropertyValueFactory<>("tenLoaiSach"));
        colKieuSachLS.setCellValueFactory(new PropertyValueFactory<>("kieuSach"));
        TableColumn<LoaiSach, Boolean> colXoaLS = new TableColumn<>("Xoá");
        colXoaLS.setEditable(true);

        colXoaLS.setCellFactory(column -> new CheckBoxTableCell<>());
        colXoaLS.setCellValueFactory(cellData -> {
            LoaiSach cellValue = cellData.getValue();
            BooleanProperty property = new SimpleBooleanProperty();
            property.setValue(cellValue.getXoa());

            // Add listener to handler change
            property.addListener((observable, oldValue, newValue) -> {
                if (!LoaiSachCommand.toggleLoaiSach(cellValue.getId(), newValue)) {
                    Dialog.errorBox("Xoá loại sách lỗi", "Lỗi", null);
                }
            });
            return property;
        });

        tbvLoaiSach.getColumns().add(colXoaLS);
        tbvLoaiSach.setEditable(true);
        getListLoaiSach();
    }

    private void initTableViewTacGia() {
        colTenTacGiaTG.setCellValueFactory(new PropertyValueFactory<>("tenTacGia"));
        TableColumn<TacGia, Boolean> colXoaTG = new TableColumn<>("Xoá");
        colXoaTG.setEditable(true);

        colXoaTG.setCellFactory(column -> new CheckBoxTableCell<>());
        colXoaTG.setCellValueFactory(cellData -> {
            TacGia cellValue = cellData.getValue();
            BooleanProperty property = new SimpleBooleanProperty();
            property.setValue(cellValue.getXoa());

            // Add listener to handler change
            property.addListener((observable, oldValue, newValue) -> {
                if (!TacGiaCommand.toggleTacGia(cellValue.getId(), newValue)) {
                    Dialog.errorBox("Xoá tác giả lỗi", "Lỗi", null);
                }
            });
            return property;
        });

        tbvTacGia.getColumns().add(colXoaTG);
        tbvTacGia.setEditable(true);
        getListTacGia();
    }

    private void initTableViewNXB() {
        colTenNXBNXB.setCellValueFactory(new PropertyValueFactory<>("tenNXB"));
        colDiaChiNXB.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        colSdtNXB.setCellValueFactory(new PropertyValueFactory<>("sDT"));

        TableColumn<NhaXuatBan, Boolean> colXoaNXB = new TableColumn<>("Xoá");
        colXoaNXB.setEditable(true);

        colXoaNXB.setCellFactory(column -> new CheckBoxTableCell<>());
        colXoaNXB.setCellValueFactory(cellData -> {
            NhaXuatBan cellValue = cellData.getValue();
            BooleanProperty property = new SimpleBooleanProperty();
            property.setValue(cellValue.getXoa());

            // Add listener to handler change
            property.addListener((observable, oldValue, newValue) -> {
                if (!TacGiaCommand.toggleTacGia(cellValue.getId(), newValue)) {
                    Dialog.errorBox("Xoá NXB lỗi", "Lỗi", null);
                }
            });
            return property;
        });

        tbvNXB.getColumns().add(colXoaNXB);
        tbvNXB.setEditable(true);
        getListNXB();
    }

    private void getListSach() {
        ObservableList<SachViewModel> listSach
                = SachQuery.getSach(searchString, chkShowBookDelete.isSelected());
        tbvSach.setItems(listSach);
    }

    private void getListLoaiSach() {
        ObservableList<LoaiSach> loaiSach
                = LoaiSachQuery.getListLoaiSach();
        tbvLoaiSach.setItems(loaiSach);
    }

    private void getListTacGia() {
        ObservableList<TacGia> tacGia
                = TacGiaQuery.getListTacGia();
        tbvTacGia.setItems(tacGia);
    }

    private void getListNXB() {
        ObservableList<NhaXuatBan> nxb
                = NhaXuatBanQuery.getListNXB();
        tbvNXB.setItems(nxb);
    }

    private void resetSach() {
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

    private void resetLoaiSach() {
        txtLoaiSach.setText("");
        txtKieuSach.setText("");
        btnSaveLoaiSach.setText("Thêm");
        loaiSachSelected = new LoaiSach();
    }

    private void resetTacGia() {
        txtTenTacGiaTG.setText("");
        btnSaveTG.setText("Thêm");
        tacGiaSelected = new TacGia();
    }

    private void resetNXB() {
        txtTenNXB.setText("");
        txtDiaChiNXB.setText("");
        txtSdtNXB.setText("");
        btnSaveNXB.setText("Thêm");
        nxbSelected = new NhaXuatBan();
    }
}
