package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public class RectangularMap implements WorldMap {

    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final Vector2d upperRight;
    private final Vector2d lowerLeft = new Vector2d(0, 0);

    public RectangularMap(int width, int height) {
        upperRight = new Vector2d(width, height);
    }

    public boolean withinBounds(Vector2d position) {
        return (position.follows(lowerLeft) && position.precedes(upperRight));
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(lowerLeft, upperRight);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (withinBounds(position) && !isOccupied(position));
    }

    public Vector2d getUpperRight() {
        return upperRight;
    }

    public Vector2d getLowerLeft() {
        return lowerLeft;
    }

    public List<WorldElement> getElements() {
        return new ArrayList<>(animals.values());
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
