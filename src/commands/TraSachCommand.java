/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import data.ConnectionContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;

/**
 *
 * @author phuonghoangnguyen
 */
public class TraSachCommand {
    
    public static boolean traSach(int phieuMuonId) {
        try {
            Connection connect = ConnectionContext.ketNoi();
            String query = "UPDATE phieu_muon SET NgayTra = ? WHERE Id = ?";
            PreparedStatement prep = connect.prepareStatement(query);
            prep.setDate(1, Date.valueOf(LocalDate.now()));
            prep.setInt(2, phieuMuonId);
            prep.executeUpdate();
            connect.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }
}
