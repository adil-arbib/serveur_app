package com.team.serveur_app.controller.table;

import com.team.serveur_app.model.table.Table;
import com.team.serveur_app.utils.OnTableClickedListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TableItemController implements Initializable {
    @FXML
    public Label numLabel;

    @FXML
    AnchorPane tablePane;

    private OnTableClickedListener tableClickedListener;

    public void setData(Table table, OnTableClickedListener tableClickedListener){
        this.tableClickedListener = tableClickedListener;
        numLabel.setText(table.getNum()+"");

        tablePane.setOnMouseClicked(e -> tableClickedListener.onTableClicked(table));


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
