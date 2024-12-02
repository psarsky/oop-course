package agh.ics.oop.presenter;

import agh.ics.oop.model.MapChangeListener;
import agh.ics.oop.model.WorldMap;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SimulationPresenter implements MapChangeListener {
    @FXML
    private Label infoLabel;
    private WorldMap map;

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        drawMap();
    }

    public void drawMap() {
        infoLabel.setText(map.toString());
    }

    public void setWorldMap(WorldMap worldMap) {
        this.map = worldMap;
    }
}
