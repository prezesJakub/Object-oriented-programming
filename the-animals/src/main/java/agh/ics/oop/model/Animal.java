package agh.ics.oop.model;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;

    public Animal() {
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2,2);
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
        return position + " " + orientation;
    }
    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }
    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case FORWARD -> {
                if (isWithinMap(this.position.add(this.orientation.toUnitVector()))) {
                    this.position = this.position.add(this.orientation.toUnitVector());
                }
            }
            case BACKWARD -> {
                if (isWithinMap(this.position.subtract(this.orientation.toUnitVector()))) {
                    this.position = this.position.subtract(this.orientation.toUnitVector());
                }
            }
        }
    }
    private boolean isWithinMap(Vector2d position) {
        return position.precedes(new Vector2d(4,4)) && position.follows(new Vector2d(0, 0));
    }
}
