/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import data.ConnectionContext;
import entity.Sach;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author phuonghoangnguyen
 */
public class SachCommand {

    public static boolean XoaSach(int id) {
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

    public static boolean ThemSach(Sach sach) {
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
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean SuaSach(Sach sach) {
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
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
