package com.team.serveur_app.controller.plat;

import com.team.serveur_app.model.plat.Plat;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class AddedPlatController implements Initializable {
    @FXML
    Label nameLabel, totalPriceLabel, countLabel;
    @FXML
    ImageView img, iconPlus, iconMinus;

    private Plat plat;
    private int count;

    public void setData(Plat plat, int count){
        this.plat = plat;
        this.count = count;
        nameLabel.setText(plat.getNom());
        totalPriceLabel.setText(plat.getPrice()*count+" DH");
        countLabel.setText(count+"");
        img.setImage(new Image(new ByteArrayInputStream(plat.getImg()),200,200,true,true));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
