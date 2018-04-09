/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author phuonghoangnguyen
 */
public class OpenForm {

    public FXMLLoader open(String resource, String title) throws IOException {
        Stage stage = new Stage();
        URL u = getClass().getClassLoader().getResource("quanlythuvien/" + resource);
        FXMLLoader loader = new FXMLLoader(u);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(title + " - Quản lý thư viện");
        stage.setResizable(false);
        stage.show();
        return loader;
    }
}
