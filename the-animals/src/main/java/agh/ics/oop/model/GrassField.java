package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;
import agh.ics.oop.model.util.RandomPositionGenerator;

import java.util.*;
import java.util.stream.Stream;

public class GrassField extends AbstractWorldMap {
    private final Map<Vector2d, Grass> grasses = new HashMap<>();
    private final int grassCount;

    public GrassField(int grassCount) {
        this.grassCount = grasses.size();
        int maxCoordinate = (int) Math.sqrt(grassCount*10);

        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(maxCoordinate, maxCoordinate,
                grassCount);
        for (Vector2d position : randomPositionGenerator) {
            grasses.put(position, new Grass(position));
        }
    }

    @Override
    public Optional<WorldElement> objectAt(Vector2d position) {
        Optional<WorldElement> object = super.objectAt(position);
        if (object.isPresent()) {
            return object;
        }
        return Optional.ofNullable(grasses.get(position));
    }

    public Boundary getCurrentBounds() {
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

        return new Boundary(lowerLeft, upperRight);
    }

    public List<WorldElement> getElements() {
        return Stream.concat(super.getElements().stream(), grasses.values().stream())
                .toList();
    }
}
