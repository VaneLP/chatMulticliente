module com.example.chatmulticliente {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.example.chatmulticliente.cliente;
    opens com.example.chatmulticliente.cliente to javafx.fxml;
    exports com.example.chatmulticliente.servidor;
    opens com.example.chatmulticliente.servidor to javafx.fxml;
}