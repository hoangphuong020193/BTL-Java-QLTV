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
import model.SachViewModel;

/**
 *
 * @author phuonghoangnguyen
 */
public class SachQuery {

    public static ObservableList<SachViewModel> getSach(String searchString, boolean showXoa) {
        ObservableList<SachViewModel> listSach = FXCollections.observableArrayList();
        try {
            Connection connect = ConnectionContext.ketNoi();
            String query = "SELECT s.Id, s.MaSach, s.TenSach, s.IdLoaiSach, "
                    + "l.TenLoaiSach, l.KieuSach, s.IdTacGia, tg.TenTacGia, "
                    + "s.IdNXB, nxb.TenNXB, s.NgayNhap, s.SoLuong, s.Xoa "
                    + "FROM sach s JOIN loai_sach l ON s.IdLoaiSach = l.Id "
                    + "JOIN tac_gia tg ON s.IdTacGia = tg.Id "
                    + "JOIN nha_xuat_ban nxb ON s.IdNXB = nxb.Id";

            if (!showXoa) {
                query = query + " WHERE s.Xoa = 0 ";
            }

            if (!searchString.equals("")) {

                if (showXoa) {
                    query = query + " WHERE ";
                } else {
                    query = query + " AND ";
                }

                query = query + " (MaSach LIKE ? "
                        + "OR TenSach LIKE ? "
                        + "OR TenTacGia LIKE ? "
                        + "OR TenNXB LIKE ? "
                        + "OR TenLoaiSach LIKE ?) ";
            }
            PreparedStatement prep = connect.prepareStatement(query);

            if (!searchString.equals("")) {
                prep.setString(1, "%" + searchString + "%");
                prep.setString(2, "%" + searchString + "%");
                prep.setString(3, "%" + searchString + "%");
                prep.setString(4, "%" + searchString + "%");
                prep.setString(5, "%" + searchString + "%");
            }

            ResultSet result = prep.executeQuery();
            while (result.next()) {
                SachViewModel sach = new SachViewModel();
                sach.setId(result.getInt("Id"));
                sach.setMaSach(result.getString("MaSach"));
                sach.setTenSach(result.getString("TenSach"));
                sach.setIdTacGia(result.getInt("IdTacGia"));
                sach.setTenTacGia(result.getString("TenTacGia"));
                sach.setIdLoaiSach(result.getInt("IdLoaiSach"));
                sach.setTenLoaiSach(result.getString("TenLoaiSach"));
                sach.setKieuSach(result.getString("KieuSach"));
                sach.setIdNXB(result.getInt("IdNXB"));
                sach.setTenNXB(result.getString("TenNXB"));
                sach.setNgayNhap(result.getDate("NgayNhap"));
                sach.setSoLuong(result.getInt("SoLuong"));
                sach.setXoa(result.getBoolean("Xoa"));
                listSach.add(sach);
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listSach;
    }

    public static boolean CheckMaSach(String maSach, int id) {
        try {
            Connection connect = ConnectionContext.ketNoi();
            String query = "SELECT * FROM sach WHERE Id <> ? && MaSach = ?";
            PreparedStatement prep = connect.prepareStatement(query);
            prep.setInt(1, id);
            prep.setString(2, maSach);
            ResultSet result = prep.executeQuery();
            while (result.next()) {
                return true;
            }
            connect.close();
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static ObservableList<SachViewModel> getSachKhaDung(String searchString) {
        ObservableList<SachViewModel> listSach = FXCollections.observableArrayList();
        try {
            Connection connect = ConnectionContext.ketNoi();
            String query = "SELECT A.Id, A.MaSach, A.TenSach, "
                    + "C.TenLoaiSach, C.KieuSach, "
                    + "(A.SoLuong - IFNULL(B.DangMuon, 0)) AS SoLuong "
                    + "FROM sach A "
                    + "LEFT JOIN "
                    + "(SELECT B.IdSach, COUNT(B.IdSach) AS DangMuon "
                    + "FROM phieu_muon A "
                    + "JOIN sach_muon B ON A.Id = B.IdPhieuMuon "
                    + "WHERE NgayTra IS NULL GROUP BY B.IdSach) "
                    + "B ON A.Id = B.IdSach "
                    + "LEFT JOIN "
                    + "loai_sach C ON A.IdLoaiSach = C.Id "
                    + "WHERE A.Xoa = 0";

            if (!searchString.trim().equals("")) {
                query = query + " AND (A.MaSach LIKE ? "
                        + "OR A.TenSach LIKE ? )";
            }

            PreparedStatement prep = connect.prepareStatement(query);

            if (!searchString.trim().equals("")) {
                prep.setString(1, "%" + searchString + "%");
                prep.setString(2, "%" + searchString + "%");
            }

            ResultSet result = prep.executeQuery();
            while (result.next()) {
                SachViewModel sach = new SachViewModel();
                sach.setId(result.getInt("Id"));
                sach.setMaSach(result.getString("MaSach"));
                sach.setTenSach(result.getString("TenSach"));
                sach.setTenLoaiSach(result.getString("TenLoaiSach"));
                sach.setKieuSach(result.getString("KieuSach"));
                sach.setSoLuong(result.getInt("SoLuong"));
                listSach.add(sach);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return listSach;
    }
}
