package com.team.serveur_app.controller.login;

import com.team.serveur_app.App;
import com.team.serveur_app.controller.serveur.ServerItemController;
import com.team.serveur_app.controller.table.TableItemController;
import com.team.serveur_app.model.serveur.Serveur;
import com.team.serveur_app.model.serveur.ServeurDAO;
import com.team.serveur_app.model.table.Table;
import com.team.serveur_app.utils.Bundle;
import com.team.serveur_app.utils.ServerOnClickListener;
import com.team.serveur_app.utils.StageManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.security.spec.ECField;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable, ServerOnClickListener {

    @FXML
    GridPane serveursGridPane;




    private final ArrayList<Serveur> serveurs = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            serveurs.addAll(ServeurDAO.getAll());
            displayServeurs();
        } catch (Exception e) {
            e.printStackTrace();
        }




    }

    private void displayServeurs() throws IOException {
        int row = 0, column = 0;
        for(Serveur serveur : serveurs){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("fxml/server-item.fxml"));
            AnchorPane anchorPane = loader.load();
            ServerItemController itemController = loader.getController();
            itemController.setData(serveur, LoginController.this);
            if(column == 3){
                column = 0;
                row ++;
            }
            serveursGridPane.add(anchorPane, column++, row);
            GridPane.setMargin(anchorPane, new Insets(10));
        }
    }

    @Override
    public void onServerClick(Serveur serveur) throws IOException {
        Bundle bundle = Bundle.getInstance();
        bundle.put("currentServer",serveur);
        StageManager.replace("fxml/main-activity.fxml",true,true);
    }
}
