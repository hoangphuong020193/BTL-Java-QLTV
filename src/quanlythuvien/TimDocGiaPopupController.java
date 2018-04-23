/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.DocGiaViewModel;
import queries.DocGiaQuery;

/**
 * FXML Controller class
 *
 * @author phuonghoangnguyen
 */
public class TimDocGiaPopupController implements Initializable {

    @FXML
    private TextField txtMaDG;
    @FXML
    private TextField txtHoTenDG;
    @FXML
    private Button btnTim;
    @FXML
    private Button btnHuy;
    @FXML
    private TableView<DocGiaViewModel> tbvDocGia;
    @FXML
    private TableColumn<DocGiaViewModel, String> colMaDG;
    @FXML
    private TableColumn<DocGiaViewModel, String> colHotenDG;
    @FXML
    private Button btnChon;

    public DocGiaViewModel docGiaSelected;

    private final ReadOnlyObjectWrapper<DocGiaViewModel> currentDocGia = new ReadOnlyObjectWrapper<>();

    public ReadOnlyObjectProperty<DocGiaViewModel> currentDocGiaProperty() {
        return currentDocGia.getReadOnlyProperty();
    }

    public DocGiaViewModel getCurrentDocGia() {
        return currentDocGia.get();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTableView();
    }

    @FXML
    private void onClickTim(ActionEvent event) {
        getListDocGia();
    }

    @FXML
    private void onClickHuy(ActionEvent event) {
        txtMaDG.setText("");
        txtHoTenDG.setText("");
        btnChon.setDisable(true);
        getListDocGia();
    }

    @FXML
    private void onSelectDocGia(MouseEvent event) {
        docGiaSelected = tbvDocGia.getSelectionModel().getSelectedItem();
        btnChon.setDisable(false);
        txtMaDG.setText(docGiaSelected.getMaDocGia());
        txtHoTenDG.setText(docGiaSelected.getHoTenDG());
    }

    @FXML
    private void onClickChon(ActionEvent event) {
        currentDocGia.set(docGiaSelected);
        Stage stage = (Stage) btnChon.getScene().getWindow();
        stage.close();
    }

    private void initTableView() {
        colMaDG.setCellValueFactory(new PropertyValueFactory<>("maDocGia"));
        colHotenDG.setCellValueFactory(new PropertyValueFactory<>("hoTenDG"));
        getListDocGia();
    }

    private void getListDocGia() {

        String maDG = txtMaDG.getText().trim();
        String hoTen = txtHoTenDG.getText().trim();

        ObservableList<DocGiaViewModel> listDG
                = DocGiaQuery.getDocGiaKhaDung(maDG, hoTen);
        tbvDocGia.setItems(listDG);
    }
}
