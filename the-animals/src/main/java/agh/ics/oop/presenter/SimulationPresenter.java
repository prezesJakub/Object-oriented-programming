package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    private WorldMap map;

    @FXML
    private Label infoLabel;

    @FXML
    private TextField movesTextField;

    @FXML
    private Button startButton;

    public void setWorldMap(WorldMap map) {
        this.map = map;
        this.map.addObserver(this);
    }

    public void drawMap() {
        if (map != null) {
            infoLabel.setText(map.toString());
        } else {
            infoLabel.setText("");
        }
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        drawMap();
    }

    
}
