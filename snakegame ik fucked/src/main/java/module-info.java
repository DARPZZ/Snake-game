module com.example.snakegamefucked {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.snakegamefucked to javafx.fxml;
    opens com.example.snakegamefucked.Database to javafx.base;

    exports com.example.snakegamefucked;
}