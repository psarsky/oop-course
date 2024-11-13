package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap {

    private final Map<Vector2d, Grass> grass = new HashMap<>();

    public GrassField(int count) {

        super();
        Vector2d rightUp = new Vector2d((int)Math.sqrt(count*10), (int)Math.sqrt(count*10));

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
    public WorldElement objectAt(Vector2d position) {
        if (!isOccupied(position)) {
            return grass.get(position);
        }
        return super.objectAt(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    @Override
    public Vector2d getUpperRight() {
        Vector2d upperRight = new Vector2d(0, 0);
        for (WorldElement we : getElements()) {
            upperRight = we.getPos().upperRight(upperRight);
        }
        return upperRight.add(new Vector2d(1, 1));
    }

    @Override
    public Vector2d getLowerLeft() {
        Vector2d lowerLeft = new Vector2d(0, 0);
        for (WorldElement we : getElements()) {
            lowerLeft = we.getPos().lowerLeft(lowerLeft);
        }
        return lowerLeft.subtract(new Vector2d(1, 1));
    }

    @Override
    public List<WorldElement> getElements() {
        List<WorldElement> worldElements = new ArrayList<>(animals.values());
        worldElements.addAll(grass.values());
        return worldElements;
    }
}
