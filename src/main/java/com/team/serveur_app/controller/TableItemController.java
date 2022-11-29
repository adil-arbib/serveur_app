package com.team.serveur_app.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class TableItemController implements Initializable {
    @FXML
    public Label numLabel;

    private int tableNum;

    public void setData(int tableNum){
        this.tableNum = tableNum;

        numLabel.setText(tableNum+"");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
