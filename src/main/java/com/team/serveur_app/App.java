package com.team.serveur_app;

import com.team.serveur_app.utils.StageManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        StageManager.init(stage,"fxml/login.fxml",false);
    }

    public static void main(String[] args) {
        launch();
    }
}