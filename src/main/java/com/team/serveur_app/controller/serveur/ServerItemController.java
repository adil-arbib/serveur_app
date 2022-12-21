package com.team.serveur_app.controller.serveur;

import com.team.serveur_app.model.serveur.Serveur;
import com.team.serveur_app.utils.ServerOnClickListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ServerItemController implements Initializable {

    @FXML
    Label nom_prenom;

    @FXML
    AnchorPane serverItem;

    private Serveur serveur;

    private ServerOnClickListener serverListener;

    public void setData(Serveur serveur, ServerOnClickListener listener){
        this.serveur = serveur;
        this.serverListener = listener;

        nom_prenom.setText(serveur.getNom() + "\n" + serveur.getPrenom());

        serverItem.setOnMouseClicked(e -> {
            try {
                serverListener.onServerClick(serveur);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
