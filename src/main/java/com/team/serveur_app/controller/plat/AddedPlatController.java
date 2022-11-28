package com.team.serveur_app.controller.plat;

import com.team.serveur_app.model.plat.Plat;
import com.team.serveur_app.utils.CancelPlatListener;
import com.team.serveur_app.utils.MinusClickListener;
import com.team.serveur_app.utils.PlusClickListener;
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
    ImageView img, iconPlus, iconMinus, iconClose;

    private Plat plat;
    private int count;
    private PlusClickListener plusListener;
    private MinusClickListener minusListener;
    private CancelPlatListener cancelPlatListener;

    public void setData(Plat plat, int count, PlusClickListener plusListener, MinusClickListener minusListener,
                        CancelPlatListener cancelPlatListener){
        this.plat = plat;
        this.count = count;
        this.plusListener = plusListener;
        this.minusListener = minusListener;
        this.cancelPlatListener = cancelPlatListener;
        nameLabel.setText(plat.getNom());
        totalPriceLabel.setText(plat.getPrice()*count+" DH");
        countLabel.setText(count+"");
        img.setImage(new Image(new ByteArrayInputStream(plat.getImg()),200,200,true,true));

        iconMinus.setOnMouseClicked(e->{
            this.count--;
            countLabel.setText(this.count+"");
            totalPriceLabel.setText(this.count*plat.getPrice()+" DH");
            minusListener.onMinusClick(plat, this.count);
        });

        iconPlus.setOnMouseClicked(e->{
            this.count++;
            countLabel.setText(this.count+"");
            totalPriceLabel.setText(this.count*plat.getPrice()+" DH");
            plusListener.onPlusClick(plat, this.count);
        });

        iconClose.setOnMouseClicked(e-> cancelPlatListener.onCancel(plat));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
