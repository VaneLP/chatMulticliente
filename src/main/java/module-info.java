module com.example.chatmulticliente {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.chatmulticliente to javafx.fxml;
    exports com.example.chatmulticliente;
    exports com.example.chatmulticliente.entrada;
    opens com.example.chatmulticliente.entrada to javafx.fxml;
    exports com.example.chatmulticliente.cliente;
    opens com.example.chatmulticliente.cliente to javafx.fxml;
    exports com.example.chatmulticliente.servidor;
    opens com.example.chatmulticliente.servidor to javafx.fxml;
}