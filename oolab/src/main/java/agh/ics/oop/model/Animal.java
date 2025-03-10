package agh.ics.oop.model;

public class Animal implements WorldElement {

    private MapDirection direction;
    private Vector2d position;

    public Animal(Vector2d position) {
        this.direction = MapDirection.NORTH;
        this.position = position;
    }

    @Override
    public String toString() {
        return switch(direction) {
            case NORTH -> "^";
            case SOUTH -> "v";
            case EAST -> ">";
            case WEST -> "<";
        };
    }

    public void move(MoveDirection direction, MoveValidator validator) {
        Vector2d newPosition = position;
        switch (direction) {
            case RIGHT -> this.direction = this.direction.next();
            case LEFT -> this.direction = this.direction.previous();
            case FORWARD -> newPosition = position.add(this.direction.toUnitVector());
            case BACKWARD -> newPosition = this.position.subtract(this.direction.toUnitVector());
        }
        if(newPosition != position && validator.canMoveTo(newPosition)) {
            this.position = newPosition;
        }
    }

    @Override
    public Vector2d getPos() {
        return position;
    }

    public MapDirection getDir() {
        return direction;
    }
}
