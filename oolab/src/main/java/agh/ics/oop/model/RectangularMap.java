package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap<T> implements WorldMap<T, Vector2d> {

    public static final Vector2d LOWER_LEFT_BOUND = new Vector2d(0, 0);

    private final Map<Vector2d, T> objects = new HashMap<>();
    private final Map<T, Vector2d> objectsReverse = new HashMap<>();
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
    public boolean place(T object, Vector2d position) {

        if (!canMoveTo(position)) {
            return false;
        }

        objects.put(position, object);
        objectsReverse.put(object, position);
        return true;
    }

    @Override
    public void move(T object, MoveDirection direction) {

        Vector2d currentPosition = objectsReverse.get(object);

        if (currentPosition == null) {
            return;
        }

        Vector2d newPosition = switch (direction) {
            case FORWARD -> currentPosition.add(MapDirection.NORTH.toUnitVector());
            case BACKWARD -> currentPosition.subtract(MapDirection.NORTH.toUnitVector());
            // JAK ZROBIC OBRACANIE PRAWO LEWO DLA DOWOLNEGO OBIEKTU ????????
            case RIGHT -> currentPosition.add(MapDirection.EAST.toUnitVector());
            case LEFT -> currentPosition.subtract(MapDirection.EAST.toUnitVector());
        };

        if (canMoveTo(newPosition)) {
            objects.remove(currentPosition);
            objects.put(newPosition, object);
            objectsReverse.remove(object);
            objectsReverse.put(object, newPosition);
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (position.follows(LOWER_LEFT_BOUND) &&
                position.precedes(LOWER_LEFT_BOUND.add(new Vector2d(width, height))) &&
                !isOccupied(position));
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objects.containsKey(position);
    }

    @Override
    public T objectAt(Vector2d position) {
        if (!isOccupied(position)) return null;
        return objects.get(position);
    }

    @Override
    public Map<T, Vector2d> getPositions() {
        return objectsReverse;
    }
}