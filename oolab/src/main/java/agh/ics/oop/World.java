package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

import static agh.ics.oop.OptionsParser.parse;

public class World {
    public static void main(String[] args) {
        System.out.println("START");

        try {
            List<MoveDirection> directions = parse(args);
            List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
            AbstractWorldMap map1 = new RectangularMap(8, 8);
            AbstractWorldMap map2 = new GrassField(10);
            map1.addObserver(new ConsoleMapDisplay());
            map2.addObserver(new ConsoleMapDisplay());
            Simulation simulation1 = new Simulation(positions, directions, map1);
            Simulation simulation2 = new Simulation(positions, directions, map2);
            List<Simulation> simulations = List.of(simulation1, simulation2);
            SimulationEngine engine = new SimulationEngine(simulations);
            engine.runSync();
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException: " + e.getMessage());
            return;
        }
        System.out.println("STOP");
    }
}
