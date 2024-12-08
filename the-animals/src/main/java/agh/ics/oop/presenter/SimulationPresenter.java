package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.World;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    private static final int MAP_WIDTH = 300;
    private static final int MAP_HEIGHT = 300;
    private WorldMap map;
    private Boundary bounds;
    private int width;
    private int height;
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;
    private int cellWidth;
    private int cellHeight;

    @FXML
    private GridPane mapGrid;

    @FXML
    private Label infoLabel;

    @FXML
    private TextField movesTextField;

    @FXML
    private Button startButton;

    public void setWorldMap(WorldMap map) {
        this.map = map;
        map.addObserver(this);
    }

    private void drawMap() {
        clearGrid();
        updateBounds();
        headerLabel();
        generateTable();
        addElements();
        mapGrid.setGridLinesVisible(true);
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0)); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    private void updateBounds() {
        bounds = map.getCurrentBounds();
        minX = bounds.lowerLeft().getX();
        minY = bounds.lowerLeft().getY();
        maxX = bounds.upperRight().getX();
        maxY = bounds.upperRight().getY();
        width = maxX-minX+1;
        height = maxY-minY+1;
        cellWidth = Math.round(MAP_WIDTH /width);
        cellHeight = Math.round(MAP_HEIGHT /height);
        cellHeight = Math.min(cellHeight, cellWidth);
        cellWidth = cellHeight;
    }

    private void generateTable() {
        for(int i=0; i<width; i++) {
            Label label1 = new Label(Integer.toString(i+minX));
            GridPane.setHalignment(label1, HPos.CENTER);
            mapGrid.getColumnConstraints().add(new ColumnConstraints(cellWidth));
            mapGrid.add(label1, i+1, 0);
        }
        for(int i=0; i<height; i++) {
            Label label2 = new Label(Integer.toString(maxY-i));
            GridPane.setHalignment(label2, HPos.CENTER);
            mapGrid.getRowConstraints().add(new RowConstraints(cellHeight));
            mapGrid.add(label2, 0, i+1);
        }
    }

    private void headerLabel() {
        mapGrid.getColumnConstraints().add(new ColumnConstraints(cellWidth));
        mapGrid.getRowConstraints().add(new RowConstraints(cellHeight));
        Label label = new Label("y/x");
        mapGrid.add(label, 0, 0);
        GridPane.setHalignment(label, HPos.CENTER);
    }

    private void addElements() {
        for(int i=minX; i<=maxX; i++) {
            for(int j=maxY; j>=minY; j--) {
                Vector2d pos = new Vector2d(i, j);
                if(map.isOccupied(pos)) {
                    mapGrid.add(new Label(map.objectAt(pos).toString()), i-minX+1, maxY-j+1);
                }
                else {
                    mapGrid.add(new Label(" "), i-minX+1, maxY-j+1);
                }
                mapGrid.setHalignment(mapGrid.getChildren().get(mapGrid.getChildren().size() - 1), HPos.CENTER);
            }
        }
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        this.map = worldMap;
        Platform.runLater(() -> {
            clearGrid();
            drawMap();
            infoLabel.setText(message);
        });
    }

    @FXML
    public void onSimulationStartClicked() {
        String moves = movesTextField.getText();
        List<MoveDirection> directions = OptionsParser.parse(moves.split(" "));
        List<Vector2d> positions = List.of(new Vector2d(2,3), new Vector2d(6,1));
        AbstractWorldMap map = new GrassField(10);
        setWorldMap(map);

        Simulation simulation = new Simulation(positions, directions, map);
        SimulationEngine engine = new SimulationEngine(List.of(simulation));

        infoLabel.setText("Simulation started with moves: " + moves);
        new Thread(() -> {
            engine.runAsync();
        }).start();
    }
}
