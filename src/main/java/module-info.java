module org.example.dentelmanag {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;  // Pour JDBC et SQL
    requires mysql.connector.j;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires static lombok; // Assurez-vous que le module JNDI est requis


    // Ouvrir les packages de contrôleurs aux modules JavaFX pour l'injection FXML
    opens org.example.dentelmanag.Model to org.hibernate.orm.core;
    opens org.example.dentelmanag to javafx.fxml;
    opens org.example.dentelmanag.Controller to javafx.fxml;
    opens org.example.dentelmanag.Controller.Infermier to javafx.fxml;
    opens org.example.dentelmanag.Controller.Medecin to javafx.fxml;

    // Exporter les packages
    exports org.example.dentelmanag;
    exports org.example.dentelmanag.Controller;
    exports org.example.dentelmanag.Controller.Infermier;
    exports org.example.dentelmanag.Controller.Medecin;
    exports org.example.dentelmanag.Model;

    exports org.example.dentelmanag.Util;
    opens org.example.dentelmanag.Util to javafx.fxml;
}
