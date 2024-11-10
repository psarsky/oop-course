package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {

    @Test
    public void outOfBoundsPlacement() {

        RectangularMap<Animal> map = new RectangularMap<>(4, 4);
        Animal animal = new Animal(new Vector2d(5, 5));

        boolean result = map.place(animal, animal.getPos());

        assertFalse(result);
    }

    @Test
    public void overlappingPlacement() {

        RectangularMap<Animal> map = new RectangularMap<>(4, 4);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 2));

        boolean result1 = map.place(animal1, animal1.getPos());
        boolean result2 = map.place(animal2, animal2.getPos());

        Animal placedAnimal = map.objectAt(new Vector2d(2, 2));

        assertTrue(result1);
        assertFalse(result2);
        assertEquals(animal1, placedAnimal);
    }

    @Test
    public void validMoves() {

        Vector2d v1 = new Vector2d(2, 2);
        Vector2d v2 = new Vector2d(1, 1);
        RectangularMap<Animal> map = new RectangularMap<>(5, 5);
        List<Vector2d> initialPositions = List.of(v1, v2);
        List<Animal> animals = List.of(new Animal(v1), new Animal(v2));
        List<MoveDirection> moves = OptionsParser.parse(new String[]{"f", "r", "f", "l", "b", "f", "r", "l"});

        Vector2d resultAnimal1 = new Vector2d(3, 3);
        Vector2d resultAnimal2 = new Vector2d(0, 2);

        Simulation<Animal, Vector2d> simulation = new Simulation<>(animals, initialPositions, moves, map);
        simulation.run();

        Animal animal1 = simulation.getObject(0);
        Animal animal2 = simulation.getObject(1);

        Vector2d pos1 = simulation.getPosition().get(animal1);
        Vector2d pos2 = simulation.getPosition().get(animal2);

        assertEquals(resultAnimal1, pos1);
        assertEquals(resultAnimal2, pos2);
    }

    @Test
    public void boundaryConditions() {

        Vector2d v1 = new Vector2d(0, 0);
        Vector2d v2 = new Vector2d(3, 3);
        RectangularMap<Animal> map = new RectangularMap<>(3, 3);
        List<Vector2d> initialPositions = List.of(v1, v2);
        List<Animal> animals = List.of(new Animal(v1), new Animal(v2));
        List<MoveDirection> moves = OptionsParser.parse(new String[]{"b", "f", "l", "r"});

        Vector2d resultAnimal1 = new Vector2d(0, 0);
        Vector2d resultAnimal2 = new Vector2d(3, 3);

        Simulation<Animal, Vector2d> simulation = new Simulation<>(animals, initialPositions, moves, map);
        simulation.run();

        Animal animal1 = simulation.getObject(0);
        Animal animal2 = simulation.getObject(1);

        Vector2d pos1 = simulation.getPosition().get(animal1);
        Vector2d pos2 = simulation.getPosition().get(animal2);

        assertEquals(resultAnimal1, pos1);
        assertEquals(resultAnimal2, pos2);
    }

    @Test
    public void emptyMoves() {

        Vector2d v1 = new Vector2d(1, 1);
        Vector2d v2 = new Vector2d(2, 3);
        RectangularMap<Animal> map = new RectangularMap<>(5, 5);
        List<Vector2d> initialPositions = List.of(v1, v2);
        List<Animal> animals = List.of(new Animal(v1), new Animal(v2));

        Vector2d resultAnimal1 = new Vector2d(1, 1);
        Vector2d resultAnimal2 = new Vector2d(2, 3);

        Simulation<Animal, Vector2d> simulation = new Simulation<>(animals, initialPositions, new ArrayList<>(), map);
        simulation.run();

        Animal animal1 = simulation.getObject(0);
        Animal animal2 = simulation.getObject(1);

        Vector2d pos1 = simulation.getPosition().get(animal1);
        Vector2d pos2 = simulation.getPosition().get(animal2);

        assertEquals(resultAnimal1, pos1);
        assertEquals(resultAnimal2, pos2);
    }

    @Test
    public void allInOne() {
        // overlapping placement, OOB placement, OOB movement, movement collisions
        RectangularMap<Animal> map = new RectangularMap<>(5, 5);
        ArrayList<Vector2d> initialPositions = new ArrayList<>();
        initialPositions.add(new Vector2d(0, 0));
        initialPositions.add(new Vector2d(0, 0));
        initialPositions.add(new Vector2d(2, 3));
        initialPositions.add(new Vector2d(6, 6));
        initialPositions.add(new Vector2d(2, 3));
        ArrayList<Animal> animals = new ArrayList<>();
        for (Vector2d v : initialPositions) {
            animals.add(new Animal(v));
        }
        List<MoveDirection> moves = OptionsParser.parse(new String[]{"f", "l", "r", "b", "f", "b", "l", "r", "l", "r", "b", "r", "b", "r", "b", "r", "b", "f", "b", "f", "b", "f", "b", "f"});

        Vector2d resultAnimal1 = new Vector2d(0, 0);
        Vector2d resultAnimal2 = new Vector2d(5, 5);

        Simulation<Animal, Vector2d> simulation = new Simulation<>(animals, initialPositions, moves, map);
        simulation.run();

        Animal animal1 = simulation.getObject(0);
        Animal animal2 = simulation.getObject(1);

        Vector2d pos1 = simulation.getPosition().get(animal1);
        Vector2d pos2 = simulation.getPosition().get(animal2);

        assertEquals(resultAnimal1, pos1);
        assertEquals(resultAnimal2, pos2);
    }
}
