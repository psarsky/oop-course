package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {

    @Test
    public void validMoves() {

        RectangularMap map = new RectangularMap(5, 5);
        List<Vector2d> initialPositions = List.of(new Vector2d(2, 2), new Vector2d(1, 1));
        List<MoveDirection> moves = OptionsParser.parse(new String[]{"f", "r", "f", "l", "b", "f", "r", "l"});

        Vector2d resultAnimal1 = new Vector2d(2, 3);
        Vector2d resultAnimal2 = new Vector2d(1, 2);

        Simulation simulation = new Simulation(initialPositions, moves, map);
        simulation.run();

        Animal animal1 = simulation.getAnimal(0);
        Animal animal2 = simulation.getAnimal(1);

        assertEquals(resultAnimal1, animal1.getPos());
        assertEquals(resultAnimal2, animal2.getPos());

        assertEquals(MapDirection.EAST, animal1.getDir());
        assertEquals(MapDirection.WEST, animal2.getDir());
    }

    @Test
    public void boundaryConditions() {

        RectangularMap map = new RectangularMap(3, 3);
        List<Vector2d> initialPositions = List.of(new Vector2d(0, 0), new Vector2d(3, 3));
        List<MoveDirection> moves = OptionsParser.parse(new String[]{"b", "f", "l", "r", "f", "f"});

        Vector2d resultAnimal1 = new Vector2d(0, 0);
        Vector2d resultAnimal2 = new Vector2d(3, 3);

        Simulation simulation = new Simulation(initialPositions, moves, map);
        simulation.run();

        Animal animal1 = simulation.getAnimal(0);
        Animal animal2 = simulation.getAnimal(1);

        assertEquals(resultAnimal1, animal1.getPos());
        assertEquals(resultAnimal2, animal2.getPos());
    }

    @Test
    public void overlappingPlacement() {

        Vector2d testPosition = new Vector2d(2, 2);
        RectangularMap map = new RectangularMap(5, 5);
        List<Vector2d> initialPositions = List.of(testPosition, testPosition);
        List<MoveDirection> moves = OptionsParser.parse(new String[]{"f"});

        Simulation simulation = new Simulation(initialPositions, moves, map);

        assertTrue(map.isOccupied(testPosition));
        assertEquals(testPosition, simulation.getAnimal(0).getPos());
        assertEquals(1, simulation.getAnimals().size());
    }

    @Test
    public void emptyMoves() {

        RectangularMap map = new RectangularMap(5, 5);
        List<Vector2d> initialPositions = List.of(new Vector2d(1, 1), new Vector2d(2, 3));

        Vector2d resultAnimal1 = new Vector2d(1, 1);
        Vector2d resultAnimal2 = new Vector2d(2, 3);

        Simulation simulation = new Simulation(initialPositions, new ArrayList<>(), map);
        simulation.run();

        Animal animal1 = simulation.getAnimal(0);
        Animal animal2 = simulation.getAnimal(1);

        assertEquals(resultAnimal1, animal1.getPos());
        assertEquals(resultAnimal2, animal2.getPos());

        assertEquals(MapDirection.NORTH, animal1.getDir());
        assertEquals(MapDirection.NORTH, animal2.getDir());
    }
}
