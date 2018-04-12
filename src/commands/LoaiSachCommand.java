/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import common.Dialog;
import data.ConnectionContext;
import entity.LoaiSach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author phuonghoangnguyen
 */
public class LoaiSachCommand {

    public static boolean saveLoaiSach(LoaiSach loaiSach) {
        if (!validateData(loaiSach)) {
            return false;
        }

        if (loaiSach.getId() == 0) {
            return themLoaiSach(loaiSach);
        } else {
            return suaLoaiSach(loaiSach);
        }
    }

    private static boolean themLoaiSach(LoaiSach loaiSach) {
        try {
            Connection connect = ConnectionContext.ketNoi();
            String query = "INSERT INTO loai_sach "
                    + "(TenLoaiSach, KieuSach) "
                    + "VALUES (?,?)";
            PreparedStatement prep = connect.prepareStatement(query);
            prep.setString(1, loaiSach.getTenLoaiSach());
            prep.setString(2, loaiSach.getKieuSach());
            prep.executeUpdate();
            connect.close();
            Dialog.infoBox("Thêm loại sách thành công", "Thành công", null);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    private static boolean suaLoaiSach(LoaiSach loaiSach) {
        try {
            Connection connect = ConnectionContext.ketNoi();
            String query = "UPDATE loai_sach SET "
                    + "TenLoaiSach = ?, "
                    + "KieuSach = ? "
                    + "WHERE Id = ?";
            PreparedStatement prep = connect.prepareStatement(query);
            prep.setString(1, loaiSach.getTenLoaiSach());
            prep.setString(2, loaiSach.getKieuSach());
            prep.setInt(3, loaiSach.getId());
            prep.executeUpdate();
            connect.close();
            Dialog.infoBox("Sửa loại sách thành công", "Thành công", null);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    private static boolean validateData(LoaiSach loaiSach) {
        if (loaiSach.getKieuSach().equals("") || loaiSach.getKieuSach().equals("")) {
            Dialog.errorBox("Các trường phải được nhập", "Lỗi", null);
            return false;
        }
        return true;
    }

    public static boolean toggleLoaiSach(int id, boolean xoa) {
        try {
            Connection connect = ConnectionContext.ketNoi();
            String query = "UPDATE loai_sach SET Xoa = ? WHERE Id = ?";
            PreparedStatement prep = connect.prepareStatement(query);
            prep.setBoolean(1, xoa);
            prep.setInt(2, id);
            prep.executeUpdate();
            connect.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }
}
