package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RectangularMap implements WorldMap {

    public static final Vector2d LOWER_LEFT = new Vector2d(0, 0);
    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final Vector2d upperRight;

    public RectangularMap(int width, int height) {
        upperRight = new Vector2d(width, height);
    }

    public boolean withinBounds(Vector2d position) {
        return (position.follows(LOWER_LEFT) && position.precedes(upperRight));
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(LOWER_LEFT, upperRight);
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (Objects.equals(objectAt(animal.getPos()), animal)) {
            animals.remove(animal.getPos());
            animal.move(direction, this);
            animals.put(animal.getPos(), animal);
        }
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (withinBounds(position) && !isOccupied(position));
    }

    @Override
    public boolean place(Animal animal) {
        if (!canMoveTo(animal.getPos())) {
            return false;
        }
        animals.put(animal.getPos(), animal);
        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }
}
