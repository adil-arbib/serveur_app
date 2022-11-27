package com.team.serveur_app.controller.plat;

import com.team.serveur_app.model.plat.Plat;
import com.team.serveur_app.utils.PlatOnClickListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemPlatController implements Initializable {
    @FXML
    public Label  nameLabel, priceLabel;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) throws IOException {
        myListener.onPlatClick(plat);
    }

    private Plat plat;
    private PlatOnClickListener myListener;

    public void setData(Plat plat, PlatOnClickListener myListener) {
        this.plat = plat;
        this.myListener = myListener;
        nameLabel.setText(plat.getNom());
        priceLabel.setText(plat.getPrice()+" DH");
        Image image = new Image(new ByteArrayInputStream(plat.getImg()),200,200,true,true);
        img.setImage(image);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }


}
