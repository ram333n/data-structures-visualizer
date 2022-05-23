module com.prokopchuk.lab_2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.prokopchuk.lab_2 to javafx.fxml;
    exports com.prokopchuk.lab_2;
}