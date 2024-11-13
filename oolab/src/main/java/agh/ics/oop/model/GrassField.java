package agh.ics.oop.model;

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

        Random random = new Random();
        int x;
        int y;
        int maxX = 0;
        int maxY = 0;
        Vector2d pos;

        for (int i = 0; i < count; i++) {

            do {
                x = random.nextInt((int) sqrt(count * 10));
                y = random.nextInt((int) sqrt(count * 10));
                pos = new Vector2d(x, y);
            } while (objectAt(pos) != null);

            grass.put(pos, new Grass(pos));
            maxX = max(maxX, x);
            maxY = max(maxY, y);
        }
        upperRight = new Vector2d(maxX, maxY);
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
        return animals.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }
}
