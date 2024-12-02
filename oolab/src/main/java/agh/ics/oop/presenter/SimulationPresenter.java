package agh.ics.oop.presenter;

import agh.ics.oop.model.WorldMap;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SimulationPresenter {
    @FXML
    private Label infoLabel;
    private WorldMap worldMap;

    public void drawMap() {

    }

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
    }
}
