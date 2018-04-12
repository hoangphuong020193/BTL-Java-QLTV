/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import common.Dialog;
import data.ConnectionContext;
import entity.TacGia;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author phuonghoangnguyen
 */
public class TacGiaCommand {

    public static boolean saveTacGia(TacGia tacGia) {
        if (!validateData(tacGia)) {
            return false;
        }

        if (tacGia.getId() == 0) {
            return themTacGia(tacGia);
        } else {
            return suaTacGia(tacGia);
        }
    }

    private static boolean themTacGia(TacGia tacGia) {
        try {
            Connection connect = ConnectionContext.ketNoi();
            String query = "INSERT INTO tac_gia "
                    + "(TenTacGia) "
                    + "VALUES (?)";
            PreparedStatement prep = connect.prepareStatement(query);
            prep.setString(1, tacGia.getTenTacGia());
            prep.executeUpdate();
            connect.close();
            Dialog.infoBox("Thêm tác giả thành công", "Thành công", null);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    private static boolean suaTacGia(TacGia tacGia) {
        try {
            Connection connect = ConnectionContext.ketNoi();
            String query = "UPDATE tac_gia SET "
                    + "TenTacGia = ? "
                    + "WHERE Id = ?";
            PreparedStatement prep = connect.prepareStatement(query);
            prep.setString(1, tacGia.getTenTacGia());
            prep.setInt(2, tacGia.getId());
            prep.executeUpdate();
            connect.close();
            Dialog.infoBox("Sửa tác giả thành công", "Thành công", null);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    private static boolean validateData(TacGia tacGia) {
        if (tacGia.getTenTacGia().equals("")) {
            Dialog.errorBox("Các trường phải được nhập", "Lỗi", null);
            return false;
        }
        return true;
    }

    public static boolean toggleTacGia(int id, boolean xoa) {
        try {
            Connection connect = ConnectionContext.ketNoi();
            String query = "UPDATE tac_gia SET Xoa = ? WHERE Id = ?";
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
