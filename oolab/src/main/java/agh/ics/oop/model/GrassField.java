package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.sqrt;

public class GrassField implements WorldMap {

    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final Map<Vector2d, Grass> grass = new HashMap<>();
    private Vector2d upperRight;

    public GrassField(int count) {

        upperRight = new Vector2d((int) sqrt(count * 10), (int) sqrt(count * 10));
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
        return visualizer.draw(new Vector2d(0,0), upperRight);
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (Objects.equals(objectAt(animal.getPos()), animal)) {
            animals.remove(animal.getPos());
            animal.move(direction, this);
            animals.put(animal.getPos(), animal);
        }
        int maxX = upperRight.getX();
        int maxY = upperRight.getY();
        maxX = max(maxX, animal.getPos().getX());
        maxY = max(maxY, animal.getPos().getY());
        upperRight = new Vector2d(maxX, maxY);
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
