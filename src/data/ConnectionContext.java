/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author phuonghoangnguyen
 */
public class ConnectionContext {

    public static Connection ketNoi() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/quanlythuvien", "root", "123456");

        } catch (ClassNotFoundException e) {
            System.out.println("Load không được driver");
        } catch (SQLException ex) {
            System.out.println("Lỗi" + ex.getMessage());
        }
        return connection;
    }
}
