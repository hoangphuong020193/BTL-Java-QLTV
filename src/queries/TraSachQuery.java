/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queries;

import data.ConnectionContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.PhieuMuonViewModel;
import model.SachMuonViewModel;

/**
 *
 * @author phuonghoangnguyen
 */
public class TraSachQuery {

    public static ObservableList<PhieuMuonViewModel> getPhieuMuon(String maDG,
            String tenDG, Date ngayMuon) {
        ObservableList<PhieuMuonViewModel> listPhieuMuon = FXCollections.observableArrayList();
        try {
            Connection connect = ConnectionContext.ketNoi();
            String query = "SELECT A.Id, B.MaDocGia, "
                    + "B.Ho, B.TenLot, B.Ten, "
                    + "A.NgayMuon, A.NgayTra "
                    + "FROM phieu_muon A "
                    + "JOIN doc_gia B ON A.IdDocGia = B.Id ";

            int i = 0;
            HashMap<Integer, String> params = new HashMap<Integer, String>();

            boolean filter = false;
            if (!maDG.equals("")) {
                filter = true;
                query = query + " WHERE B.MaDocGia LIKE ? ";
                params.put(++i, "%" + maDG + "%");
            }

            if (!tenDG.equals("")) {
                if (filter) {
                    query = query + " AND ";
                } else {
                    query = query + " WHERE ";
                }

                query = query + " (B.Ho LIKE ? OR B.TenLot LIKE ? OR B.Ten LIKE ?)";
                params.put(++i, "%" + tenDG + "%");
                params.put(++i, "%" + tenDG + "%");
                params.put(++i, "%" + tenDG + "%");
            }

            if (ngayMuon != null) {
                if (filter) {
                    query = query + " AND ";
                } else {
                    query = query + " WHERE ";
                }

                query = query + " A.NgayMuon = ? ";
                SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
                params.put(++i, fm.format(ngayMuon));
                i++;
            }

            query = query + " ORDER BY NgayMuon DESC ";
            PreparedStatement prep = connect.prepareStatement(query);

            for (Map.Entry param : params.entrySet()) {
                prep.setString((int) param.getKey(), (String) param.getValue());
            }

            ResultSet result = prep.executeQuery();
            while (result.next()) {
                PhieuMuonViewModel phieuMuon = new PhieuMuonViewModel();
                phieuMuon.setId(result.getInt("Id"));
                phieuMuon.setMaDocGia(result.getString("MaDocGia"));
                String name = result.getString("Ho") + " "
                        + result.getString("TenLot") + " "
                        + result.getString("Ten");
                phieuMuon.setTenDocGia(name.replace("  ", " "));
                phieuMuon.setNgayMuon(result.getDate("NgayMuon"));
                phieuMuon.setNgayTra(result.getDate("NgayTra"));
                listPhieuMuon.add(phieuMuon);
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listPhieuMuon;
    }

    public static ObservableList<SachMuonViewModel> getSachMuon(int idPhieuMuon) {
        ObservableList<SachMuonViewModel> listSachMuon = FXCollections.observableArrayList();
        try {
            Connection connect = ConnectionContext.ketNoi();
            String query = "SELECT A.IdPhieuMuon, B.Id AS IdSach, "
                    + "B.MaSach, B.TenSach, A.Xoa "
                    + "FROM sach_muon A "
                    + "JOIN sach B ON A.IdSach = B.Id WHERE A.IdPhieuMuon = ?";
            PreparedStatement prep = connect.prepareStatement(query);
            prep.setInt(1, idPhieuMuon);

            ResultSet result = prep.executeQuery();
            while (result.next()) {
                SachMuonViewModel sachMuon = new SachMuonViewModel();
                sachMuon.setIdPhieuMuon(result.getInt("IdPhieuMuon"));
                sachMuon.setIdSach(result.getInt("IdSach"));
                sachMuon.setMaSach(result.getString("MaSach"));
                sachMuon.setTenSach(result.getString("TenSach"));
                sachMuon.setXoa(result.getBoolean("Xoa"));
                listSachMuon.add(sachMuon);
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listSachMuon;
    }
}
