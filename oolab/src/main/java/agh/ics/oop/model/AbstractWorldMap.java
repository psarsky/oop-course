package agh.ics.oop.model;

import agh.ics.oop.model.util.IncorrectPositionException;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public abstract class AbstractWorldMap implements WorldMap {

    protected final HashMap<Vector2d, Animal> animals;
    private final MapVisualizer mapVisualizer;

    public AbstractWorldMap() {
        animals = new HashMap<>();
        mapVisualizer = new MapVisualizer(this);
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(getLowerLeft(), getUpperRight());
    }

    @Override
    public boolean place(Animal animal) throws IncorrectPositionException {
        if (canMoveTo(animal.getPos())) {
            animals.put(animal.getPos(), animal);
            return true;
        }
        throw new IncorrectPositionException(animal.getPos());
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
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
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public List<WorldElement> getElements() {
        return new ArrayList<>(animals.values());
    }

    public abstract Vector2d getUpperRight();
    public abstract Vector2d getLowerLeft();
}
