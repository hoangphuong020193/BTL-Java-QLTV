/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queries;

import data.ConnectionContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.LoaiSachViewModel;

/**
 *
 * @author phuonghoangnguyen
 */
public class LoaiSachQuery {

    public static ObservableList<LoaiSachViewModel> getLoaiSach() {
        ObservableList<LoaiSachViewModel> listLoai = FXCollections.observableArrayList();
        try {
            Connection connect = ConnectionContext.ketNoi();
            String query = "SELECT Id, CONCAT('[', KieuSach, '] - ', TenLoaiSach) AS TenLoai "
                    + "FROM loai_sach WHERE Xoa = 0";

            PreparedStatement prep = connect.prepareStatement(query);
            ResultSet result = prep.executeQuery();
            while (result.next()) {
                LoaiSachViewModel loaiSach = new LoaiSachViewModel();
                loaiSach.setId(result.getInt("Id"));
                loaiSach.setTenLoaiSach(result.getString("TenLoai"));
                listLoai.add(loaiSach);
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listLoai;
    }
}
