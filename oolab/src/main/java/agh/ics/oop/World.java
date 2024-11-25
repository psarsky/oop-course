package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

import static agh.ics.oop.OptionsParser.parse;

public class World {
    public static void main(String[] args) {
        System.out.println("START");
        try {
            List<MoveDirection> directions = parse(args);
            List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
            SimulationEngine engine = getSimulationEngine(positions, directions);
            engine.runAsync();
            engine.awaitSimulationsEnd();
            System.out.println("Main: " + Thread.currentThread());
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException: " + e.getMessage());
            return;
        }
        System.out.println("STOP");
    }

    private static SimulationEngine getSimulationEngine(List<Vector2d> positions, List<MoveDirection> directions) {
        List<Simulation> simulations = new ArrayList<>();
        ConsoleMapDisplay observer = new ConsoleMapDisplay();
        for (int i = 0; i < 1000; i++) {
            AbstractWorldMap map;
            if (i % 2 == 0) {
                 map = new RectangularMap(8, 8);
            }
            else {
                map = new GrassField(10);
            }
            map.addObserver(observer);
            Simulation simulation = new Simulation(positions, directions, map);
            simulations.add(simulation);
        }
        return new SimulationEngine(simulations);
    }
}
