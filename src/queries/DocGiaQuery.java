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
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.DocGiaViewModel;

/**
 *
 * @author phuonghoangnguyen
 */
public class DocGiaQuery {

    public static ObservableList<DocGiaViewModel> getDocGiaKhaDung(String maDG, String hoTenDG) {
        ObservableList<DocGiaViewModel> listDocGia = FXCollections.observableArrayList();
        try {
            Connection connect = ConnectionContext.ketNoi();
            String query = "SELECT * "
                    + "FROM doc_gia WHERE Xoa = 0";

            HashMap<Integer, String> params = new HashMap<Integer, String>();
            int index = 0;
            if (!maDG.equals("")) {
                query = query + " AND MaDocGia LIKE ? ";
                params.put(++index, "%" + maDG + "%");
            }

            if (!hoTenDG.equals("")) {
                query = query + " AND CONCAT(Ho,TenLot,Ten) LIKE ? ";
                params.put(++index, "%" + hoTenDG + "%");
            }

            PreparedStatement prep = connect.prepareStatement(query);

            for (Map.Entry param : params.entrySet()) {
                prep.setString((int) param.getKey(), (String) param.getValue());
            }

            ResultSet result = prep.executeQuery();
            while (result.next()) {
                DocGiaViewModel docGia = new DocGiaViewModel();
                docGia.setId(result.getInt("Id"));
                docGia.setMaDocGia(result.getString("MaDocGia"));
                docGia.setHo(result.getString("Ho"));
                docGia.setTenLot(result.getString("TenLot"));
                docGia.setTen(result.getString("Ten"));
                listDocGia.add(docGia);
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listDocGia;
    }
}
