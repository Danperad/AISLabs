package com.vyatsu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {

    private static final String url = "jdbc:postgresql://localhost:5432/work100012";
    private static final String user = "postgres";
    private static final String pass = "000000";

    static Connection connection;

    @Override
    public void init() throws Exception {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, user, pass);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        super.init();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Photostudio.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Products");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        if (connection == null) return;
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}