package agh.ics.oop.model;

import agh.ics.oop.model.util.RandomPositionGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap {

    private final Map<Vector2d, Grass> grass = new HashMap<>();

    public GrassField(int grassCount) {

        super();
        Vector2d upperRight = new Vector2d((int)sqrt(grassCount*10), (int)sqrt(grassCount*10));
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(upperRight.getX(), upperRight.getY(), grassCount);

        for (Vector2d grassPosition : randomPositionGenerator) {
            grass.put(grassPosition, new Grass(grassPosition));
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
