/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import common.Dialog;
import data.ConnectionContext;
import entity.Sach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import queries.SachQuery;

/**
 *
 * @author phuonghoangnguyen
 */
public class SachCommand {

    public static boolean xoaSach(int id) {
        try {
            Connection connect = ConnectionContext.ketNoi();
            String query = "UPDATE sach SET Xoa = 1 WHERE Id = ?";
            PreparedStatement prep = connect.prepareStatement(query);
            prep.setInt(1, id);
            prep.executeUpdate();
            connect.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }

    public static boolean saveSach(Sach sach) {
        if (!validateData(sach)) {
            return false;
        }

        if (sach.getId() == 0) {
            return themSach(sach);
        } else {
            return suaSach(sach);
        }
    }

    private static boolean themSach(Sach sach) {
        try {
            Connection connect = ConnectionContext.ketNoi();
            String query = "INSERT INTO sach "
                    + "(TenSach, MaSach, IdTacGia, IdLoaiSach, IdNXB, NgayNhap, SoLuong) "
                    + "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement prep = connect.prepareStatement(query);
            prep.setString(1, sach.getTenSach());
            prep.setString(2, sach.getMaSach());
            prep.setInt(3, sach.getIdTacGia());
            prep.setInt(4, sach.getIdLoaiSach());
            prep.setInt(5, sach.getIdNXB());
            prep.setDate(6, sach.getNgayNhap());
            prep.setInt(7, sach.getSoLuong());
            prep.executeUpdate();
            connect.close();
            Dialog.infoBox("Sửa sách thành công", "Thành công", null);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    private static boolean suaSach(Sach sach) {
        try {
            Connection connect = ConnectionContext.ketNoi();
            String query = "UPDATE sach SET "
                    + "TenSach = ?, "
                    + "MaSach = ?, "
                    + "IdTacGia = ?, "
                    + "IdLoaiSach = ?, "
                    + "IdNXB = ?, "
                    + "NgayNhap = ?, "
                    + "SoLuong = ? "
                    + "WHERE Id = ?";
            PreparedStatement prep = connect.prepareStatement(query);
            prep.setString(1, sach.getTenSach());
            prep.setString(2, sach.getMaSach());
            prep.setInt(3, sach.getIdTacGia());
            prep.setInt(4, sach.getIdLoaiSach());
            prep.setInt(5, sach.getIdNXB());
            prep.setDate(6, sach.getNgayNhap());
            prep.setInt(7, sach.getSoLuong());
            prep.setInt(8, sach.getId());
            prep.executeUpdate();
            connect.close();
            Dialog.infoBox("Thêm sách thành công", "Thành công", null);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    private static boolean validateData(Sach sach) {
        if (sach.getMaSach().equals("")
                || sach.getTenSach().equals("")
                || sach.getIdLoaiSach() == 0
                || sach.getIdTacGia() == 0
                || sach.getIdNXB() == 0
                || sach.getSoLuong() <= 0
                || sach.getNgayNhap() == null) {
            Dialog.errorBox("Tất cả các trường phải được nhập", "Lỗi", null);
            return false;
        }

        if (SachQuery.CheckMaSach(sach.getMaSach().trim(), sach.getId())) {
            Dialog.errorBox("Mã sách đã tồn tại", "Lỗi", null);
            return false;
        }
        return true;
    }
}
