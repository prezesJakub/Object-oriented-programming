package agh.ics.oop.model;

public class Animal implements WorldElement {
    private MapDirection orientation;
    private Vector2d position;

    public Animal() {
        this(new Vector2d(2, 2));
    }
    public Animal(Vector2d initialPosition) {
        this.orientation = MapDirection.NORTH;
        this.position = initialPosition;
    }
    public Vector2d getPosition() {
        return this.position;
    }
    public MapDirection getOrientation() {
        return this.orientation;
    }
    public String toString() {
        return this.orientation.toString();
    }

    @Override
    public String getResourceName() {
        return switch(this.orientation) {
            case NORTH -> "up.png";
            case EAST -> "right.png";
            case SOUTH -> "down.png";
            case WEST -> "left.png";
        };
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }
    void move(MoveDirection direction, MoveValidator validator) {
        Vector2d newPosition = switch (direction) {
            case FORWARD -> this.position.add(this.orientation.toUnitVector());
            case BACKWARD -> this.position.subtract(this.orientation.toUnitVector());
            case RIGHT -> {
                this.orientation = this.orientation.next();
                yield this.position;
            }
            case LEFT -> {
                this.orientation = this.orientation.previous();
                yield this.position;
            }
        };
        if(validator.canMoveTo(newPosition)) {
            this.position = newPosition;
        }
    }
}
