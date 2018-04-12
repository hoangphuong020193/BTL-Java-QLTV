/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queries;

import data.ConnectionContext;
import entity.TacGia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.TacGiaViewModel;

/**
 *
 * @author phuonghoangnguyen
 */
public class TacGiaQuery {
    
    public static ObservableList<TacGiaViewModel> getTacGia() {
        ObservableList<TacGiaViewModel> listTacGia = FXCollections.observableArrayList();
        try {
            Connection connect = ConnectionContext.ketNoi();
            String query = "SELECT Id, TenTacGia "
                    + "FROM tac_gia WHERE Xoa = 0";
            
            PreparedStatement prep = connect.prepareStatement(query);
            ResultSet result = prep.executeQuery();
            while (result.next()) {
                TacGiaViewModel tacGia = new TacGiaViewModel();
                tacGia.setId(result.getInt("Id"));
                tacGia.setTacGia(result.getString("TenTacGia"));
                listTacGia.add(tacGia);
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listTacGia;
    }
    
    public static ObservableList<TacGia> getListTacGia() {
        ObservableList<TacGia> listTacGia = FXCollections.observableArrayList();
        try {
            Connection connect = ConnectionContext.ketNoi();
            String query = "SELECT * FROM tac_gia";
            
            PreparedStatement prep = connect.prepareStatement(query);
            ResultSet result = prep.executeQuery();
            while (result.next()) {
                TacGia tacGia = new TacGia();
                tacGia.setId(result.getInt("Id"));
                tacGia.setTenTacGia(result.getString("TenTacGia"));
                tacGia.setXoa(result.getBoolean("Xoa"));
                listTacGia.add(tacGia);
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listTacGia;
    }
}
