/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queries;

import data.ConnectionContext;
import entity.NhaXuatBan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.NhaXuatBanViewModel;

/**
 *
 * @author phuonghoangnguyen
 */
public class NhaXuatBanQuery {

    public static ObservableList<NhaXuatBanViewModel> getNXB() {
        ObservableList<NhaXuatBanViewModel> listNXB = FXCollections.observableArrayList();
        try {
            Connection connect = ConnectionContext.ketNoi();
            String query = "SELECT Id, TenNXB "
                    + "FROM nha_xuat_ban WHERE Xoa = 0";

            PreparedStatement prep = connect.prepareStatement(query);
            ResultSet result = prep.executeQuery();
            while (result.next()) {
                NhaXuatBanViewModel nxb = new NhaXuatBanViewModel();
                nxb.setId(result.getInt("Id"));
                nxb.setTenNXB(result.getString("TenNXB"));
                listNXB.add(nxb);
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listNXB;
    }

    public static ObservableList<NhaXuatBan> getListNXB() {
        ObservableList<NhaXuatBan> listNXB = FXCollections.observableArrayList();
        try {
            Connection connect = ConnectionContext.ketNoi();
            String query = "SELECT * FROM nha_xuat_ban";

            PreparedStatement prep = connect.prepareStatement(query);
            ResultSet result = prep.executeQuery();
            while (result.next()) {
                NhaXuatBan nxb = new NhaXuatBan();
                nxb.setId(result.getInt("Id"));
                nxb.setTenNXB(result.getString("TenNXB"));
                nxb.setDiaChi(result.getString("DiaChi"));
                nxb.setSDT(result.getString("SDT"));
                nxb.setXoa(result.getBoolean("Xoa"));
                listNXB.add(nxb);
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listNXB;
    }
}
