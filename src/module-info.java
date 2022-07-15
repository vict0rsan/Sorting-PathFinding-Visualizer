module core {
    requires javafx.controls;
    requires javafx.fxml;


    exports core;
    opens core to javafx.fxml;
    exports core.Controllers;
    opens core.Controllers to javafx.fxml;
}