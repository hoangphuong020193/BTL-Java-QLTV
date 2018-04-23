/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien;

import common.OpenForm;
import common.StringHandle;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import model.DocGiaMuonSachViewModel;
import model.NhanVienDangNhapModel;
import model.SachMuonNhieuViewModel;
import model.SoSachMuonTrongNgayViewModel;
import queries.ThongKeQuery;

/**
 * FXML Controller class
 *
 * @author phuonghoangnguyen
 */
public class ThongKeController implements Initializable {

    @FXML
    private ComboBox<String> cbbLoai;
    @FXML
    private DatePicker dpkFrom;
    @FXML
    private DatePicker dpkTo;
    @FXML
    private TextField txtSoLuong;
    @FXML
    private Button btnXem;
    @FXML
    private StackPane stackPane;

    private NhanVienDangNhapModel nhanVien;
    private final String SACH_MUON_HANG_NGAY = "Sách mượn hằng ngày";
    private final String DOC_GIA_MUON_NHIEU = "Độc giả mượn nhiều";
    private final String SACH_DUOC_MUON_NHIEU = "Sách được mượn nhiều";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initComboboxLoai();
        LocalDate today = LocalDate.now();
        dpkFrom.setValue(today.withDayOfMonth(1));
        dpkTo.setValue(today.plusMonths(1).withDayOfMonth(1).minusDays(1));

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
    private void onClickXem(ActionEvent event) {
        String loai = cbbLoai.getValue();
        switch (loai) {
            case SACH_MUON_HANG_NGAY:
                generateChartSachMuonHangNgay();
                break;
            case DOC_GIA_MUON_NHIEU:
                generateChartDocGiaMuonNhieu();
                break;
            case SACH_DUOC_MUON_NHIEU:
            default:
                generateChartSachDuocMuonNhieu();
                break;
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

    @FXML
    private void onClickLoai(ActionEvent event) {
        if (cbbLoai.getValue() == SACH_MUON_HANG_NGAY) {
            txtSoLuong.setDisable(true);
        } else {
            txtSoLuong.setDisable(false);
        }
    }

    private void initComboboxLoai() {
        ObservableList<String> listLoai = FXCollections.observableArrayList();;
        listLoai.add(SACH_MUON_HANG_NGAY);
        listLoai.add(DOC_GIA_MUON_NHIEU);
        listLoai.add(SACH_DUOC_MUON_NHIEU);
        cbbLoai.setItems(listLoai);

        Callback<ListView<String>, ListCell<String>> factory
                = lv -> new ListCell<String>() {

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item);
            }
        };

        cbbLoai.setCellFactory(factory);
        cbbLoai.setButtonCell(factory.call(null));
        cbbLoai.getSelectionModel().select(0);
    }

    private void generateChartSachMuonHangNgay() {
        Date fromDate = Date.valueOf(dpkFrom.getValue());
        Date toDate = Date.valueOf(dpkTo.getValue());

        ObservableList<SoSachMuonTrongNgayViewModel> listSachMuon
                = ThongKeQuery.getSachMuonTrongNgay(fromDate, toDate);

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Ngày");

        final LineChart<String, Number> lineChart
                = new LineChart<String, Number>(xAxis, yAxis);

        lineChart.setTitle("Số lượng sách mượn hằng ngày");

        XYChart.Series series = new XYChart.Series();
        series.setName("Sách");

        LocalDate startDate = dpkFrom.getValue();
        LocalDate endDate = dpkTo.getValue();
        long days = ChronoUnit.DAYS.between(startDate, endDate);

        for (long i = 0; i < days; i++) {
            LocalDate date = startDate.plusDays(i);
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM");

            Optional<SoSachMuonTrongNgayViewModel> data
                    = listSachMuon.stream()
                            .filter(x
                                    -> x.getNgayMuon().equals(date.format(formatter1)))
                            .findFirst();
            Number soLuong = 0;
            if (data.isPresent()) {
                soLuong = data.get().getSoLuongMuon();
            }

            series.getData().add(new XYChart.Data(date.format(formatter2), soLuong));
        }
        lineChart.getData().add(series);

        stackPane.getChildren().add(lineChart);
    }

    private void generateChartDocGiaMuonNhieu() {
        Date fromDate = Date.valueOf(dpkFrom.getValue());
        Date toDate = Date.valueOf(dpkTo.getValue());
        int limit = txtSoLuong.getText().equals("") ? 0 : Integer.valueOf(txtSoLuong.getText());

        ObservableList<DocGiaMuonSachViewModel> listDocGiaMuonSach
                = ThongKeQuery.getDocGiaMuonSach(fromDate, toDate, limit);

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart
                = new BarChart<String, Number>(xAxis, yAxis);
        barChart.setTitle("Độc giả mượn nhiều");
        xAxis.setLabel("Độc giả");
        yAxis.setLabel("Số lượt mượn");

        XYChart.Series series = new XYChart.Series();

        for (DocGiaMuonSachViewModel item : listDocGiaMuonSach) {
            series.getData().add(new XYChart.Data(item.getHoTen(), item.getSoLanMuon()));
        }

        barChart.getData().addAll(series);
        stackPane.getChildren().clear();
        stackPane.getChildren().add(barChart);
    }

    private void generateChartSachDuocMuonNhieu() {
        Date fromDate = Date.valueOf(dpkFrom.getValue());
        Date toDate = Date.valueOf(dpkTo.getValue());
        int limit = txtSoLuong.getText().equals("") ? 0 : Integer.valueOf(txtSoLuong.getText());

        ObservableList<SachMuonNhieuViewModel> listSachMuon
                = ThongKeQuery.getSachDuocMuonNhieu(fromDate, toDate, limit);

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart
                = new BarChart<String, Number>(xAxis, yAxis);
        barChart.setTitle("Sách được mượn nhiều");
        xAxis.setLabel("Tên sách");
        yAxis.setLabel("Số lượt mượn");

        XYChart.Series series = new XYChart.Series();

        for (SachMuonNhieuViewModel item : listSachMuon) {
            series.getData().add(new XYChart.Data(item.getTenSach(), item.getSoLanDuocMuon()));
        }

        barChart.getData().addAll(series);
        stackPane.getChildren().clear();
        stackPane.getChildren().add(barChart);
    }
}
