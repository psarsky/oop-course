package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {

    @Test
    public void directionAfterMoves() {
        Animal animal = new Animal();

        animal.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.EAST, animal.getDir());

        animal.move(MoveDirection.LEFT);
        assertEquals(MapDirection.NORTH, animal.getDir());

        animal.move(MoveDirection.LEFT);
        assertEquals(MapDirection.WEST, animal.getDir());
    }

    @Test
    public void positionAfterMoves() {
        Animal animal = new Animal(new Vector2d(2, 2));

        animal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 3), animal.getPos());

        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(3, 3), animal.getPos());

        animal.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(2, 3), animal.getPos());
    }

    @Test
    public void withinBounds() {
        Animal animal = new Animal(Animal.LOWER_LEFT_BOUND);

        animal.move(MoveDirection.BACKWARD);
        assertEquals(Animal.LOWER_LEFT_BOUND, animal.getPos());

        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        assertEquals(Animal.LOWER_LEFT_BOUND, animal.getPos());

        animal.setPos(Animal.UPPER_RIGHT_BOUND);
        animal.setDir(MapDirection.NORTH);
        animal.move(MoveDirection.FORWARD);
        assertEquals(Animal.UPPER_RIGHT_BOUND, animal.getPos());

        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        assertEquals(Animal.UPPER_RIGHT_BOUND, animal.getPos());
    }

    @Test
    public void simulationRun() {

        String[] args = {"f", "r", "f", "l"};
        Vector2d result = new Vector2d(3, 3);

        List<MoveDirection> moves = OptionsParser.parse(args);
        List<Vector2d> initialPositions = List.of(new Vector2d(2, 2));

        Simulation simulation = new Simulation(initialPositions, moves);
        simulation.run();

        Animal animal = simulation.getAnimal(0);
        assertTrue(animal.isAt(result));
        assertEquals(MapDirection.NORTH, animal.getDir());
    }
}
