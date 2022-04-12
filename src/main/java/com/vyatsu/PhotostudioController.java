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

public class PhotostudioController {

    private final ObservableList<Service> servicesData = FXCollections.observableArrayList();

    public Button loadButton;

    @FXML
    private TableView<Service> tableServices;

    @FXML
    private TableColumn<Service, String > serviceCol;

    @FXML
    private TableColumn<Service, String> clientCol;

    @FXML
    private TableColumn<Service, String > dateCol;

    @FXML
    private TableColumn<Service, String> timeCol;

    @FXML
    private TableColumn<Service, String> statusCol;


    @FXML
    private void handleButtonAction(ActionEvent event) {
        initData();
        serviceCol.setCellValueFactory(new PropertyValueFactory<>("service"));
        clientCol.setCellValueFactory(new PropertyValueFactory<>("client"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        // заполняем таблицу данными
        tableServices.setItems(servicesData);
    }

    private void initData() {
        try {
            Statement st = Main.connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM services");
            while (rs.next()) {
                Service service = new Service();
                service.setId(rs.getInt("id"));
                service.setService(rs.getString("service"));
                service.setClient(rs.getString("client"));
                service.setDate(rs.getString("s_date"));
                service.setTime(rs.getString("s_time"));
                service.setStatus(rs.getString("status"));

                servicesData.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}