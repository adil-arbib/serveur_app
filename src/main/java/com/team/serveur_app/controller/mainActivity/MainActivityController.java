package com.team.serveur_app.controller.mainActivity;

import com.team.serveur_app.App;
import com.team.serveur_app.controller.TableItemController;
import com.team.serveur_app.controller.plat.AddedPlatController;
import com.team.serveur_app.controller.plat.ItemPlatController;
import com.team.serveur_app.model.categorie.Categorie;
import com.team.serveur_app.model.categorie.CategorieDAO;
import com.team.serveur_app.model.plat.Plat;
import com.team.serveur_app.model.plat.PlatDAO;
import com.team.serveur_app.model.table.Table;
import com.team.serveur_app.model.table.TableDAO;
import com.team.serveur_app.utils.CancelPlatListener;
import com.team.serveur_app.utils.MinusClickListener;
import com.team.serveur_app.utils.PlatOnClickListener;
import com.team.serveur_app.utils.PlusClickListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.*;

public class MainActivityController implements Initializable, PlatOnClickListener,
        PlusClickListener, MinusClickListener, CancelPlatListener
{
    @FXML
    GridPane gridPane, rightGridPane, tablesGridPane;
    @FXML
    ListView<Label> horizontalListView;
    @FXML
    Label  totalLabel, itemsLabel, tableLabel;
    private ArrayList<Categorie> availableCategories;
    private ArrayList<Table> availableTables;
    private final ArrayList<Plat> selectedPlats = new ArrayList<>();
    private final Map<Integer, AddedPlatController> addedPlatHashMap = new HashMap<>();
    private final Map<Plat, AnchorPane> anchorPaneMap = new HashMap<>();
    private final ObservableList<Label> categoriesList = FXCollections.observableArrayList();
    private final ArrayList<AnchorPane> nodes = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayCategories();
        horizontalListView.getStylesheets().add(App.class.getResource("css/listView.css").toExternalForm());
        horizontalListView.setItems(categoriesList);
        horizontalListView.setOnMouseClicked(event -> {
            int index = horizontalListView.getSelectionModel().getSelectedIndex();
            try {
                displayAssociatedPlats(availableCategories.get(index));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        try {
            displayTables();
        } catch (SQLException | IOException e) {}

    }


    private void displayTables() throws SQLException, IOException {
        availableTables =  TableDAO.getAll();
        int row = 0, column = 0;
        for(Table table : availableTables){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("fxml/table-item.fxml"));
            AnchorPane anchorPane = loader.load();
            TableItemController itemController = loader.getController();
            itemController.setData(table.getNum());
            if(column == 2){
                column = 0;
                row ++;
            }
            tablesGridPane.add(anchorPane, column++, row);
            GridPane.setMargin(anchorPane, new Insets(10));
        }
    }

    private void displayAssociatedPlats(Categorie category) throws SQLException {
        gridPane.getChildren().removeAll(nodes);
        ArrayList<Plat> plats = PlatDAO.selectPLatByIdCat(category.getId());
        nodes.clear();
        int row = 0 , column = 0;
        try {
            for(Plat plat : plats){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(App.class.getResource("fxml/plat-item.fxml"));
                AnchorPane anchorPane = loader.load();
                ItemPlatController itemController = loader.getController();
                itemController.setData(plat,MainActivityController.this);
                if(column==3){
                    column=0;
                    row++;
                }
                gridPane.add(anchorPane, column++, row);
                nodes.add(anchorPane);
                GridPane.setMargin(anchorPane, new Insets(10));
            }

        } catch (IOException e) {}
    }

    private void displayCategories(){
        try {
            availableCategories = CategorieDAO.getAll();
            for(Categorie category : availableCategories){
                categoriesList.add(getCustomLabel(category.getLibelle()));
            }
            horizontalListView.setItems(categoriesList);
            if(availableCategories.size()>0)displayAssociatedPlats(availableCategories.get(0));
        } catch (SQLException e) {}


    }



//    private void changeColor(Label label) {
//        label.getStylesheets().add(App.class.getResource("css/labelSelected.css").toExternalForm());
//    }

    private Label getCustomLabel(String name){
        Label label = new Label(name);
        label.getStylesheets().add(App.class.getResource("css/labelStyle.css").toExternalForm());
        return label;
    }

    private int platCount(Plat plat){
        int count = 0;
        for(Plat p : selectedPlats){
            if(plat.equals(p)) count++;
        }
        return count;
    }

    /**
     * calculate total price, total items
     */
    private void calculate(){
        int totalPrice = 0;
        for(Plat plat : selectedPlats) totalPrice += plat.getPrice();
        totalLabel.setText(totalPrice+" DH");
        itemsLabel.setText(selectedPlats.size()+"");
    }


    int sidePaneRow = 0;

    @Override
    public void onPlatClick(Plat plat) throws IOException {
        selectedPlats.add(plat);
        calculate();
        if(addedPlatHashMap.containsKey(plat.getId())){
            addedPlatHashMap.get(plat.getId()).setData(plat,platCount(plat),
                    MainActivityController.this,
                    MainActivityController.this,
                    MainActivityController.this
            );
        }else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("fxml/added-plat-item.fxml"));
            AnchorPane anchorPane = loader.load();
            AddedPlatController addedPlatController = loader.getController();
            addedPlatController.setData(plat,platCount(plat),
                    MainActivityController.this,
                    MainActivityController.this,
                    MainActivityController.this);

            rightGridPane.add(anchorPane, 0, sidePaneRow++);
            anchorPaneMap.put(plat, anchorPane);
            GridPane.setMargin(anchorPane, new Insets(10));
            addedPlatHashMap.put(plat.getId(), addedPlatController);
        }

    }

    @Override
    public void onMinusClick(Plat plat, int count) {
        if(count==0){
            rightGridPane.getChildren().remove(anchorPaneMap.get(plat));
            addedPlatHashMap.remove(plat.getId());
        }
        selectedPlats.remove(plat);
        calculate();
    }

    @Override
    public void onPlusClick(Plat plat, int count) {
        selectedPlats.add(plat);
        calculate();
    }

    @Override
    public void onCancel(Plat plat) {
        selectedPlats.removeIf(p -> p.equals(plat));
        rightGridPane.getChildren().remove(anchorPaneMap.get(plat));
        addedPlatHashMap.remove(plat.getId());
        calculate();
    }
}
