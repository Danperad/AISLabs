package com.vyatsu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import javafx.scene.control.TableColumn;

public class FXMLDocumentController {

    private final ObservableList<Product> productsData = FXCollections.observableArrayList();

    public Button loadButton;

    @FXML
    private TableView<Product> tableProducts;

    @FXML
    private TableColumn<Product, Integer> idCol;

    @FXML
    private TableColumn<Product, String> titleCol;

    @FXML
    private TableColumn<Product, Double> priceCol;

    @FXML
    private TableColumn<Product, String> nameCol;


    @FXML
    private void handleButtonAction(ActionEvent event) {
        initData();
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        // заполняем таблицу данными
        tableProducts.setItems(productsData);
    }

    private void initData() {
        try {
            Connection con = DriverManager.getConnection(Main.url, Main.user, Main.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM products");
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setTitle(rs.getString("title"));
                product.setPrice(rs.getDouble("price"));
                product.setName(rs.getString("categoryname"));

                productsData.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}