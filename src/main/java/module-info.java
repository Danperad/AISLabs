module com.vyatsu {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.vyatsu to javafx.fxml;
    exports com.vyatsu;
}