package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap {

    private final Vector2d upperRight;

    public RectangularMap(int width, int height) {
        this.upperRight = new Vector2d(width-1, height-1);
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.precedes(upperRight) && position.follows(new Vector2d(0, 0)) && super.canMoveTo(position);
    }

    public Boundary getCurrentBounds() {
        return new Boundary(new Vector2d(0, 0), upperRight);
    }
}
