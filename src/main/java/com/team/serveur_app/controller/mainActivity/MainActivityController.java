package com.team.serveur_app.controller.mainActivity;

import com.team.serveur_app.App;
import com.team.serveur_app.model.categorie.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

public class MainActivityController implements Initializable {
    @FXML
    ListView<Label> horizontalListView;
    private Label selectedLabel;
    private ArrayList<Category> allCategoriesList;

    ObservableList<Label> categoriesList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Label lbl1 = getCustomLabel("Drinks");
        selectedLabel = lbl1;
        selectedLabel.getStylesheets().add(App.class.getResource("css/labelSelected.css").toExternalForm());
        categoriesList.add(lbl1);

        displayCategories();



        horizontalListView.getStylesheets().add(App.class.getResource("css/listView.css").toExternalForm());
        horizontalListView.setItems(categoriesList);

        horizontalListView.setOnMouseClicked(event -> {
            Label label = horizontalListView.getSelectionModel().getSelectedItem();
            System.out.println(horizontalListView.getSelectionModel().getSelectedIndex());
            changeColor(label);

        });
    }

    private void displayCategories(){
        allCategoriesList = getAllCategories();
        for(Category category : allCategoriesList){
            categoriesList.add(getCustomLabel(category.getLibelle()));
        }
        horizontalListView.setItems(categoriesList);
    }

    private ArrayList<Category> getAllCategories(){
        return new ArrayList<>(Arrays.asList(
                new Category("drinks"),
                new Category("tacos"),
                new Category("pizza"),
                new Category("burger"),
                new Category("other")));
    }

    private void changeColor(Label label) {
        selectedLabel.getStylesheets().remove(0);
        selectedLabel.getStylesheets().add(App.class.getResource("css/labelStyle.css").toExternalForm());
        label.getStylesheets().add(App.class.getResource("css/labelSelected.css").toExternalForm());
        selectedLabel = label;
    }

    private Label getCustomLabel(String name){
        Label label = new Label(name);
        label.getStylesheets().add(App.class.getResource("css/labelStyle.css").toExternalForm());
        return label;
    }
}
