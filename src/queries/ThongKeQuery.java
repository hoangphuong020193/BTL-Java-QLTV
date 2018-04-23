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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.DocGiaMuonSachViewModel;
import model.SachMuonNhieuViewModel;
import model.SoSachMuonTrongNgayViewModel;

/**
 *
 * @author phuonghoangnguyen
 */
public class ThongKeQuery {

    public static ObservableList<SoSachMuonTrongNgayViewModel>
            getSachMuonTrongNgay(Date fromDate, Date toDate) {

        ObservableList<SoSachMuonTrongNgayViewModel> listSachMuon = FXCollections.observableArrayList();
        try {
            Connection connect = ConnectionContext.ketNoi();
            String query = "SELECT DATE_FORMAT(B.NgayMuon, \"%d/%m/%Y\") AS NgayMuon, "
                    + "COUNT(A.IdPhieuMuon) AS SoLuongMuon "
                    + "FROM sach_muon A "
                    + "JOIN phieu_muon B ON A.IdPhieuMuon = B.Id "
                    + "WHERE ? <= B.NgayMuon "
                    + "AND B.NgayMuon <= ? "
                    + "GROUP BY B.NgayMuon";
            PreparedStatement prep = connect.prepareStatement(query);
            prep.setDate(1, fromDate);
            prep.setDate(2, toDate);
            ResultSet result = prep.executeQuery();

            while (result.next()) {
                SoSachMuonTrongNgayViewModel sachMuon = new SoSachMuonTrongNgayViewModel();
                sachMuon.setNgayMuon(result.getString("NgayMuon"));
                sachMuon.setSoLuongMuon(result.getInt("SoLuongMuon"));
                listSachMuon.add(sachMuon);
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listSachMuon;
    }

    public static ObservableList<DocGiaMuonSachViewModel>
            getDocGiaMuonSach(Date fromDate, Date toDate, int limit) {

        ObservableList<DocGiaMuonSachViewModel> docGiaMuonSach = FXCollections.observableArrayList();
        try {
            Connection connect = ConnectionContext.ketNoi();
            String query = "SELECT B.Ho, B.TenLot, B.Ten, B.MaDocGia, "
                    + "COUNT(A.IdDocGia) AS SoLanMuon "
                    + "FROM phieu_muon A "
                    + "JOIN doc_gia B ON A.IdDocGia = B.Id "
                    + "WHERE ? <= A.NgayMuon "
                    + "AND A.NgayMuon <= ? "
                    + "GROUP BY A.IdDocGia "
                    + "ORDER BY COUNT(A.IdDocGia) DESC "
                    + "LIMIT ?";
            PreparedStatement prep = connect.prepareStatement(query);
            prep.setDate(1, fromDate);
            prep.setDate(2, toDate);
            prep.setInt(3, limit);
            ResultSet result = prep.executeQuery();

            while (result.next()) {
                DocGiaMuonSachViewModel dgMuonSach = new DocGiaMuonSachViewModel();
                String hoTen = result.getString("Ho")
                        + " " + result.getString("TenLot")
                        + " " + result.getString("Ten");

                dgMuonSach.setHoTen(hoTen.replace("  ", " "));
                dgMuonSach.setMaDocGia(result.getString("MaDocGia"));
                dgMuonSach.setSoLanMuon(result.getInt("SoLanMuon"));
                docGiaMuonSach.add(dgMuonSach);
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return docGiaMuonSach;
    }

    public static ObservableList<SachMuonNhieuViewModel>
            getSachDuocMuonNhieu(Date fromDate, Date toDate, int limit) {

        ObservableList<SachMuonNhieuViewModel> sachMuonNhieu = FXCollections.observableArrayList();
        try {
            Connection connect = ConnectionContext.ketNoi();
            String query = "SELECT C.TenSach, "
                    + "COUNT(B.IdSach) AS SoLanMuon "
                    + "FROM phieu_muon A "
                    + "JOIN sach_muon B ON A.Id = B.IdPhieuMuon "
                    + "JOIN sach C ON B.IdSach = C.Id "
                    + "WHERE ? <= A.NgayMuon "
                    + "AND A.NgayMuon <= ? "
                    + "GROUP BY B.IdSach "
                    + "ORDER BY COUNT(B.IdSach) DESC "
                    + "LIMIT ?";
            PreparedStatement prep = connect.prepareStatement(query);
            prep.setDate(1, fromDate);
            prep.setDate(2, toDate);
            prep.setInt(3, limit);
            ResultSet result = prep.executeQuery();

            while (result.next()) {
                SachMuonNhieuViewModel sach = new SachMuonNhieuViewModel();
                sach.setTenSach(result.getString("TenSach"));
                sach.setSoLanDuocMuon(result.getInt("SoLanMuon"));
                sachMuonNhieu.add(sach);
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return sachMuonNhieu;
    }
}
