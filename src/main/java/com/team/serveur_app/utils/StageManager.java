package com.team.serveur_app.utils;

import com.team.serveur_app.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;



public final class StageManager {
    private  static Stage mainStage;
    private static Scene scene;

    public static void init(Stage stage,String file, boolean resizable, String title) throws IOException {
        mainStage = stage;
        Parent loader = FXMLLoader.load(Objects.requireNonNull(App.class.getResource(file)));
        scene = new Scene(loader);
        mainStage.setResizable(resizable);
        mainStage.setScene(scene);
        mainStage.setTitle(title);
        mainStage.setResizable(resizable);
        mainStage.getIcons().add(new Image("D:\\Codes\\serveur_app\\" +
                "src\\main\\resources\\com\\team\\" +
                "serveur_app\\img\\Los_Pollos_Hermanos_logo.png"));
        mainStage.show();
    }

    public static void replace(String file, boolean resizable, boolean maximized, String title) throws IOException {
        Parent loader = FXMLLoader.load(Objects.requireNonNull(App.class.getResource(file)));
        mainStage.close();
        scene = new Scene(loader);
        mainStage.setScene(scene);
        mainStage.setTitle(title);
        mainStage.setResizable(resizable);
        mainStage.setMaximized(maximized);
        mainStage.show();
    }
}
