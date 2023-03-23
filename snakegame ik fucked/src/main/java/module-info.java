module com.example.snakegamefucked {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snakegamefucked to javafx.fxml;
    exports com.example.snakegamefucked;
}