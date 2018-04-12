/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import common.Dialog;
import data.ConnectionContext;
import entity.NhaXuatBan;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author phuonghoangnguyen
 */
public class NhaXuatBanCommand {

    public static boolean saveNXB(NhaXuatBan nxb) {
        if (!validateData(nxb)) {
            return false;
        }

        if (nxb.getId() == 0) {
            return themNXB(nxb);
        } else {
            return suaNXB(nxb);
        }
    }

    private static boolean themNXB(NhaXuatBan nxb) {
        try {
            Connection connect = ConnectionContext.ketNoi();
            String query = "INSERT INTO nha_xuat_ban "
                    + "(TenNXB, DiaChi, SDT) "
                    + "VALUES (?, ?, ?)";
            PreparedStatement prep = connect.prepareStatement(query);
            prep.setString(1, nxb.getTenNXB());
            prep.setString(2, nxb.getDiaChi());
            prep.setString(3, nxb.getSDT());
            prep.executeUpdate();
            connect.close();
            Dialog.infoBox("Thêm NXB thành công", "Thành công", null);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    private static boolean suaNXB(NhaXuatBan nxb) {
        try {
            Connection connect = ConnectionContext.ketNoi();
            String query = "UPDATE nha_xuat_ban SET "
                    + "TenNXB = ?, "
                    + "DiaChi = ?, "
                    + "SDT = ? "
                    + "WHERE Id = ?";
            PreparedStatement prep = connect.prepareStatement(query);
            prep.setString(1, nxb.getTenNXB());
            prep.setString(2, nxb.getDiaChi());
            prep.setString(3, nxb.getSDT());
            prep.setInt(4, nxb.getId());
            prep.executeUpdate();
            connect.close();
            Dialog.infoBox("Sửa NXB thành công", "Thành công", null);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    private static boolean validateData(NhaXuatBan nxb) {
        if (nxb.getTenNXB().equals("")) {
            Dialog.errorBox("Các trường phải được nhập", "Lỗi", null);
            return false;
        }
        return true;
    }

    public static boolean toggleNXB(int id, boolean xoa) {
        try {
            Connection connect = ConnectionContext.ketNoi();
            String query = "UPDATE nha_xuat_ban SET Xoa = ? WHERE Id = ?";
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
