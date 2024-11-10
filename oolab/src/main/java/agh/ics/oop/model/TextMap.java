package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.List;

public class TextMap implements WorldNumberPositionMap<String, Integer> {

    private final List<String> objects = new ArrayList<>();

    @Override
    public boolean place(String object, Integer position) {
        objects.add(object);
        return true;
    }

    @Override
    public void move(String object, MoveDirection direction) {

        int currentIndex = objects.indexOf(object);

        if (currentIndex == -1) return;

        int newIndex = switch (direction) {
            case FORWARD, RIGHT -> currentIndex + 1;
            case BACKWARD, LEFT -> currentIndex - 1;
        };

        if (newIndex >= 0 && newIndex < objects.size()) {
            String swapped = objects.set(newIndex, object);
            objects.set(currentIndex, swapped);
        }
    }

    @Override
    public boolean isOccupied(Integer position) {
        return position >= 0 && position < objects.size();
    }

    @Override
    public String objectAt(Integer position) {
        return isOccupied(position) ? objects.get(position) : null;
    }

    @Override
    public boolean canMoveTo(Integer position) {
        return isOccupied(position);
    }

    @Override
    public String toString() {
        return objects.toString();
    }
}
