package agh.ics.oop.model;

import agh.ics.oop.OptionsParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class AnimalTest {

    @Test
    void isAnimalAtPositionDefault() {
        Vector2d position = new Vector2d(2,2);
        Animal animal = new Animal();

        boolean result = animal.isAt(position);

        assertTrue(result);
    }

    @Test
    void isAnimalAtPositionCustom() {
        Vector2d position = new Vector2d(1,3);
        Animal animal = new Animal(new Vector2d(1,3));

        boolean result = animal.isAt(position);

        assertTrue(result);
    }

    @Test
    void isDirectionCorrect() {
        Animal animal = new Animal(new Vector2d(0,0));
        for(int i = 0; i < 5; i++) {
            animal.move(MoveDirection.LEFT);
        }
        assertEquals(MapDirection.WEST, animal.getDir());
    }

    @Test
    void isDirectionCorrect2() {
        Animal animal = new Animal();

        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);

        assertEquals(MapDirection.SOUTH, animal.getDir());
    }

    @Test
    void isAnimalGoingToProperPosition() {
        Animal animal = new Animal(new Vector2d(3,3));
        Vector2d result = new Vector2d(2,4);

        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);

        assertTrue(animal.isAt(result));
    }

    @Test
    void inputInterpretation() {
        String[] args = {"f","f","l","f","sadsa","sdafsd","f","sdafds","f","f","r","b","b","r","f","f","f","l","f","f"};
        Vector2d result = new Vector2d(3, 2);

        List<MoveDirection> moves = OptionsParser.parse(args);
        Animal animal = new Animal(new Vector2d(0,0));
        for(MoveDirection move : moves) {
            animal.move(move);
        }

        assertTrue(animal.isAt(result));
    }

    @Test
    void integrationTest() {
        Animal animal = new Animal(new Vector2d(0,0));
        Vector2d result = new Vector2d(1,0);

        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);

        assertTrue(animal.isAt(result));
        assertEquals(MapDirection.EAST, animal.getDir());

        result = new Vector2d(0,0);

        animal.move(MoveDirection.BACKWARD);

        assertTrue(animal.isAt(result));
        assertEquals(MapDirection.EAST, animal.getDir());

        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);

        assertTrue(animal.isAt(result));
        assertEquals(MapDirection.SOUTH, animal.getDir());

        result = new Vector2d(0,2);

        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);

        assertTrue(animal.isAt(result));
        assertEquals(MapDirection.SOUTH, animal.getDir());

        animal.move(MoveDirection.LEFT);

        assertEquals(MapDirection.EAST, animal.getDir());

        animal.move(MoveDirection.LEFT);

        assertEquals(MapDirection.NORTH, animal.getDir());

        animal.move(MoveDirection.LEFT);

        assertEquals(MapDirection.WEST, animal.getDir());

        animal.move(MoveDirection.FORWARD);

        assertTrue(animal.isAt(result));

        result = new Vector2d(2,2);

        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);

        assertTrue(animal.isAt(result));
        assertEquals(MapDirection.WEST, animal.getDir());

        result = new Vector2d(4, 2);

        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);

        assertTrue(animal.isAt(result));
        assertEquals(MapDirection.WEST, animal.getDir());

        String[] args = {"f", "b", "r", "l", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f"};
        List<MoveDirection> moves = OptionsParser.parse(args);
        result = new Vector2d(0, 2);

        for(MoveDirection move : moves) {
            animal.move(move);
        }

        assertTrue(animal.isAt(result));
        assertEquals(MapDirection.WEST, animal.getDir());
    }
}