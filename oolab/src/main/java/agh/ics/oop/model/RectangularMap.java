package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap {

    public static final Vector2d LOWER_LEFT_BOUND = new Vector2d(0, 0);

    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final MapVisualizer mapVisualizer;
    private final int height;
    private final int width;

    public RectangularMap(int width, int height) {
        this.height = height;
        this.width = width;
        mapVisualizer = new MapVisualizer(this);
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(LOWER_LEFT_BOUND, LOWER_LEFT_BOUND.add(new Vector2d(width, height)));
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
    public void move(Animal animal, MoveDirection direction) {

        if (!isOccupied(animal.getPos())) {
            return;
        }

        animals.remove(animal.getPos());
        animal.move(direction, this);
        animals.put(animal.getPos(), animal);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (position.follows(LOWER_LEFT_BOUND) && position.precedes(LOWER_LEFT_BOUND.add(new Vector2d(width, height))) && !isOccupied(position));
        // return (0 <= position.getX() && position.getX() <= width && 0 <= position.getY() && position.getY() <= height) && !isOccupied(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public Animal objectAt(Vector2d position) {

        if (!isOccupied(position)) {
            return null;
        }
        return animals.get(position);
    }
}