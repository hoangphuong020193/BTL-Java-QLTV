/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import data.ConnectionContext;
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
}
