/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import data.ConnectionContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.collections.ObservableList;
import model.DocGiaViewModel;
import model.SachMuonViewModel;

/**
 *
 * @author phuonghoangnguyen
 */
public class MuonSachCommand {

    public static boolean taoPhieuMuon(DocGiaViewModel docGia,
            ObservableList<SachMuonViewModel> listSachMuon) throws SQLException {
        int key = 0;
        try {
            Connection connect = ConnectionContext.ketNoi();
            String queryInsertPhieuMuon = "INSERT INTO phieu_muon "
                    + "(IdDocGia, NgayMuon) "
                    + "VALUES (?, ?)";
            PreparedStatement prep
                    = connect.prepareStatement(queryInsertPhieuMuon,
                            Statement.RETURN_GENERATED_KEYS);
            prep.setInt(1, docGia.getId());
            prep.setDate(2, Date.valueOf(LocalDate.now()));
            prep.executeUpdate();
            ResultSet rs = prep.getGeneratedKeys();

            if (rs.next()) {
                key = rs.getInt(1);
            }

            for (SachMuonViewModel item : listSachMuon) {
                String queryInsertSachMuon = "INSERT INTO sach_muon "
                        + "(IdPhieuMuon, IdSach) "
                        + "VALUES (?,?)";
                prep = connect.prepareStatement(queryInsertSachMuon);
                prep.setInt(1, key);
                prep.setInt(2, item.getIdSach());
                prep.executeUpdate();
            }

            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            handlError(key);
            return false;
        }
    }

    private static void handlError(int idPhieuMuon) throws SQLException {
        if (idPhieuMuon > 0) {
            Connection connect = ConnectionContext.ketNoi();
            String deleteSachMuonQuery = "DELETE FROM sach_muon "
                    + "WHERE IdPhieuMuon = ? ";
            PreparedStatement prep
                    = connect.prepareStatement(deleteSachMuonQuery);
            prep.executeUpdate();

            String deletePhieuMuonQuery = "DELETE FROM phieu_muon "
                    + "WHERE Id = ? ";
            prep = connect.prepareStatement(deletePhieuMuonQuery);
            prep.executeUpdate();
        }
    }
}
