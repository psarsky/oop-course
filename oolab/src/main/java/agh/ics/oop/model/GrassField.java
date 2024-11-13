package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;
import static java.lang.Math.sqrt;

public class GrassField implements WorldMap {

    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final Map<Vector2d, Grass> grass = new HashMap<>();

    public GrassField(int count) {

        Random random = new Random();
        int x;
        int y;
        Vector2d pos;

        for (int i = 0; i < count; i++) {
            do {
                x = random.nextInt((int) sqrt(count * 10));
                y = random.nextInt((int) sqrt(count * 10));
                pos = new Vector2d(x, y);
            } while (objectAt(pos) != null);
            grass.put(pos, new Grass(pos));
        }
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(getLowerLeft(), getUpperRight());
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if (isOccupied(position)) {
            return animals.get(position);
        }
        return grass.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    public Vector2d getUpperRight() {
        Vector2d upperRight = new Vector2d(0, 0);
        for (WorldElement we : getElements()) {
            upperRight = we.getPos().upperRight(upperRight);
        }
        return upperRight.add(new Vector2d(1, 1));
    }

    private Vector2d getLowerLeft() {
        Vector2d lowerLeft = new Vector2d(0, 0);
        for (WorldElement we : getElements()) {
            lowerLeft = we.getPos().lowerLeft(lowerLeft);
        }
        return lowerLeft.subtract(new Vector2d(1, 1));
    }

    public List<WorldElement> getElements() {
        List<WorldElement> worldElements = new ArrayList<>(animals.values());
        worldElements.addAll(grass.values());
        return worldElements;
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
