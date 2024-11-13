package agh.ics.oop;

import agh.ics.oop.model.GrassField;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.RectangularMap;
import agh.ics.oop.model.Vector2d;

import java.util.List;

import static agh.ics.oop.OptionsParser.parse;

public class World {

    public static void main(String[] args) {
        System.out.println("START");

        List<MoveDirection> directions = parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        Simulation simulation1 = new Simulation(positions, directions, new RectangularMap(8, 8));
        Simulation simulation2 = new Simulation(positions, directions, new GrassField(10));
        simulation2.run();

        System.out.println("STOP");
    }
}
