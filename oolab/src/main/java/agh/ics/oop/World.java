package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

import static agh.ics.oop.OptionsParser.parse;

public class World {

    public static void main(String[] args) {
        System.out.println("START");

        List<MoveDirection> directions = parse(args);
        List<Vector2d> positions = List.of(new Vector2d(4, 2), new Vector2d(4, 1), new Vector2d(5, 1));
        List<Object> animals = List.of(new Animal(new Vector2d(4, 2)), new Animal(new Vector2d(4, 1)), new Animal(new Vector2d(5, 1)));
        Simulation<Object, Vector2d> simulation = new Simulation<>(animals, positions, directions, new RectangularMap<>(8, 8));
        simulation.run();

        List<String> strings = List.of("Ala", "ma", "sowoniedźwiedzia");
        List<Integer> ints = List.of(0, 0, 0);
        Simulation<String, Integer> simulation2 = new Simulation<>(strings, ints, directions, new TextMap());
        simulation2.run();

        System.out.println("STOP");
    }
}
