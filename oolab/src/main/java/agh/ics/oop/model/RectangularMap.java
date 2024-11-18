package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap extends AbstractWorldMap {

    private final Vector2d upperRight;
    private final Vector2d lowerLeft = new Vector2d(0, 0);

    public RectangularMap(int width, int height) {
        super();
        upperRight = new Vector2d(width, height);
    }

    public boolean withinBounds(Vector2d position) {
        return (position.follows(lowerLeft) && position.precedes(upperRight));
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (withinBounds(position) && super.canMoveTo(position));
    }

    @Override
    public Vector2d getUpperRight() {
        return upperRight;
    }

    @Override
    public Vector2d getLowerLeft() {
        return lowerLeft;
    }

    @Override
    public List<WorldElement> getElements() {
        return new ArrayList<>(animals.values());
    }
}
