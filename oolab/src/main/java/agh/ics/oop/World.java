package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.RectangularMap;
import agh.ics.oop.model.Vector2d;

import java.util.List;

public class World {

    public static void main(String[] args) {
        System.out.println("START");

        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(4,2), new Vector2d(4,1), new Vector2d(5,1), new Vector2d(1,2));
        Simulation simulation = new Simulation(positions, directions, new RectangularMap(8, 2));
        simulation.run();

        System.out.println("STOP");
    }
}
