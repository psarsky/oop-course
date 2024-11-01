package agh.ics.oop.model;

public class Animal {
    private MapDirection direction;
    private Vector2d position;
    public static final Vector2d upperRightBound = new Vector2d(4, 4);
    public static final Vector2d lowerLeftBound = new Vector2d(0, 0);

    public Animal() {
        this.direction = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
    }
    public Animal(Vector2d position) {
        this.direction = MapDirection.NORTH;
        this.position = position;
    }
    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }
    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> this.direction = this.direction.next();
            case LEFT -> this.direction = this.direction.previous();
            case FORWARD -> {
                Vector2d newPosition = this.position.add(this.direction.toUnitVector());
                if(newPosition.precedes(upperRightBound) && newPosition.follows(lowerLeftBound)) {
                    this.position = newPosition;
                }
            }
            case BACKWARD -> {
                Vector2d newPosition = this.position.subtract(this.direction.toUnitVector());
                if(newPosition.precedes(upperRightBound) && newPosition.follows(lowerLeftBound)) {
                    this.position = newPosition;
                }
            }
        }
    }
    @Override
    public String toString() {
        return "Animal [direction = " + direction + ", position = " + position + "]";
    }
    public void setDir(MapDirection direction) {
        this.direction = direction;
    }
    public void setPos(Vector2d position) {
        this.position = position;
    }
    public MapDirection getDir() {
        return direction;
    }
    public Vector2d getPos() {
        return position;
    }
}
