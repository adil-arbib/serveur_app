package com.team.serveur_app.controller.mainActivity;

import com.team.serveur_app.App;
import com.team.serveur_app.model.categorie.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

public class MainActivityController implements Initializable {
    @FXML
    GridPane gridPane;
    @FXML
    ListView<Label> horizontalListView;
    private ArrayList<Category> allCategoriesList;

    ObservableList<Label> categoriesList = FXCollections.observableArrayList();
    private ArrayList<AnchorPane> nodes = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        displayCategories();
        displayAssociatedPlats(null);





        horizontalListView.getStylesheets().add(App.class.getResource("css/listView.css").toExternalForm());
        horizontalListView.setItems(categoriesList);

        horizontalListView.setOnMouseClicked(event -> {
            System.out.println(horizontalListView.getSelectionModel().getSelectedIndex());
            displayAssociatedPlats(null);
        });
    }

    private void displayAssociatedPlats(Category category){
        // test
        gridPane.getChildren().removeAll(nodes);
        nodes.clear();
        int row = 0 , column = 0;
        Random random = new Random();
        int size = random.nextInt(5,15);
        try {
            for(int i=0; i<size; i++){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(App.class.getResource("fxml/plat-item.fxml"));
                AnchorPane anchorPane = loader.load();
                if(column==3){
                    column=0;
                    row++;
                }
                gridPane.add(anchorPane, column++, row);
                nodes.add(anchorPane);
            }

        } catch (IOException e) {}
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

//    private void changeColor(Label label) {
//        label.getStylesheets().add(App.class.getResource("css/labelSelected.css").toExternalForm());
//    }

    private Label getCustomLabel(String name){
        Label label = new Label(name);
        label.getStylesheets().add(App.class.getResource("css/labelStyle.css").toExternalForm());
        return label;
    }
}
