package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;
import agh.ics.oop.model.util.RandomPositionGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrassField extends AbstractWorldMap {
    private final Map<Vector2d, Grass> grasses = new HashMap<>();
    private final int grassCount;

    public GrassField(int grassCount) {
        this.grassCount = grassCount;
        int maxCoordinate = (int) Math.sqrt(grassCount*10);

        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(maxCoordinate, maxCoordinate,
                grassCount);
        for (Vector2d position : randomPositionGenerator) {
            grasses.put(position, new Grass(position));
        }
    }

    @Override
    protected WorldElement getAdditionalElements(Vector2d position) {
        return grasses.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animals.containsKey(position);
    }

    @Override
    public String toString() {
        Vector2d lowerLeft = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Vector2d upperRight = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);

        for (Vector2d position : animals.keySet()) {
            lowerLeft = lowerLeft.lowerLeft(position);
            upperRight = upperRight.upperRight(position);
        }
        for (Vector2d position : grasses.keySet()) {
            lowerLeft = lowerLeft.lowerLeft(position);
            upperRight = upperRight.upperRight(position);
        }

        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(lowerLeft, upperRight);
    }

    public List<WorldElement> getElements() {
        List<WorldElement> elements = super.getElements();
        elements.addAll(grasses.values());
        return elements;
    }
}
