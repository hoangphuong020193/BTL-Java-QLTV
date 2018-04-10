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
                sach.setTenLoaiSach(result.getString("TenLoai"));
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
}
