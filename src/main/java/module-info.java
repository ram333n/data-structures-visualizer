module com.prokopchuk.lab_2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires org.junit.jupiter.api;

    opens com.prokopchuk.lab_2 to javafx.fxml;
    exports com.prokopchuk.lab_2;
}