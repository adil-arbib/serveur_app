package com.team.serveur_app.utils;

import com.team.serveur_app.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public final class StageManager {
    private  static Stage mainStage;
    private static Scene scene;

    public static void init(Stage stage,String file, boolean resizable) throws IOException {
        mainStage = stage;
        Parent loader = FXMLLoader.load(App.class.getResource(file));
        scene = new Scene(loader);
        mainStage.setResizable(resizable);
        mainStage.setScene(scene);
        mainStage.setResizable(resizable);
        mainStage.show();
    }

    public static void replace(String file, boolean resizable, boolean maximized) throws IOException {
        Parent loader = FXMLLoader.load(App.class.getResource(file));
        mainStage.close();
        scene = new Scene(loader);
        mainStage.setScene(scene);
        mainStage.setResizable(resizable);
        mainStage.setMaximized(maximized);
        mainStage.show();
    }
}
