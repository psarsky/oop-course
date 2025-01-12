package agh.ics.oop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SimulationApp extends Application implements Runnable {
    String args;

    public SimulationApp() {
        args = "";
    }

    public SimulationApp(String args) {
        this.args = args;
    }

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
        BorderPane viewRoot;
        try {
            viewRoot = loader.load();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        configureStage(primaryStage, viewRoot);

        primaryStage.show();
    }
    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        if (!Objects.equals(this.args, "")) {
            TextField moveList = (TextField) scene.lookup("#moveList");
            moveList.setText(args);
            Button startBtn = (Button) scene.lookup("#startButton");
            startBtn.fire();
            Button newSimBtn = (Button) scene.lookup("#newSimulationButton");
            startBtn.setVisible(false);
            newSimBtn.setVisible(false);
            moveList.setDisable(true);
        }
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }

    @Override
    public void run() {
        start(new Stage());
    }
}
