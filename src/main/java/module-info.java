module com.team.serveur_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;



    opens com.team.serveur_app.model.serveur to javafx.base;
    opens com.team.serveur_app.model.plat to javafx.base;
    opens com.team.serveur_app.model.categorie to javafx.base;
    opens com.team.serveur_app.model.table to javafx.base;

    exports com.team.serveur_app;
    exports com.team.serveur_app.utils;

    exports com.team.serveur_app.model.plat;

    opens com.team.serveur_app.utils to javafx.fxml;

    exports com.team.serveur_app.controller.serveur;
    opens com.team.serveur_app.controller.serveur to javafx.fxml;
    exports com.team.serveur_app.controller.plat;
    opens com.team.serveur_app.controller.plat to javafx.fxml;
    exports com.team.serveur_app.controller.commande;
    opens com.team.serveur_app.controller.commande to javafx.fxml;
    exports com.team.serveur_app.controller.categorie;
    opens com.team.serveur_app.controller.categorie to javafx.fxml;
    exports com.team.serveur_app.controller.mainActivity to javafx.fxml;
    opens com.team.serveur_app.controller.mainActivity;
}