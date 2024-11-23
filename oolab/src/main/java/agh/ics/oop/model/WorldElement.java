package agh.ics.oop.model;

public interface WorldElement {

    default boolean isAt(Vector2d position) {
        return this.getPos().equals(position);
    }

    Vector2d getPos();
}
