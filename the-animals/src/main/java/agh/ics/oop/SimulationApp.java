package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.presenter.SimulationPresenter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SimulationApp extends Application {
    public void start(Stage primaryStage) throws IOException, IncorrectPositionException {
        primaryStage.setTitle("Simulation App");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
        BorderPane viewRoot = loader.load();
        SimulationPresenter presenter = loader.getController();

        AbstractWorldMap grassField = new GrassField(10);
        grassField.addObserver(presenter);
        presenter.setWorldMap(grassField);
        Animal animal1 = new Animal(new Vector2d(2,3));
        grassField.place(animal1);
        grassField.move(animal1, MoveDirection.RIGHT);

        configureStage(primaryStage, viewRoot);
        primaryStage.show();
    }

    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }
}
