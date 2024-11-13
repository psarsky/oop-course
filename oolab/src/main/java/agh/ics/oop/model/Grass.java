package agh.ics.oop.model;

public class Grass implements WorldElement {

    private final Vector2d position;

    public Grass(Vector2d position) {
        this.position = position;
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    @Override
    public String toString() {
        return "*";
    }
    @Override
    public Vector2d getPos() {
        return position;
    }
}
