package agh.ics.oop.presenter;

import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.List;

import static agh.ics.oop.OptionsParser.parse;

public class SimulationPresenter implements MapChangeListener {
    @FXML
    private Label infoLabel;
    @FXML
    private Label moveDescription;
    @FXML
    private TextField moveList;
    private WorldMap map;

    @FXML
    private void onSimulationStartClicked() {
        String[] args = moveList.getText().split(" ");
        List<MoveDirection> directions = parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        AbstractWorldMap map = new RectangularMap(10, 10);
        map.addObserver(this);
        Simulation simulation = new Simulation(positions, directions, map);
        moveDescription.setText("Simulation started with moves: " + Arrays.toString(args));
        SimulationEngine engine = new SimulationEngine(List.of(simulation));
        engine.runAsyncInThreadPool();
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        setWorldMap(worldMap);
        Platform.runLater(this::drawMap);
    }

    public void drawMap() {
        infoLabel.setText(map.toString());
    }

    public void setWorldMap(WorldMap worldMap) {
        this.map = worldMap;
    }
}
